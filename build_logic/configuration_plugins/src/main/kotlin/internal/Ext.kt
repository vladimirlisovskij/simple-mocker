package internal

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import org.jetbrains.kotlin.gradle.dsl.kotlinExtension

internal object Ext {
    val Project.libs get() = extensions.getByType<VersionCatalogsExtension>().named(Constants.VERSION_CATALOG_NAME)

    fun Project.baseConfiguration(commonExtension: CommonExtension<*, *, *, *>) {
        with(commonExtension) {
            compileSdk = Constants.COMPILE_SDK
            defaultConfig {
                minSdk = Constants.MIN_SDK
            }

            compileOptions {
                targetCompatibility = Constants.JAVA_VERSION
                sourceCompatibility = Constants.JAVA_VERSION
                isCoreLibraryDesugaringEnabled = true
            }

            (this as ExtensionAware).extensions.configure<KotlinJvmOptions> {
                jvmTarget = Constants.JAVA_VERSION.toString()
            }

            dependencies {
                add("coreLibraryDesugaring", libs.findLibrary("desugar").get())
            }
        }
    }

    fun Project.configureViewBinding(commonExtension: CommonExtension<*, *, *, *>) {
        with(commonExtension) {
            buildFeatures {
                viewBinding = true
            }
        }
    }

    fun Project.configureCompose(commonExtension: CommonExtension<*, *, *, *>) {
        with(commonExtension) {
            buildFeatures {
                compose = true
            }

            composeOptions {
                kotlinCompilerExtensionVersion = libs.findVersion("androidx-compose-core-version").get().requiredVersion
            }

            dependencies {
                add("implementation", libs.findLibrary("androidx-compose-core").get())
                add("implementation", libs.findLibrary("androidx-compose-material").get())
                add("implementation", libs.findLibrary("androidx-compose-preview-default").get())
                add("debugImplementation", libs.findLibrary("androidx-compose-preview-debug").get())
            }
        }
    }
}