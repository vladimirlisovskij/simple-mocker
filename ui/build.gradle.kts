plugins {
    id("projectPlugins.config.android.library.base")
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.androidx.navigation)
}

dependencies {
    implementation(projects.shared)
    implementation(projects.core.infrastructure.view.plainAdapter)
    implementation(projects.okhttp)

    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.fragmentKtx)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.coroutines)
    implementation(libs.dagger.core)
    implementation(libs.viewbindingDelegate)

    implementation(libs.bundles.androidx.navigation)

    kapt(libs.dagger.kapt)
}