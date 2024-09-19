// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
}
// Adding hilt dependency
buildscript {
    repositories{
        google()
        mavenCentral()

    }
   dependencies{
       classpath("com.android.tools.build:gradle:8.0.2") // Ensure this matches your Android Gradle plugin version
       classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.0") // Match Kotlin version with your project
       classpath("com.google.dagger:hilt-android-gradle-plugin:2.44") // Hilt for dependency injection
   }
}

