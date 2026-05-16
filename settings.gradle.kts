pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "kmp-migration-demo"

include(":platform:android")
include(":platform:ios")

include(":shared:app")

include(":shared:core:domain")
include(":shared:core:data-remote")
include(":shared:core:presentation")
include(":shared:core:di")

include(":shared:feature:weather:business")
include(":shared:feature:weather:infrastructure")
include(":shared:feature:weather:api")
include(":shared:feature:weather:ui")
