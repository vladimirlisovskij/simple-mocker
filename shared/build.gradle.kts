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
        database("MockRequestsDatabase") {
            packageName = "com.high_quality_solution.simplemocker.shared.mock_requests_database"
            sourceFolders = listOf("res/sqldelight")
        }
    }
}

dependencies {
    implementation(libs.androidx.core)
    implementation(libs.dagger.core)
    implementation(libs.sqldeilght)

    kapt(libs.dagger.kapt)

    implementation("androidx.collection:collection:1.2.0")
}