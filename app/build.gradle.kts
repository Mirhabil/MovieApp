plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    id ("kotlin-kapt")
    id("com.google.dagger.hilt.android")

    id("androidx.navigation.safeargs")
    id("kotlin-parcelize")

    id("com.google.gms.google-services")


}


android {
    buildFeatures {
        viewBinding=true
    }
    namespace = "com.example.letterboxd"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.letterboxd"
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
}

dependencies {

    val paging_version = "3.3.0"

    implementation("androidx.paging:paging-runtime:$paging_version")


//RoomDatabase

    implementation ("androidx.room:room-runtime:2.6.1")
    implementation ("androidx.room:room-ktx:2.6.1")
    kapt ("androidx.room:room-compiler:2.6.1")
    //implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:2.6.1")

//ViewModel

    implementation("androidx.fragment:fragment-ktx:1.6.2")


//Retrofit

    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

//Glide

    implementation ("com.github.bumptech.glide:glide:4.11.0")

//Hilt

    implementation("com.google.dagger:hilt-android:2.46.1")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    kapt("com.google.dagger:hilt-android-compiler:2.46.1")

    implementation("com.google.firebase:firebase-firestore-ktx:25.0.0")

    implementation ("androidx.recyclerview:recyclerview:1.3.0")
    implementation ("androidx.paging:paging-runtime-ktx:3.0.1")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")



    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}