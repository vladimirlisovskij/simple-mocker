enableFeaturePreview("VERSION_CATALOGS")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }

    versionCatalogs {
        create("libs") {
            from(files("gradle/libraries.versions.toml"))
        }
    }
}

rootProject.name = "simple-mocker"

includeBuild("build_logic")

include(":core:aidl")
include(":core:component_holder")
include(":core:database")
include(":core:design")
// include(":core:view_model_factory")

include(":core:infrastructure:view:base")
include(":core:infrastructure:view:plain_adapter")

include(":feature:request_list_new:api")
include(":feature:request_list_new:impl")

// include(":feature:request_list:api")
// include(":feature:request_list:impl")
//
// include(":app:request_controller")
//
// include(":service")
// include(":ui")
// include(":shared")
// include(":okhttp")
