plugins {
    alias(libs.plugins.droidcon.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.droidcon.library.compose)
}

android {
    namespace = "com.droidcon.droidynote.shared.ui"
}

dependencies {
    implementation(project(":domain"))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}