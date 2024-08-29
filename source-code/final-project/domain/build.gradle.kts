plugins {
    alias(libs.plugins.droidcon.library)
}

android {
    namespace = "com.droidcon.droidynote.domain"
}

dependencies {
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}