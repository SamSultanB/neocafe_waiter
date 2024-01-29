plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.neocafe.neocafewaiter"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.neocafe.neocafewaiter"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false //todo: isMinifyEnable = true
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

    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.4")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //navigation dependencies
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.3")

    //mask dependency
    implementation("io.github.vicmikhailau:MaskedEditText:5.0.1")

    //glide
    implementation ("com.github.bumptech.glide:glide:4.12.0")

    //retrofit
    implementation ("com.google.code.gson:gson:2.9.0")
    implementation ("com.squareup.retrofit2:retrofit:2.6.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.6.0")
    implementation ("com.squareup.okhttp3:okhttp:4.2.1")

    //koin
    implementation ("io.insert-koin:koin-core:3.1.2")
    implementation ("io.insert-koin:koin-android:3.1.2")
}