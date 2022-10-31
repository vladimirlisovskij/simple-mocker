package internal

import org.gradle.api.JavaVersion

internal object Constants {
    const val MIN_SDK = 21
    const val COMPILE_SDK = 32
    const val TARGET_SDK = 32

    const val VERSION_CATALOG_NAME = "libs"

    const val COMPOSE_COMPILER_VERSION = "1.1.1"

    val JAVA_VERSION = JavaVersion.VERSION_1_8
}