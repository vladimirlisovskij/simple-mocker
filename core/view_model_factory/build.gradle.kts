plugins {
    id("projectPlugins.config.android.library.base")
    alias(libs.plugins.kotlin.kapt)
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.dagger.core)

    kapt(libs.dagger.kapt)
}