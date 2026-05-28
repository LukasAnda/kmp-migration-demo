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
}

tasks.register("buildAllIosFrameworks") {
    description = "Build iOS frameworks (only umbrella module produces framework in step-4)"
    group = "build"

    doLast {
        println("Building iOS frameworks...")
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
