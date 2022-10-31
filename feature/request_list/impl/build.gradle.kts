plugins {
    id("projectPlugins.config.android.library.base")
    id("projectPlugins.config.android.library.viewBinding")
    alias(libs.plugins.kotlin.kapt)
}

dependencies {
    implementation(projects.feature.requestList.api)
    implementation(projects.core.infrastructure.view.base)
    implementation(projects.core.design)

    implementation(libs.dagger.core)
    implementation(libs.viewbindingDelegate)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.fragmentKtx)

    kapt(libs.dagger.kapt)
}