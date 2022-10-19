apply{
    from("$rootDir/library-build.gradle")

}
dependencies{
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.profileBankDataSource))
    "implementation"(project(Modules.profileBankDomain))
    "implementation"(Kotlinx.coroutinesCore)
}