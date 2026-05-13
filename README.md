# KMP Migration Demo

A sample Kotlin Multiplatform weather app showing a step-by-step migration from AGP 8 to AGP 9, with improvements to build configuration, dependency injection, and iOS build times.

Built for a conference talk. Each branch represents one migration step, so you can diff between them to see exactly what changed.

## What This Demo Covers

The migration path has four steps, each in its own branch:

### main (Baseline)

The "before" state. A working KMP app with:

- AGP 8.7.3, Gradle 8.10.2, Kotlin 2.1.0
- Old-style `gradlePlugins/` directory with custom plugins
- Manual Koin module definitions using `module { single { ... } }`
- iOS framework configured in every KMP module

This is how many production KMP projects still look today.

### step-1-agp9 (Version Upgrades)

Upgrade the toolchain:

- AGP 8.7.3 to 9.1.0
- Gradle 8.10.2 to 9.4.0
- Kotlin 2.1.0 to 2.1.20
- Enable configuration cache

AGP 9 with Gradle 9 enables configuration cache by default. After the first build populates the cache, subsequent builds skip the configuration phase entirely. This cuts Android build times roughly in half for incremental builds.

### step-2-build-logic (Convention Plugins)

Replace the old `gradlePlugins/` with a proper `build-logic/` setup:

- Delete `gradlePlugins/` directory
- Create `build-logic/convention/` with `demo.kmp` and `demo.cmp` plugins
- Remove the `allprojects {}` block from root build.gradle.kts
- Update all modules to use the new plugin IDs

Convention plugins are the recommended way to share build logic. They work better with configuration caching and produce cleaner build output.

### step-3-koin-compiler (Koin DSL)

Switch from manual Koin wiring to the DSL functions:

- Replace `module { single { ClassName() } }` with `singleOf(::ClassName)`
- Replace manual viewModel factories with `viewModelOf(::ViewModelName)`
- Update Koin to 4.1.0

This removes boilerplate and makes the DI setup more concise. The DSL functions also provide better error messages at runtime.

### step-4-ios-umbrella (iOS Framework Consolidation)

Stop building iOS frameworks in every module:

- Remove `binaries.framework` configuration from the KMP convention plugin
- Only the umbrella module (`shared/app`) produces the iOS framework
- Submodules compile to `.klib` only

When you have 50+ modules each producing their own framework, Kotlin/Native spends most of its time linking. By consolidating to a single umbrella framework, you eliminate all that redundant linking work.

## Build Times

Measured on MacBook Pro M2:

### Android (clean build)

| Branch | First Build | With Config Cache |
|--------|-------------|-------------------|
| main (AGP 8) | 16s | N/A |
| step-1-agp9 (AGP 9) | 14s | 7s |

Configuration cache stores the result of the configuration phase. After the first build, Gradle skips configuration entirely and jumps straight to task execution.

### iOS Release Build (Xcode Archive)

| Branch | buildAllIosFrameworks |
|--------|----------------------|
| main through step-3 | 24m 25s |
| step-4-ios-umbrella | 2m 41s |

When you Archive in Xcode to publish your app, it triggers Gradle to build iOS frameworks. The slow part is not compilation but linking - Kotlin/Native has to convert Kotlin IR to LLVM IR, run optimization passes, and generate native code.

On main through step-3, every module with `binaries.framework` config runs the linker separately. That is 10 modules times 3 architectures equals 30 link tasks. On step-4, submodules only compile to .klib (fast), and the linker runs once for the umbrella module.

This is representative of what happens during a real release build. Run `./gradlew buildAllIosFrameworks` to measure it yourself.

## Project Structure

```
kmp-migration-demo/
├── build-logic/convention/     # Convention plugins (step-2 and later)
├── gradlePlugins/              # Old-style plugins (main and step-1)
├── shared/
│   ├── app/                    # Umbrella module, Koin aggregation, iOS framework
│   ├── core/
│   │   ├── domain/             # Result, AppError, DispatcherProvider
│   │   ├── data-remote/        # HttpClient factory
│   │   ├── presentation/       # BaseViewModel
│   │   └── di/                 # Koin initialization
│   └── feature/weather/
│       ├── business/           # Models, repository interface, service
│       ├── infrastructure/     # Repository implementation
│       ├── api/                # DTOs, fake data source
│       └── ui/                 # ViewModel, Compose screen
└── platform/
    ├── android/                # Android app
    └── ios/                    # iOS app (XcodeGen)
```

## Prerequisites

- JDK 17 or newer
- Android Studio Ladybug or newer
- Xcode 15 or newer (for iOS builds)
- XcodeGen: `brew install xcodegen`

## Running the App

Android:

```bash
./gradlew :platform:android:installDebug
```

iOS:

```bash
cd platform/ios && xcodegen generate
./gradlew :shared:app:linkReleaseFrameworkIosSimulatorArm64
open platform/ios/WeatherDemo.xcodeproj
```

Then run from Xcode on a simulator.

## Comparing Branches

To see what changed in each step:

```bash
git diff main..step-1-agp9
git diff step-1-agp9..step-2-build-logic
git diff step-2-build-logic..step-3-koin-compiler
git diff step-3-koin-compiler..step-4-ios-umbrella
```

## Measuring Build Times Yourself

Android with configuration cache:

```bash
# First build (populates cache)
./gradlew clean
./gradlew :platform:android:assembleDebug

# Second build (uses cache)
./gradlew clean
time ./gradlew :platform:android:assembleDebug
```

iOS framework:

```bash
./gradlew clean
time ./gradlew :shared:app:linkReleaseFrameworkIosSimulatorArm64
```

## Tech Stack

- Kotlin 2.1.0 / 2.1.20
- Compose Multiplatform 1.7.3
- Koin 4.0.0 / 4.1.0
- Ktor 3.0.3
- AGP 8.7.3 / 9.1.0
- Gradle 8.10.2 / 9.4.0
