plugins {
    `kotlin-dsl`
}

group = "com.droidstarter.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        create("androidApplicationCompose") {
            id = "com.droidstarter.convention.application.compose"
            implementationClass = "com.droidstarter.convention.AndroidApplicationComposeConventionPlugin"
        }

        create("androidApplication") {
            id = "com.droidstarter.convention.application"
            implementationClass = "com.droidstarter.convention.AndroidApplicationConventionPlugin"
        }

        create("androidLibrary") {
            id = "com.droidstarter.convention.library"
            implementationClass = "com.droidstarter.convention.AndroidLibraryConventionPlugin"
        }

        create("androidLibraryCompose") {
            id = "com.droidstarter.convention.library.compose"
            implementationClass = "com.droidstarter.convention.AndroidLibraryComposeConventionPlugin"
        }

        create("hilt") {
            id = "com.droidstarter.convention.hilt"
            implementationClass = "com.droidstarter.convention.AndroidHiltConventionPlugin"
        }

        create("jvmLibrary") {
            id = "com.droidstarter.convention.jvm.library"
            implementationClass = "com.droidstarter.convention.JvmLibraryConventionPlugin"
        }
    }
}