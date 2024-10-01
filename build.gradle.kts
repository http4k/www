import org.gradle.api.JavaVersion.VERSION_21

plugins {
    kotlin("jvm")
    id("com.google.devtools.ksp")
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
    testApi(platform("dev.forkhandles:forkhandles-bom:_"))
    testApi(platform("org.junit:junit-bom:_"))


    testApi("org.http4k:http4k-aws")
    testApi("org.http4k:http4k-azure")
    testApi("org.http4k:http4k-client-apache")
    testApi("org.http4k:http4k-client-apache-async")
    testApi("org.http4k:http4k-client-apache4")
    testApi("org.http4k:http4k-client-apache4-async")
    testApi("org.http4k:http4k-client-fuel")
    testApi("org.http4k:http4k-client-helidon")
    testApi("org.http4k:http4k-client-jetty")
    testApi("org.http4k:http4k-client-okhttp")
    testApi("org.http4k:http4k-client-websocket")
    testApi("org.http4k:http4k-cloudevents")
    testApi("org.http4k:http4k-cloudnative")
    testApi("org.http4k:http4k-config")
    testApi("org.http4k:http4k-contract")
    testApi("org.http4k:http4k-contract-jsonschema")
    testApi("org.http4k:http4k-contract-ui-redoc")
    testApi("org.http4k:http4k-contract-ui-swagger")
    testApi("org.http4k:http4k-core")
    testApi("org.http4k:http4k-failsafe")
    testApi("org.http4k:http4k-format-argo")
    testApi("org.http4k:http4k-format-core")
    testApi("org.http4k:http4k-format-dataframe")
    testApi("org.http4k:http4k-format-gson")
    testApi("org.http4k:http4k-format-jackson")
    testApi("org.http4k:http4k-format-jackson-csv")
    testApi("org.http4k:http4k-format-jackson-xml")
    testApi("org.http4k:http4k-format-jackson-yaml")
    testApi("org.http4k:http4k-format-klaxon")
    testApi("org.http4k:http4k-format-kondor-json")
    testApi("org.http4k:http4k-format-kotlinx-serialization")
    testApi("org.http4k:http4k-format-moshi")
    testApi("org.http4k:http4k-format-moshi-yaml")
    testApi("org.http4k:http4k-format-xml")
    testApi("org.http4k:http4k-graphql")
    testApi("org.http4k:http4k-htmx")
    testApi("org.http4k:http4k-incubator")
    testApi("org.http4k:http4k-jsonrpc")
    testApi("org.http4k:http4k-metrics-micrometer")
    testApi("org.http4k:http4k-multipart")
    testApi("org.http4k:http4k-opentelemetry")
    testApi("org.http4k:http4k-realtime-core")
    testApi("org.http4k:http4k-resilience4j")
    testApi("org.http4k:http4k-security-core")
    testApi("org.http4k:http4k-security-digest")
    testApi("org.http4k:http4k-security-oauth")
    testApi("org.http4k:http4k-server-apache")
    testApi("org.http4k:http4k-server-apache4")
    testApi("org.http4k:http4k-server-helidon")
    testApi("org.http4k:http4k-server-jetty")
    testApi("org.http4k:http4k-server-jetty11")
    testApi("org.http4k:http4k-server-ktorcio")
    testApi("org.http4k:http4k-server-ktornetty")
    testApi("org.http4k:http4k-server-netty")
    testApi("org.http4k:http4k-server-ratpack")
    testApi("org.http4k:http4k-server-undertow")
    testApi("org.http4k:http4k-server-websocket")
    testApi("org.http4k:http4k-serverless-alibaba")
    testApi("org.http4k:http4k-serverless-azure")
    testApi("org.http4k:http4k-serverless-core")
    testApi("org.http4k:http4k-serverless-gcf")
    testApi("org.http4k:http4k-serverless-lambda")
    testApi("org.http4k:http4k-serverless-lambda-runtime")
    testApi("org.http4k:http4k-serverless-openwhisk")
    testApi("org.http4k:http4k-serverless-tencent")
    testApi("org.http4k:http4k-template-core")
    testApi("org.http4k:http4k-template-freemarker")
    testApi("org.http4k:http4k-template-handlebars")
    testApi("org.http4k:http4k-template-jte")
    testApi("org.http4k:http4k-template-pebble")
    testApi("org.http4k:http4k-template-pug4j")
    testApi("org.http4k:http4k-template-rocker")
    testApi("org.http4k:http4k-template-thymeleaf")
    testApi("org.http4k:http4k-testing-approval")
    testApi("org.http4k:http4k-testing-chaos")
    testApi("org.http4k:http4k-testing-hamkrest")
    testApi("org.http4k:http4k-testing-kotest")
    testApi("org.http4k:http4k-testing-playwright")
    testApi("org.http4k:http4k-testing-servirtium")
    testApi("org.http4k:http4k-testing-strikt")
    testApi("org.http4k:http4k-testing-tracerbullet")
    testApi("org.http4k:http4k-testing-webdriver")
    testApi("org.http4k:http4k-webhook")

    testApi(Testing.junit.jupiter.engine)
    testApi(Testing.junit.jupiter.api)

    testApi("javax.servlet:javax.servlet-api:_")
    testApi("jakarta.servlet:jakarta.servlet-api:_")

    testApi("dev.zacsweers.moshix:moshi-metadata-reflect:_")
    testApi("se.ansman.kotshi:api:_")
//        kspTest("se.ansman.kotshi:compiler:_")

    testApi("io.opentelemetry:opentelemetry-sdk-testing:_")

    testApi("dev.forkhandles:mock4k")

    testApi("software.amazon.awssdk:s3:_")
    testApi("com.azure:azure-search-documents:_")

    testApi("io.opentelemetry.contrib:opentelemetry-aws-xray-propagator:_")
    testApi("com.expediagroup:graphql-kotlin-schema-generator:_")
    testApi("com.amazonaws:aws-lambda-java-events:_")
}

sourceSets {
    test {
        kotlin.srcDir("$projectDir/src/website")
        resources.srcDir("$projectDir/src/website")
    }
}
