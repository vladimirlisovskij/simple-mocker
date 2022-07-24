plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.sqldelight)
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

    sqldelight {
        database("RequestsDataBase") {
            packageName = "com.github.vladimirlisovskij.simple_mocker.core.datasource.database.requests"
            sourceFolders = listOf("res/sqldelight")
        }
    }
}

dependencies {
    implementation(libs.dagger.core)
    implementation(libs.bundles.sqldelight)

    kapt(libs.dagger.kapt)
}