
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.homemedicalkit"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.homemedicalkit"
        minSdk = 24
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    implementation("androidx.hilt:hilt-work:1.2.0")
    implementation("androidx.test.ext:junit-ktx:1.1.5")
    val room_version = "2.5.0"
    implementation ("androidx.room:room-ktx:$room_version")
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.2")
    ksp("androidx.room:room-compiler:$room_version")
    implementation ("androidx.compose.ui:ui-text:1.6.4")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation("com.google.dagger:hilt-android:2.49")
    kapt("com.google.dagger:hilt-android-compiler:2.44")

    implementation ("androidx.hilt:hilt-work:1.0.0")
    kapt ("androidx.hilt:hilt-compiler:1.0.0")
    implementation ("androidx.work:work-runtime-ktx:2.7.1")

// Compose dependencies
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    implementation ("androidx.navigation:navigation-compose:2.7.7")
    implementation ("androidx.compose.material:material-icons-extended:1.6.4")
    implementation ("androidx.hilt:hilt-navigation-compose:1.2.0")
    implementation ("androidx.compose.material3:material3:1.2.1")
    implementation("io.coil-kt:coil-compose:2.4.0")

    testImplementation ("junit:junit:4.13.2")
    testImplementation ("org.mockito:mockito-core:4.2.0")
    testImplementation ("org.mockito.kotlin:mockito-kotlin:4.0.0")
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.1")
    testImplementation ("androidx.arch.core:core-testing:2.1.0")
    testImplementation ("com.google.truth:truth:1.1.3")
    testImplementation ("io.mockk:mockk:1.13.1")
}
kapt {
    correctErrorTypes = true
}