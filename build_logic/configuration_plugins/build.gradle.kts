plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.gradle.plugin.buildtools)
    implementation(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("AndroidApplicationBaseConfigurationPlugin") {
            id = "projectPlugins.config.android.application.base"
            implementationClass = "plugins.android.application.AndroidApplicationBaseConfigurationPlugin"
        }

        register("AndroidLibraryBaseConfigurationPlugin") {
            id = "projectPlugins.config.android.library.base"
            implementationClass = "plugins.android.library.AndroidLibraryBaseConfigurationPlugin"
        }

        register("AndroidLibraryViewBindingConfigurationPlugin") {
            id = "projectPlugins.config.android.library.viewBinding"
            implementationClass = "plugins.android.library.AndroidLibraryViewBindingConfigurationPlugin"
        }

        register("AndroidLibraryComposeConfigurationPlugin") {
            id = "projectPlugins.config.android.library.compose"
            implementationClass = "plugins.android.library.AndroidLibraryComposeConfigurationPlugin"
        }
    }
}


