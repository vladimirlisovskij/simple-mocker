allprojects {
    group = "com.github.vladimirlisovskij.simple-mocker"
    version = "1.0-alpha1"
}

buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }

    dependencies {
        classpath(libs.gradle.plugin.buildtools)
    }
}