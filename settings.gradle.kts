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

rootProject.name = "coroutine-play"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS") // Enable usage like implementation(projects.core.datastore) in gradle

include(":app")
