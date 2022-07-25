pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

enableFeaturePreview("VERSION_CATALOGS")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

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

include(":core:infrastructure:view:base")
include(":core:infrastructure:view:navigation_factory")
include(":core:infrastructure:view:view_model_factory")
include(":core:infrastructure:view:plain_adapter")
include(":core:infrastructure:di:dependency_container")
include(":core:design")
include(":core:datasource:database")
include(":core:aidl")

include(":feature:request_list:api")
include(":feature:request_list:impl")

include(":app:request_controller")

include(":service")
include(":ui")
include(":shared")
include(":okhttp")
