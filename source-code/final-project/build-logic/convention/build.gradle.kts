import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.droidcon.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

dependencies {
    compileOnly(libs.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("library") {
            id = "com.droidcon.library"
            implementationClass = "LibraryConventionPlugin"
        }

        register("libraryCompose") {
            id = "com.droidcon.library.compose"
            implementationClass = "LibraryComposeConventionPlugin"
        }

        register("application") {
            id = "com.droidcon.application"
            implementationClass = "ApplicationConventionPlugin"
        }
    }
}