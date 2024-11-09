pluginManagement {

    includeBuild("build-logic")

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal() // External plugins can be resolved in dependencies section
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "android-template-repo"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS") // Enable usage like implementation(projects.core.designsystem) in gradle

include(":app")
include(":core")
include(":core:designsystem")
