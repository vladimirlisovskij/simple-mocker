plugins {
    id("maven-publish")
    id("projectPlugins.config.android.library.base")
}

android {
    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
}

dependencies {
    implementation(projects.core.aidl)

    implementation(libs.okhttp)
}

publishing {
    publications {
        create<MavenPublication>("release") {
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}
