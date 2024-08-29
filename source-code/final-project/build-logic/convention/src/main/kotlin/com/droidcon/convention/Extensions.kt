package com.droidcon.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

val Project.libs
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

internal fun Project.configureCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        buildFeatures {
            compose = true
        }

        dependencies {

            val composeBom = libs.findLibrary("androidx.compose.bom").get()
            add("implementation", platform(composeBom))
            add("implementation", libs.findLibrary("androidx.lifecycle.runtime.compose").get())
            add("implementation", libs.findLibrary("androidx.ui").get())
            add("implementation", libs.findLibrary("androidx.ui.graphics").get())
            add("implementation", libs.findLibrary("androidx.ui.tooling.preview").get())
            add("debugImplementation", libs.findLibrary("androidx.ui.tooling").get())
            add("debugImplementation", libs.findLibrary("androidx.ui.test.manifest").get())
            add("implementation", libs.findLibrary("androidx.material3").get())
            add("androidTestImplementation", platform(composeBom))
            add("androidTestImplementation", libs.findLibrary("androidx.ui.test.junit4").get())
        }
    }
}