plugins {
    id("demo.kmp")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":shared:feature:weather:business"))
            implementation(project(":shared:core:data-remote"))
            implementation(libs.bundles.koin)
        }
    }
}
