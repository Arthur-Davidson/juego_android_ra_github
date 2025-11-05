plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    // Para integrar KSP
    alias(libs.plugins.kotlin.ksp)

    // Para integrar en el proyecto Hilt
    id("com.google.dagger.hilt.android")

    // Plugins para parcelizar

    alias(libs.plugins.jetbrainsKotlinSerialization)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "mx.uacj.juegora"
    compileSdk = 35

    defaultConfig {
        applicationId = "mx.uacj.juegora"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    // Para integrar Dagger Hilt y sus herramientas necesarias
    implementation(libs.hilt.android)
    implementation(libs.hilt.viewmodel)
    ksp(libs.hilt.android.compilador)

    val androidxhiltCompiler = "1.2.0" // Cambiamos a 1.2.0
    implementation("androidx.hilt:hilt-work:${androidxhiltCompiler}")
    ksp("androidx.hilt:hilt-compiler:${androidxhiltCompiler}")

    // Dependencia para Hilt y evitar errores extraños
    implementation("androidx.work:work-runtime-ktx:2.7.0")

    // Para instalar Retrofit
    implementation(libs.okhttp)
    implementation(libs.retrofit)
    implementation(libs.gson.converter)
    implementation(libs.login.interceptor)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // Implementa las librerias de Navigation Compose
    implementation(libs.androidx.navigation.compose.android)

    //Implementa las librerias de Kotlinx para parcelizar (o utilizar JSON)
    implementation(libs.kotlinx.serialization.json)

    // Libreria para gestionar el tema de permisos del GPS
    implementation("com.google.android.gms:play-services-location:21.3.0")
    implementation("com.google.accompanist:accompanist-permissions:0.35.0-alpha") // permisos con cualquier cosa

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}