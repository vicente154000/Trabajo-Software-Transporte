plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.aplicacion"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.aplicacion"
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
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.12.0"))
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")
    implementation("com.google.code.gson:gson:2.8.8")
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")

    implementation("org.osmdroid:osmdroid-android:6.1.16")
    implementation("org.osmdroid:osmdroid-wms:6.1.16")
    implementation("org.osmdroid:osmdroid-mapsforge:6.1.16") // optional for offline maps
    implementation("com.google.android.gms:play-services-location:21.0.1")
    implementation ("com.github.bumptech.glide:glide:4.14.2");

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.2")

    // Mockito
    testImplementation("org.mockito:mockito-core:5.3.1")
    testImplementation("org.mockito:mockito-junit-jupiter:5.3.1")

    // MockWebServer
    testImplementation("com.squareup.okhttp3:mockwebserver:4.11.0")
}