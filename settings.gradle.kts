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
        create("lib") {
            from(files("gradle/libs.versions.toml"))
        }
    }
}
rootProject.name = "SimpleMocker"

include(":app")
include(":service")
include(":ui")
include(":shared")
include(":aidl")
include(":okhttp")
