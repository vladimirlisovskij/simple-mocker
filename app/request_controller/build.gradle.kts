plugins {
    id("projectPlugins.config.android.application.base")
    alias(libs.plugins.kotlin.kapt)
}

android {
    defaultConfig {
        applicationId = "com.github.vladimirlisovskij.simple_mocker"
        versionCode = 2
        versionName = "1.0-alpha2"
    }
}

dependencies {
    // implementation(projects.service)
    // implementation(projects.ui)
    // implementation(projects.shared)
    //
    // implementation(projects.core.design)
    //
    // implementation(libs.androidx.core)
    //
    // implementation(libs.dagger.core)
    // kapt(libs.dagger.kapt)
}