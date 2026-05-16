plugins {
    `kotlin-dsl`
}

group = "com.example.buildlogic"

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
            id = "demo.kmp"
            implementationClass = "KmpConventionPlugin"
        }
        register("cmpLibrary") {
            id = "demo.cmp"
            implementationClass = "CmpConventionPlugin"
        }
    }
}
