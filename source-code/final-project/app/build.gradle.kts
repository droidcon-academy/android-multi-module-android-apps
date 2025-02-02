plugins {
    alias(libs.plugins.droidcon.application)

    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.droidcon.droidynote"

    defaultConfig {
        applicationId = "com.droidcon.droidynote"

        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }


    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":data"))
    implementation(project(":shared:ui"))
    implementation(project(":features:notelist"))
    implementation(project(":features:notedetail"))

    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
}