plugins {
    alias(libs.plugins.droidcon.application)

    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.droidcon.droidynote"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.droidcon.droidynote"
        minSdk = 24
        targetSdk = 35
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":shared:ui"))
    implementation(project(":features:noteslist"))
    implementation(project(":features:notedetail"))
    implementation(project(":data"))



    //-------- Hilt dependencies --------
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    //---------- Kotlinx Serialization --------
    implementation(libs.kotlinx.serialization.json)

    //---------- Coroutines -------------------
    implementation(libs.kotlinx.coroutines.test)
}