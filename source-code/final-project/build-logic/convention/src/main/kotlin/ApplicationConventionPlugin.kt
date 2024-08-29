import com.android.build.api.dsl.ApplicationExtension
import com.droidcon.convention.configureCompose
import com.droidcon.convention.libs
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

class ApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            extensions.configure<ApplicationExtension> {
                compileSdk = 35

                defaultConfig {
                    targetSdk = 35
                    minSdk = 21
                }

                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_17
                    targetCompatibility = JavaVersion.VERSION_17
                }

                extensions.configure<JavaPluginExtension> {
                    sourceCompatibility = JavaVersion.VERSION_17
                    targetCompatibility = JavaVersion.VERSION_17
                }

                configure<KotlinAndroidProjectExtension> {
                    with(compilerOptions) {
                        jvmTarget.set(JvmTarget.JVM_17)
                    }
                }

                configureCompose(this)
                dependencies {
                    add("implementation", libs.findLibrary("androidx.core.ktx").get())
                    add("implementation", libs.findLibrary("androidx.lifecycle.runtime.ktx").get())
                    add("implementation", libs.findLibrary("androidx.activity.compose").get())
                    add("testImplementation", libs.findLibrary("junit").get())
                    add("androidTestImplementation", libs.findLibrary("androidx.junit").get())
                    add("androidTestImplementation", libs.findLibrary("androidx.espresso.core").get())
                    add("implementation", libs.findLibrary("androidx.navigation.compose").get())
                }
            }
        }
    }

}