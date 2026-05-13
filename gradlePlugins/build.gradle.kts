plugins {
    `kotlin-dsl`
}

group = "com.example.plugins"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("kmpLibrary") {
            id = "com.example.kmp.library"
            implementationClass = "com.example.plugins.KmpLibraryPlugin"
        }
        register("kmpComposeLibrary") {
            id = "com.example.kmp.compose.library"
            implementationClass = "com.example.plugins.KmpComposeLibraryPlugin"
        }
    }
}
