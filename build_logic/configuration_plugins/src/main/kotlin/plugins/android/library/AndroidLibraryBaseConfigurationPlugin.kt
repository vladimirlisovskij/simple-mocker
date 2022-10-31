package plugins.android.library

import com.android.build.api.dsl.LibraryExtension
import internal.Constants
import internal.Ext.baseConfiguration
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryBaseConfigurationPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                baseConfiguration(this)
                defaultConfig.targetSdk = Constants.TARGET_SDK
            }
        }
    }
}
