---
category: Reference
type: ecosystem
ecosystem: http4k Connect
title: "AWS: Security Token Service"
description: Feature overview of the http4k Connect AWS Security Token Service modules
---

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-connect-amazon-sts")
    implementation("org.http4k:http4k-connect-amazon-sts-fake")
}
```


The STS connector provides the following Actions:

     *  AssumeRole
     *  AssumeRoleWithWebIdentity

The client APIs utilise the `http4k-platform-aws` module for request signing, which means no dependencies on the incredibly fat
Amazon-SDK JARs. This means this integration is perfect for running Serverless Lambdas where binary size is a
performance factor.

### Example usage

```kotlin
const val USE_REAL_CLIENT = false

fun main() {
    val region = Region.of("us-east-1")
    val roleArn = ARN.of("arn:aws:sts:us-east-1:000000000001:role:myrole")

    // we can connect to the real service or the fake (drop in replacement)
    val http: HttpHandler = if (USE_REAL_CLIENT) JavaHttpClient() else FakeSTS()

    // create a client
    val client = STS.Http(region, { AwsCredentials("accessKeyId", "secretKey") }, http.debug())

    // all operations return a Result monad of the API type
    val assumeRoleResult: Result<AssumedRole, RemoteFailure> = client.assumeRole(roleArn, "sessionId")
    println(assumeRoleResult)
}
```

### Default Fake port: 20434

To start:

```kotlin
FakeSTS().start()
```
