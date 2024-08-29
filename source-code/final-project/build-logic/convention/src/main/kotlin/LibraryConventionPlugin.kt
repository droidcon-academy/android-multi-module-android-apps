import com.android.build.gradle.LibraryExtension
import com.droidcon.convention.libs
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class LibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                compileSdk = 35

                defaultConfig {
                    minSdk = 21
                }
                defaultConfig.targetSdk = 35

                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_17
                    targetCompatibility = JavaVersion.VERSION_17
                }
                defaultConfig.testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

                dependencies {
                    add("implementation", libs.findLibrary("androidx.core.ktx").get())
                }
            }
        }
    }
}