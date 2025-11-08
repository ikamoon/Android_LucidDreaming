plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("org.jetbrains.kotlin.plugin.serialization") version "2.2.20"

//    kotlin("plugin.serialization") version "2.2.20"
}

android {
    namespace = "com.aura_weavers.luciddreaming"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.example.luciddreaming"
        minSdk = 24
        targetSdk = 36
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
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material3.adaptive.navigation.suite)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.coil.compose)

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0") // Use a version compatible with your Kotlin version

    // Supabase Bill of Materials (BOM)
    val supabase_version = "3.2.6" // Use the latest version
    implementation(platform("io.github.jan-tennert.supabase:bom:$supabase_version"))

    // Now, you can add the modules you need without specifying the version
    implementation("io.github.jan-tennert.supabase:auth-kt")
    implementation("io.github.jan-tennert.supabase:postgrest-kt")
    // Add other modules as needed, e.g., realtime-kt, storage-kt

    // supabase-kt requires a Ktor client engine
    // Choose the one that fits your project (e.g., cio, android, okhttp)
    // Ensure the Ktor version is compatible with your supabase-kt version [1]
    val ktor_version = "3.0.0-rc-1" // Check for compatible Ktor versions
    implementation("io.ktor:ktor-client-cio:$ktor_version")

//    implementation(platform(libs.supabase.bom))
//    implementation(libs.github.postgrest.kt)
//    implementation(libs.supabase.auth.kt)
//    implementation(libs.supabase.realtime.kt)
    implementation(libs.supabase.functions.kt)
//    implementation(libs.ktor.client.android.v300rc1)


//    implementation(libs.ktor.client.engine.z)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}