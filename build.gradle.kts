import org.gradle.api.JavaVersion.VERSION_21

plugins {
    kotlin("jvm")
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

buildscript {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

apply(plugin = "java")
apply(plugin = "kotlin")

repositories {
    mavenCentral()
}

tasks {
//    withType<KotlinJvmCompile>().configureEach {
//        compilerOptions {
//            jvmTarget.set(JVM_21)
//        }
//    }

    java {
        sourceCompatibility = VERSION_21
        targetCompatibility = VERSION_21
    }

    withType<Test> {
        useJUnitPlatform()
        jvmArgs = listOf("--enable-preview")
    }
}

dependencies {
    testApi(platform("org.http4k:http4k-bom:${project.properties["http4k_version"]}"))
    testApi(platform("org.http4k:http4k-connect-bom:${project.properties["http4k_connect_version"]}"))
    testApi(platform("org.junit:junit-bom:_"))
}

sourceSets {
    test {
        kotlin.srcDir("$projectDir/src/docs")
        resources.srcDir("$projectDir/src/docs")

        kotlin.srcDir("$projectDir/src/website")
        resources.srcDir("$projectDir/src/website")
    }
}
