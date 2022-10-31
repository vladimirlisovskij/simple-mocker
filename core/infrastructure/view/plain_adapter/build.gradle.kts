plugins {
    id("projectPlugins.config.android.library.base")
    id("projectPlugins.config.android.library.viewBinding")
    alias(libs.plugins.kotlin.kapt)
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.recycler)
}
