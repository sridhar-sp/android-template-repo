import com.droidstarter.support.AppConfig

plugins {
    alias(libs.plugins.droidstarter.android.application.compose)
    alias(libs.plugins.droidstarter.android.hilt)
}

android {
    namespace = AppConfig.namespace

    defaultConfig {
        applicationId = AppConfig.applicationId
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtimeKtx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(projects.core.designsystem)

    debugImplementation(libs.androidx.compose.ui.tooling)
    testImplementation(libs.junit4)
}