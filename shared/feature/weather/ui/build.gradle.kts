plugins {
    id("demo.cmp")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":shared:feature:weather:business"))
            implementation(project(":shared:core:domain"))
            implementation(project(":shared:core:presentation"))
            implementation(libs.bundles.koin)
        }
    }
}
