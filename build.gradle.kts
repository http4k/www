import org.gradle.api.JavaVersion.VERSION_21

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ksp)
    alias(libs.plugins.version.catalog.update)
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
    testApi(platform(libs.forkhandles.bom))
    testApi(platform(libs.junit.bom))

    testApi(libs.bundles.http4k.api)

    testApi(libs.bundles.http4k.clients)

    testApi(libs.http4k.config)
    testApi(libs.http4k.core)

    testApi(libs.bundles.http4k.formats)

    testApi(libs.http4k.incubator)

    testApi(libs.http4k.ai.mcp.sdk)
    testApi(libs.http4k.ai.langchain4j)
    testApi(libs.http4k.ai.llm.core)
    testApi(libs.http4k.ai.llm.anthropic)
    testApi(libs.http4k.ai.llm.azure)
    testApi(libs.http4k.ai.llm.gemini)
    testApi(libs.http4k.ai.llm.github)
    testApi(libs.http4k.ai.llm.openai)

    testApi(libs.http4k.multipart)

    testApi(libs.http4k.ops.failsafe)
    testApi(libs.http4k.ops.micrometer)
    testApi(libs.http4k.ops.resilience4j)
    testApi(libs.http4k.ops.opentelemetry)

    testApi(libs.http4k.platform.aws)
    testApi(libs.http4k.platform.azure)
    testApi(libs.http4k.platform.gcp)
    testApi(libs.http4k.platform.k8s)

    testApi(libs.http4k.realtime.core)

    testApi(libs.http4k.security.core)
    testApi(libs.http4k.security.digest)
    testApi(libs.http4k.security.oauth)

    testApi(libs.bundles.http4k.servers)

    testApi(libs.http4k.serverless.alibaba)
    testApi(libs.http4k.serverless.azure)
    testApi(libs.http4k.serverless.core)
    testApi(libs.http4k.serverless.gcf)
    testApi(libs.http4k.serverless.lambda)
    testApi(libs.http4k.serverless.lambda.runtime)
    testApi(libs.http4k.serverless.openwhisk)
    testApi(libs.http4k.serverless.tencent)

    testApi(libs.http4k.template.core)
    testApi(libs.http4k.template.freemarker)
    testApi(libs.http4k.template.handlebars)
    testApi(libs.http4k.template.jte)
    testApi(libs.http4k.template.pebble)
    testApi(libs.http4k.template.pug4j)
    testApi(libs.http4k.template.rocker)
    testApi(libs.http4k.template.thymeleaf)

    testApi(libs.bundles.http4k.testing)

    testApi(libs.http4k.web.htmx)
    testApi(libs.http4k.web.datastar)

    testApi(libs.http4k.webhook)

    testApi(libs.http4k.connect.ai.anthropic)
    testApi(libs.http4k.connect.ai.anthropic.fake)
    testApi(libs.http4k.connect.ai.azure)
    testApi(libs.http4k.connect.ai.azure.fake)
    testApi(libs.http4k.connect.ai.lmstudio)
    testApi(libs.http4k.connect.ai.lmstudio.fake)
    testApi(libs.http4k.connect.ai.ollama)
    testApi(libs.http4k.connect.ai.ollama.fake)
    testApi(libs.http4k.connect.ai.openai)
    testApi(libs.http4k.connect.ai.openai.fake)
    testApi(libs.http4k.connect.amazon.apprunner)
    testApi(libs.http4k.connect.amazon.apprunner.fake)
    testApi(libs.http4k.connect.amazon.cloudfront)
    testApi(libs.http4k.connect.amazon.cloudfront.fake)
    testApi(libs.http4k.connect.amazon.cloudwatch)
    testApi(libs.http4k.connect.amazon.cloudwatch.fake)
    testApi(libs.http4k.connect.amazon.cloudwatchlogs)
    testApi(libs.http4k.connect.amazon.cloudwatchlogs.fake)
    testApi(libs.http4k.connect.amazon.cognito)
    testApi(libs.http4k.connect.amazon.cognito.fake)
    testApi(libs.http4k.connect.amazon.containercredentials)
    testApi(libs.http4k.connect.amazon.containercredentials.fake)
    testApi(libs.http4k.connect.amazon.core)
    testApi(libs.http4k.connect.amazon.dynamodb)
    testApi(libs.http4k.connect.amazon.dynamodb.fake)
    testApi(libs.http4k.connect.amazon.eventbridge)
    testApi(libs.http4k.connect.amazon.eventbridge.fake)
    testApi(libs.http4k.connect.amazon.evidently)
    testApi(libs.http4k.connect.amazon.evidently.fake)
    testApi(libs.http4k.connect.amazon.firehose)
    testApi(libs.http4k.connect.amazon.firehose.fake)
    testApi(libs.http4k.connect.amazon.iamidentitycenter)
    testApi(libs.http4k.connect.amazon.iamidentitycenter.fake)
    testApi(libs.http4k.connect.amazon.instancemetadata)
    testApi(libs.http4k.connect.amazon.instancemetadata.fake)
    testApi(libs.http4k.connect.amazon.kms)
    testApi(libs.http4k.connect.amazon.kms.fake)
    testApi(libs.http4k.connect.amazon.lambda)
    testApi(libs.http4k.connect.amazon.lambda.fake)
    testApi(libs.http4k.connect.amazon.s3)
    testApi(libs.http4k.connect.amazon.s3.fake)
    testApi(libs.http4k.connect.amazon.secretsmanager)
    testApi(libs.http4k.connect.amazon.secretsmanager.fake)
    testApi(libs.http4k.connect.amazon.ses)
    testApi(libs.http4k.connect.amazon.ses.fake)
    testApi(libs.http4k.connect.amazon.sns)
    testApi(libs.http4k.connect.amazon.sns.fake)
    testApi(libs.http4k.connect.amazon.sqs)
    testApi(libs.http4k.connect.amazon.sqs.fake)
    testApi(libs.http4k.connect.amazon.sts)
    testApi(libs.http4k.connect.amazon.sts.fake)
    testApi(libs.http4k.connect.amazon.systemsmanager)
    testApi(libs.http4k.connect.amazon.systemsmanager.fake)
    testApi(libs.http4k.connect.core)
    testApi(libs.http4k.connect.core.fake)
    testApi(libs.http4k.connect.github)
    testApi(libs.http4k.connect.github.fake)
    testApi(libs.http4k.connect.gitlab)
    testApi(libs.http4k.connect.gitlab.fake)
    testApi(libs.http4k.connect.google.analytics.core)
    testApi(libs.http4k.connect.google.analytics.ga4)
    testApi(libs.http4k.connect.google.analytics.ga4.fake)
    testApi(libs.http4k.connect.google.analytics.ua)
    testApi(libs.http4k.connect.google.analytics.ua.fake)
    testApi(libs.http4k.connect.kafka.rest)
    testApi(libs.http4k.connect.kafka.rest.fake)
    testApi(libs.http4k.connect.kafka.schemaregistry)
    testApi(libs.http4k.connect.kafka.schemaregistry.fake)
    testApi(libs.http4k.connect.ksp.generator)
    testApi(libs.http4k.connect.mattermost)
    testApi(libs.http4k.connect.mattermost.fake)
    testApi(libs.http4k.connect.storage.core)
    testApi(libs.http4k.connect.storage.http)
    testApi(libs.http4k.connect.storage.jdbc)
    testApi(libs.http4k.connect.storage.redis)
    testApi(libs.http4k.connect.storage.s3)


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
}

sourceSets {
    test {
        kotlin.srcDir("$projectDir/src/website")
        resources.srcDir("$projectDir/src/website")
    }
}
