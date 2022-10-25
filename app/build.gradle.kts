plugins {
    id("com.android.application")
    kotlin("android")

}
android {
    compileSdk = 32
    buildToolsVersion = "30.0.3"
    /* dataBinding{
         android.buildFeatures.dataBinding=true
     }*/
    defaultConfig {

        applicationId = Android.appId
        minSdk = Android.minSdk
        targetSdk = Android.targetSdk
        versionCode = Android.versionCode
        versionName = Android.versionName
        testInstrumentationRunner ="androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures{
        dataBinding = true
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        // useIR = true
    }

    packagingOptions {
        exclude("META-INF/AL2.0")
        exclude("META-INF/LGPL2.1")
    }
}

dependencies{

    implementation(project(Modules.core))
    implementation(project(Modules.profileBankDataSource))
    implementation(project(Modules.profileBankDomain))
    implementation(project(Modules.profileBankInteractors))

      implementation(project(Modules.ui_home))

    implementation(Coil.coil)
/*
    implementation(Accompanist.animations)
*/

    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appCompat)
    implementation(AndroidX.lifecycleVmKtx)
    implementation(Navigation.navigation_fragment)
    implementation(Navigation.navigation_ui)
    implementation(Google.material)
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")


}