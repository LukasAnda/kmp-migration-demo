plugins {
    id("demo.cmp")
}

kotlin {
    // Only shared:app produces the iOS framework
    // Submodules compile to .klib only (configured in KmpConventionPlugin)
    listOf(iosX64(), iosArm64(), iosSimulatorArm64()).forEach { target ->
        target.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            api(project(":shared:core:domain"))
            api(project(":shared:core:data-remote"))
            api(project(":shared:core:presentation"))
            api(project(":shared:core:di"))

            api(project(":shared:feature:weather:business"))
            api(project(":shared:feature:weather:infrastructure"))
            api(project(":shared:feature:weather:api"))
            api(project(":shared:feature:weather:ui"))

            implementation(libs.bundles.koin)
        }
    }
}
