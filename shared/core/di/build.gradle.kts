plugins {
    id("demo.kmp")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(libs.bundles.koin)
        }
    }
}
