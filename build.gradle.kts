plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.compose) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.lighthouse)
}

allprojects {
    group = "com.example.weather"
    version = "1.0.0"

    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("buildAllIosFrameworks") {
    description = "Build iOS frameworks for all modules (demonstrates the slow pattern)"
    group = "build"

    dependsOn(
        gradle.includedBuild("gradlePlugins").task(":assemble")
    )

    doLast {
        println("Building all iOS frameworks...")
    }
}

gradle.projectsEvaluated {
    val iosLinkTasks = subprojects.flatMap { subproject ->
        subproject.tasks.matching { task ->
            task.name.startsWith("linkReleaseFrameworkIos")
        }
    }
    tasks.named("buildAllIosFrameworks") {
        dependsOn(iosLinkTasks)
    }
}
