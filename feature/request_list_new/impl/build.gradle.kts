plugins {
    id("projectPlugins.config.android.library.base")
    // id("projectPlugins.config.android.library.compose")
}

android {
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.0"
    }
}

dependencies {
    implementation(projects.core.design)

    implementation(libs.androidx.compose.core)
    implementation("androidx.compose.foundation:foundation:1.3.0")
    // implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.icons.core)
    implementation(libs.androidx.compose.preview.default)
    debugImplementation(libs.androidx.compose.preview.debug)
}