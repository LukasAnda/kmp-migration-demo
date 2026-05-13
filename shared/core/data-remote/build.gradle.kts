plugins {
    id("com.example.kmp.library")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.ktor.core)
            implementation(libs.bundles.koin)
        }
        androidMain.dependencies {
            implementation(libs.ktor.cio)
        }
        iosMain.dependencies {
            implementation(libs.ktor.darwin)
        }
    }
}
