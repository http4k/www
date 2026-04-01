pluginManagement {
    repositories {
        maven {
            url = uri("https://maven.http4k.org")
            credentials {
                username = providers.gradleProperty("http4kMavenUser").orNull
                password = providers.gradleProperty("http4kMavenPassword").orNull
            }
        }
        gradlePluginPortal()
    }
}
