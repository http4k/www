repositories {
    maven {
        url = uri("https://maven.http4k.org")
        credentials {
            username = project.findProperty("http4kMavenUser") as String
            password = project.findProperty("http4kMavenPassword") as String
        }
    }
}
