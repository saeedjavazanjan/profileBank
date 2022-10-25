
apply{
    from("$rootDir/android-library-build.gradle")

}

dependencies{

    "implementation"(project(Modules.core))
    "implementation"(project(Modules.profileBankDataSource))
    "implementation"(project(Modules.profileBankDomain))
    "implementation"(project(Modules.profileBankInteractors))

    "implementation"(Coil.coil)


    "implementation"(AndroidX.coreKtx)
    "implementation"(AndroidX.appCompat)
    "implementation"(AndroidX.lifecycleVmKtx)
    "implementation"(Navigation.navigation_fragment)
    "implementation"(Navigation.navigation_ui)
    "implementation"(Google.material)
    "implementation"("androidx.appcompat:appcompat:1.5.1")
    "implementation"("com.google.android.material:material:1.4.0")
    "implementation"("androidx.constraintlayout:constraintlayout:2.1.4")
}