plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id ("dagger.hilt.android.plugin")
    kotlin("kapt")
}

android {
    namespace = "com.example.smarthomeapplication"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.example.smarthomeapplication"
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


    dependencies {
        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.appcompat)
        implementation(libs.material)
        implementation(libs.androidx.activity)
        implementation(libs.androidx.constraintlayout)

        // Testing dependencies
        testImplementation(libs.junit)
        androidTestImplementation(libs.androidx.junit)
        androidTestImplementation(libs.androidx.espresso.core)

        // Moshi for JSON parsing
        implementation("com.squareup.moshi:moshi:1.12.0")
        implementation("com.squareup.moshi:moshi-kotlin:1.12.0")

        // Retrofit and its converters
        implementation("com.squareup.retrofit2:retrofit:2.9.0")
        implementation("com.squareup.retrofit2:converter-gson:2.9.0")
        implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
        implementation("com.squareup.retrofit2:converter-scalars:2.9.0")

        // OkHttp for logging
        implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")

        // Hilt for dependency injection
        implementation("com.google.dagger:hilt-android:2.44")
        kapt("com.google.dagger:hilt-compiler:2.44")

        // RecyclerView
        implementation("androidx.recyclerview:recyclerview:1.3.2")
    }
kapt {
    correctErrorTypes = true
}
}




