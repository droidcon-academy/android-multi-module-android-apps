plugins {
    alias(libs.plugins.droidcon.library)
}

android {
    namespace = "com.droidcon.droidynote.shared.testing"
}

dependencies {
    implementation(project(":domain"))

    api(libs.junit)
    api(libs.androidx.junit)
    api(libs.androidx.espresso.core)
    //---------- Coroutines -------------------
    api(libs.kotlinx.coroutines.test)
}