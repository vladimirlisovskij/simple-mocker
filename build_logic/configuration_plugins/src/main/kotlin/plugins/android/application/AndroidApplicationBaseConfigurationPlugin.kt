package plugins.android.application

import com.android.build.api.dsl.ApplicationExtension
import internal.Constants
import internal.Ext.baseConfiguration
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationBaseConfigurationPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<ApplicationExtension> {
                baseConfiguration(this)
                defaultConfig.targetSdk = Constants.TARGET_SDK
            }
        }
    }
}

