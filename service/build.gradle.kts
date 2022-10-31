plugins {
    id("projectPlugins.config.android.library.base")

    alias(libs.plugins.kotlin.kapt)
}

dependencies {
    implementation(projects.core.aidl)
    implementation(projects.shared)

    implementation(libs.dagger.core)
    kapt(libs.dagger.kapt)
}