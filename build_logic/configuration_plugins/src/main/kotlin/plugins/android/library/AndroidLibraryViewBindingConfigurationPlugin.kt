package plugins.android.library

import com.android.build.api.dsl.LibraryExtension
import internal.Ext.configureViewBinding
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryViewBindingConfigurationPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            extensions.configure<LibraryExtension> {
                configureViewBinding(this)
            }
        }
    }
}

