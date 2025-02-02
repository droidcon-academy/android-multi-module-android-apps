plugins {
    alias(libs.plugins.droidcon.library)
    alias(libs.plugins.droidcon.library.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.droidcon.droidynote.features.notelist"
}

dependencies {
    implementation(project(":shared:ui"))
    implementation(project(":domain"))

    testImplementation(project(":shared:testing"))
    androidTestImplementation(project(":shared:testing"))

    implementation(libs.androidx.navigation.compose)

    implementation(libs.kotlinx.serialization.json)

    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)
}
