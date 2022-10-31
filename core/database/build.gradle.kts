plugins {
    id("projectPlugins.config.android.library.base")
    alias(libs.plugins.sqldelight)
}

android {
    sqldelight {
        database("RequestsDataBase") {
            packageName = "com.github.vladimirlisovskij.simple_mocker.core.datasource.database.requests"
            sourceFolders = listOf("res/sqldelight")
        }
    }
}

dependencies {
    implementation(projects.core.componentHolder)

    implementation(libs.bundles.sqldelight)
}