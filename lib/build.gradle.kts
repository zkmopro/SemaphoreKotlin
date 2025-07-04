/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Kotlin library project to get you started.
 * For more details on building Java & JVM projects, please refer to https://docs.gradle.org/8.13/userguide/building_java_projects.html in the Gradle documentation.
 */

plugins {
    // Apply the Kotlin Android plugin
    id("org.jetbrains.kotlin.android") version "1.9.22"
    
    // Add Android plugin for instrumented tests
    id("com.android.library") version "8.2.2"
}

group = "com.github.zkmopro"    
version = "0.2.0" 

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
    google()
}

configurations.all {
    resolutionStrategy {
        // Force specific versions of dependencies to avoid conflicts
        force("androidx.test:runner:1.5.2")
        force("androidx.test:core:1.5.0")
        force("androidx.test.ext:junit:1.1.5")
    }
}

dependencies {
    // Use the Kotlin JUnit 5 integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")

    // Use the JUnit 5 integration.
    testImplementation(libs.junit.jupiter.engine)

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // This dependency is exported to consumers, that is to say found on their compile classpath.
    api(libs.commons.math3)

    // This dependency is used internally, and not exposed to consumers on their own compile classpath.
    implementation(libs.guava)

    // Uniffi
    implementation("net.java.dev.jna:jna:5.13.0@aar")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

    // Android test dependencies
    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.test:rules:1.5.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    
    // Add the AndroidX Test Runner as a direct dependency
    androidTestImplementation("androidx.test:core:1.5.0")
    androidTestImplementation("androidx.test:core-ktx:1.5.0")
    androidTestImplementation("androidx.test.ext:junit-ktx:1.1.5")
}

// Configure Android test options
android {
    namespace = "com.github.zkmopro"
    compileSdk = 35
    
    defaultConfig {
        minSdk = 33
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        
        // // Add JNA native library support
        // ndk {
        //     abiFilters += listOf("arm64-v8a", "armeabi-v7a", "x86", "x86_64")
        // }
    }
    
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    
    kotlinOptions {
        jvmTarget = "1.8"
    }
    
    testOptions {
        targetSdk = 35
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }
    
    lint {
        targetSdk = 35
    }
    
    // Add packaging options to include native libraries
    packaging {
        jniLibs {
            useLegacyPackaging = true
        }
    }
}
