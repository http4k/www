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
    testApi(platform("org.http4k:http4k-bom:${project.properties["http4k_version"]}"))
    testApi(platform("dev.forkhandles:forkhandles-bom:_"))
    testApi(platform("org.junit:junit-bom:_"))

    testApi("org.http4k:http4k-api-openapi")
    testApi("org.http4k:http4k-api-cloudevents")
    testApi("org.http4k:http4k-api-graphql")
    testApi("org.http4k:http4k-api-jsonrpc")
    testApi("org.http4k:http4k-api-jsonschema")
    testApi("org.http4k:http4k-api-ui-redoc")
    testApi("org.http4k:http4k-api-ui-swagger")

    testApi("org.http4k:http4k-client-apache")
    testApi("org.http4k:http4k-client-apache-async")
    testApi("org.http4k:http4k-client-apache4")
    testApi("org.http4k:http4k-client-apache4-async")
    testApi("org.http4k:http4k-client-fuel")
    testApi("org.http4k:http4k-client-helidon")
    testApi("org.http4k:http4k-client-jetty")
    testApi("org.http4k:http4k-client-okhttp")
    testApi("org.http4k:http4k-client-websocket")

    testApi("org.http4k:http4k-config")
    testApi("org.http4k:http4k-core")

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

    testApi("org.http4k:http4k-incubator")

    testApi("org.http4k.pro:http4k-ai-mcp-sdk")
    testApi("org.http4k:http4k-ai-llm")
    testApi("org.http4k:http4k-ai-llm-openai")
    testApi("org.http4k:http4k-ai-llm-anthropic")

    testApi("org.http4k:http4k-multipart")

    testApi("org.http4k:http4k-ops-failsafe")
    testApi("org.http4k:http4k-ops-micrometer")
    testApi("org.http4k:http4k-ops-resilience4j")
    testApi("org.http4k:http4k-ops-opentelemetry")

    testApi("org.http4k:http4k-platform-aws")
    testApi("org.http4k:http4k-platform-azure")
    testApi("org.http4k:http4k-platform-gcp")
    testApi("org.http4k:http4k-platform-k8s")

    testApi("org.http4k:http4k-realtime-core")

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

    testApi("org.http4k:http4k-web-htmx")
    testApi("org.http4k:http4k-web-datastar")

    testApi("org.http4k:http4k-webhook")

    testApi("org.http4k:http4k-connect-ai-anthropic")
    testApi("org.http4k:http4k-connect-ai-anthropic-fake")
    testApi("org.http4k:http4k-connect-ai-azure")
    testApi("org.http4k:http4k-connect-ai-azure-fake")
    testApi("org.http4k:http4k-connect-ai-langchain")
    testApi("org.http4k:http4k-connect-ai-lmstudio")
    testApi("org.http4k:http4k-connect-ai-lmstudio-fake")
    testApi("org.http4k:http4k-connect-ai-ollama")
    testApi("org.http4k:http4k-connect-ai-ollama-fake")
    testApi("org.http4k:http4k-connect-ai-openai")
    testApi("org.http4k:http4k-connect-ai-openai-fake")
    testApi("org.http4k:http4k-connect-amazon-apprunner")
    testApi("org.http4k:http4k-connect-amazon-apprunner-fake")
    testApi("org.http4k:http4k-connect-amazon-cloudfront")
    testApi("org.http4k:http4k-connect-amazon-cloudfront-fake")
    testApi("org.http4k:http4k-connect-amazon-cloudwatchlogs")
    testApi("org.http4k:http4k-connect-amazon-cloudwatchlogs-fake")
    testApi("org.http4k:http4k-connect-amazon-cognito")
    testApi("org.http4k:http4k-connect-amazon-cognito-fake")
    testApi("org.http4k:http4k-connect-amazon-containercredentials")
    testApi("org.http4k:http4k-connect-amazon-containercredentials-fake")
    testApi("org.http4k:http4k-connect-amazon-core")
    testApi("org.http4k:http4k-connect-amazon-dynamodb")
    testApi("org.http4k:http4k-connect-amazon-dynamodb-fake")
    testApi("org.http4k:http4k-connect-amazon-eventbridge")
    testApi("org.http4k:http4k-connect-amazon-eventbridge-fake")
    testApi("org.http4k:http4k-connect-amazon-evidently")
    testApi("org.http4k:http4k-connect-amazon-evidently-fake")
    testApi("org.http4k:http4k-connect-amazon-firehose")
    testApi("org.http4k:http4k-connect-amazon-firehose-fake")
    testApi("org.http4k:http4k-connect-amazon-iamidentitycenter")
    testApi("org.http4k:http4k-connect-amazon-iamidentitycenter-fake")
    testApi("org.http4k:http4k-connect-amazon-instancemetadata")
    testApi("org.http4k:http4k-connect-amazon-instancemetadata-fake")
    testApi("org.http4k:http4k-connect-amazon-kms")
    testApi("org.http4k:http4k-connect-amazon-kms-fake")
    testApi("org.http4k:http4k-connect-amazon-lambda")
    testApi("org.http4k:http4k-connect-amazon-lambda-fake")
    testApi("org.http4k:http4k-connect-amazon-s3")
    testApi("org.http4k:http4k-connect-amazon-s3-fake")
    testApi("org.http4k:http4k-connect-amazon-secretsmanager")
    testApi("org.http4k:http4k-connect-amazon-secretsmanager-fake")
    testApi("org.http4k:http4k-connect-amazon-ses")
    testApi("org.http4k:http4k-connect-amazon-ses-fake")
    testApi("org.http4k:http4k-connect-amazon-sns")
    testApi("org.http4k:http4k-connect-amazon-sns-fake")
    testApi("org.http4k:http4k-connect-amazon-sqs")
    testApi("org.http4k:http4k-connect-amazon-sqs-fake")
    testApi("org.http4k:http4k-connect-amazon-sts")
    testApi("org.http4k:http4k-connect-amazon-sts-fake")
    testApi("org.http4k:http4k-connect-amazon-systemsmanager")
    testApi("org.http4k:http4k-connect-amazon-systemsmanager-fake")
    testApi("org.http4k:http4k-connect-core")
    testApi("org.http4k:http4k-connect-core-fake")
    testApi("org.http4k:http4k-connect-github")
    testApi("org.http4k:http4k-connect-github-fake")
    testApi("org.http4k:http4k-connect-gitlab")
    testApi("org.http4k:http4k-connect-gitlab-fake")
    testApi("org.http4k:http4k-connect-google-analytics-core")
    testApi("org.http4k:http4k-connect-google-analytics-ga4")
    testApi("org.http4k:http4k-connect-google-analytics-ga4-fake")
    testApi("org.http4k:http4k-connect-google-analytics-ua")
    testApi("org.http4k:http4k-connect-google-analytics-ua-fake")
    testApi("org.http4k:http4k-connect-kafka-rest")
    testApi("org.http4k:http4k-connect-kafka-rest-fake")
    testApi("org.http4k:http4k-connect-kafka-schemaregistry")
    testApi("org.http4k:http4k-connect-kafka-schemaregistry-fake")
    testApi("org.http4k:http4k-connect-ksp-generator")
    testApi("org.http4k:http4k-connect-mattermost")
    testApi("org.http4k:http4k-connect-mattermost-fake")
    testApi("org.http4k:http4k-connect-storage-core")
    testApi("org.http4k:http4k-connect-storage-http")
    testApi("org.http4k:http4k-connect-storage-jdbc")
    testApi("org.http4k:http4k-connect-storage-redis")
    testApi("org.http4k:http4k-connect-storage-s3")

    testApi("org.http4k.pro:http4k-tools-hotreload")

    testApi("org.junit.platform:junit-platform-launcher")
    testApi("org.junit.jupiter:junit-jupiter-api")
    testApi("org.junit.jupiter:junit-jupiter-engine")

    testApi("javax.servlet:javax.servlet-api:_")
    testApi("jakarta.servlet:jakarta.servlet-api:_")

    testApi("dev.zacsweers.moshix:moshi-metadata-reflect:_")
    testApi("se.ansman.kotshi:api:_")
//        kspTest("se.ansman.kotshi:compiler:_")

    testApi("io.opentelemetry:opentelemetry-sdk-testing:_")

    testApi("dev.forkhandles:mock4k")

    testApi("software.amazon.awssdk:s3:_")
    testApi("com.azure:azure-search-documents:_") {
        exclude(group = "io.netty", module = "netty-transport-native-kqueue")
    }

    testApi("com.google.http-client:google-http-client:_")

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
