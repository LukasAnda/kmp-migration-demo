plugins {
    id("com.example.kmp.compose.library")
}

kotlin {
    // Framework binaries configured by plugin (every module has one - the slow pattern)

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
