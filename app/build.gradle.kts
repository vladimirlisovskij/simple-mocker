plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.github.vladimirlisovskij.simple_mocker"
        minSdk = 21
        targetSdk = 32
        versionCode = 1
        versionName = "1.0-alpha1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(projects.service)
    implementation(projects.ui)
    implementation(projects.shared)

    implementation(libs.androidx.core)

    implementation(libs.dagger.core)
    kapt(libs.dagger.kapt)
}