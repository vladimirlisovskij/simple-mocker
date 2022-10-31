package plugins.android.library

import com.android.build.api.dsl.LibraryExtension
import internal.Ext.configureCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryComposeConfigurationPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            extensions.configure<LibraryExtension> {
                configureCompose(this)
            }
        }
    }
}