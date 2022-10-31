plugins {
    id("projectPlugins.config.android.library.base")
}

dependencies {
    api(libs.androidx.appcompat)
    implementation(libs.coroutines)
}