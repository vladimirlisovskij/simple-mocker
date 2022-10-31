plugins {
    id("projectPlugins.config.android.library.base")
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.sqldelight)
}

android {
    sqldelight {
        database("RequestsDataBase") {
            packageName = "com.github.vladimirlisovskij.simple_mocker.shared.database.requests"
            sourceFolders = listOf("res/sqldelight")
        }
    }
}

dependencies {
    implementation(projects.core.database)

    implementation(libs.androidx.core)
    implementation(libs.dagger.core)
    implementation(libs.bundles.sqldelight)

    kapt(libs.dagger.kapt)
}