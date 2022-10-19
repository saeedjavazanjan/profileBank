apply{
    from("$rootDir/library-build.gradle")

}
dependencies{
    "implementation"(project(Modules.profileBankDomain))
    "implementation"(Retrofit.core)
    "implementation"(Retrofit.converter)
    "implementation"(Retrofit.okHttpCore)
    "implementation"(Retrofit.okHttpInterceptor)
}