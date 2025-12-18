import org.gradle.api.JavaVersion.VERSION_21

plugins {
    alias(libs.plugins.typeflows)
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ksp)
    alias(libs.plugins.version.catalog.update)
    alias(libs.plugins.powerAssert)
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
    mavenLocal()
    mavenCentral()
}

tasks {
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

    typeflowsApi(libs.typeflows.github)
    typeflowsApi(libs.typeflows.github.marketplace)
    typeflowsApi(libs.http4k.standards)

    testImplementation(platform("org.http4k:http4k-bom:${project.properties["http4k_version"]}"))
    testImplementation(platform(libs.forkhandles.bom))
    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.bundles.http4k.api)
    testImplementation(libs.bundles.http4k.clients)
    testImplementation(libs.http4k.config)
    testImplementation(libs.http4k.core)
    testImplementation(libs.bundles.http4k.formats)
    testImplementation(libs.http4k.incubator)
    testImplementation(libs.bundles.http4k.ai)
    testImplementation(libs.http4k.multipart)
    testImplementation(libs.bundles.http4k.ops)
    testImplementation(libs.bundles.http4k.platform)
    testImplementation(libs.http4k.realtime.core)
    testImplementation(libs.bundles.http4k.security)
    testImplementation(libs.bundles.http4k.servers)
    testImplementation(libs.bundles.http4k.serverless)
    testImplementation(libs.bundles.http4k.template)
    testImplementation(libs.bundles.http4k.testing)
    testImplementation(libs.bundles.http4k.web)
    testImplementation(libs.http4k.webhook)
    testImplementation(libs.bundles.http4k.connect.ai)
    testImplementation(libs.bundles.http4k.connect.amazon)
    testImplementation(libs.bundles.http4k.connect.other)
    testImplementation(libs.bundles.http4k.connect.storage)

    testImplementation(libs.http4k.tools.hotreload)
    testImplementation(libs.bundles.junit)
    testImplementation(libs.javax.servlet.api)
    testImplementation(libs.jakarta.servlet.api)
    testImplementation(libs.moshi.metadata.reflect)
    testImplementation(libs.kotshi.api)
    kspTest(libs.kotshi.compiler)
    testImplementation(libs.opentelemetry.sdk.testing)
    testImplementation(libs.mock4k)
    testImplementation(libs.amazon.s3)
    testImplementation(libs.azure.search.documents) {
        exclude(group = "io.netty", module = "netty-transport-native-kqueue")
    }
    testImplementation(libs.google.http.client)
    testImplementation(libs.opentelemetry.aws.xray.propagator)
    testImplementation(libs.graphql.kotlin.schema.generator)
    testImplementation(libs.aws.lambda.java.events)
    testImplementation(libs.redis.jedis)
}

sourceSets {
    test {
        kotlin.srcDir("$projectDir/src/website")
        resources.srcDir("$projectDir/src/website")
    }
}
