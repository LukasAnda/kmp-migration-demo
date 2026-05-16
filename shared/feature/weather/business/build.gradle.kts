plugins {
    id("demo.kmp")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":shared:core:domain"))
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.bundles.koin)
        }
    }
}
