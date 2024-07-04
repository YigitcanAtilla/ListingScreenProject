plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    namespace = "com.yigitcan.listingscreen"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.yigitcan.listingscreen"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    implementation( libs.androidx.lifecycle.viewmodel.ktx)
    implementation( libs.androidx.lifecycle.runtime.ktx)

    implementation( libs.dagger.hilt.android)
    implementation(libs.androidx.fragment)
    kapt (libs.dagger.hilt.android.compiler)
    kapt (libs.androidx.hilt.compiler)
    implementation (libs.androidx.hilt.work)
    implementation (libs.androidx.work.runtime.ktx)

    implementation (libs.retrofit)
    implementation (libs.converter.gson)

    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    implementation(libs.coil)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

}