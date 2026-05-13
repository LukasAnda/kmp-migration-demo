plugins {
    id("com.example.kmp.library")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(libs.bundles.koin)
        }
    }
}
