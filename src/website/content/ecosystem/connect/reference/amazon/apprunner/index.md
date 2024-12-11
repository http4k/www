---
category: Reference
type: ecosystem
ecosystem: http4k Connect
title: "AWS: AppRunner"
description: Feature overview of the http4k Connect AWS AppRunner modules
---

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-connect-amazon-apprunner")
    implementation("org.http4k:http4k-connect-amazon-apprunner-fake")
}
```

The AppRunner connector provides the following Actions:

     *  CreateService
     *  DeleteService
     *  ListServices

### Example usage

```kotlin
const val USE_REAL_CLIENT = false

fun main() {
    val deployedLambda = FunctionName("http4kLambda")

    val fakeAppRunner = FakeAppRunner(
    )

    // we can connect to the real service or the fake (drop in replacement)
    val http: HttpHandler = if (USE_REAL_CLIENT) JavaHttpClient() else fakeAppRunner

    // create a client
    val client = AppRunner.Http(Region.of("us-east-1"), { AwsCredentials("accessKeyId", "secretKey") }, http.debug())

    // all operations return a Result monad of the API type
    println(client.listServices())
}
```

The client APIs utilise the `http4k-platform-aws` module for request signing, which means no dependencies on the incredibly fat
Amazon-SDK JARs. This means this integration is perfect for running Serverless Lambdas where binary size is a
performance factor.

### Default Fake port: 62628

To start:

```kotlin
FakeAppRunner().start()
```
