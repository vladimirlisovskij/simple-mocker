plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 21
        targetSdk = 32
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(projects.feature.requestList.api)
    implementation(projects.core.infrastructure.view.base)
    implementation(projects.core.infrastructure.view.navigationFactory)
    implementation(projects.core.infrastructure.view.viewModelFactory)
    implementation(projects.core.infrastructure.di.dependencyContainer)
    implementation(projects.core.design)

    implementation(libs.dagger.core)
    implementation(libs.viewbindingDelegate)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.fragmentKtx)

    kapt(libs.dagger.kapt)
}