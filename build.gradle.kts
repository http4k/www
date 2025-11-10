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

    testApi(platform("org.http4k:http4k-bom:${project.properties["http4k_version"]}"))
    testApi(platform(libs.forkhandles.bom))
    testApi(platform(libs.junit.bom))
    testApi(libs.bundles.http4k.api)
    testApi(libs.bundles.http4k.clients)
    testApi(libs.http4k.config)
    testApi(libs.http4k.core)
    testApi(libs.bundles.http4k.formats)
    testApi(libs.http4k.incubator)
    testApi(libs.bundles.http4k.ai)
    testApi(libs.http4k.multipart)
    testApi(libs.bundles.http4k.ops)
    testApi(libs.bundles.http4k.platform)
    testApi(libs.http4k.realtime.core)
    testApi(libs.bundles.http4k.security)
    testApi(libs.bundles.http4k.servers)
    testApi(libs.bundles.http4k.serverless)
    testApi(libs.bundles.http4k.template)
    testApi(libs.bundles.http4k.testing)
    testApi(libs.bundles.http4k.web)
    testApi(libs.http4k.webhook)
    testApi(libs.bundles.http4k.connect.ai)
    testApi(libs.bundles.http4k.connect.amazon)
    testApi(libs.bundles.http4k.connect.other)
    testApi(libs.bundles.http4k.connect.storage)

    testApi(libs.http4k.tools.hotreload)
    testApi(libs.bundles.junit)
    testApi(libs.javax.servlet.api)
    testApi(libs.jakarta.servlet.api)
    testApi(libs.moshi.metadata.reflect)
    testApi(libs.kotshi.api)
    kspTest(libs.kotshi.compiler)
    testApi(libs.opentelemetry.sdk.testing)
    testApi(libs.mock4k)
    testApi(libs.amazon.s3)
    testApi(libs.azure.search.documents) {
        exclude(group = "io.netty", module = "netty-transport-native-kqueue")
    }
    testApi(libs.google.http.client)
    testApi(libs.opentelemetry.aws.xray.propagator)
    testApi(libs.graphql.kotlin.schema.generator)
    testApi(libs.aws.lambda.java.events)
    testApi(libs.redis.jedis)
}

sourceSets {
    test {
        kotlin.srcDir("$projectDir/src/website")
        resources.srcDir("$projectDir/src/website")
    }
}
