plugins {
    id("com.example.kmp.library")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":shared:feature:weather:business"))
            implementation(project(":shared:feature:weather:api"))
            implementation(project(":shared:core:domain"))
            implementation(libs.bundles.koin)
        }
    }
}
