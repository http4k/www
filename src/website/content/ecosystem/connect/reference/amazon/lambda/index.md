---
category: Reference
type: ecosystem
ecosystem: http4k Connect
title: "AWS: Lambda"
description: Feature overview of the http4k Connect AWS Lambda modules
---

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-connect-amazon-lambda")
    implementation("org.http4k:http4k-connect-amazon-lambda-fake")
}
```


The Lambda connector provides the following Actions:

     *  InvokeFunction

### Example usage

```kotlin
const val USE_REAL_CLIENT = false

fun main() {
    val deployedLambda = FunctionName("http4kLambda")

    val fakeLambda = FakeLambda(
        deployedLambda to { req: Request ->
            val request = Moshi.asA<Req>(req.bodyString())
            Response(OK)
                .body(Moshi.asFormatString(Resp(request.value)))
        }
    )

    // we can connect to the real service or the fake (drop in replacement)
    val http: HttpHandler = if (USE_REAL_CLIENT) JavaHttpClient() else fakeLambda

    // create a client
    val client = Lambda.Http(Region.of("us-east-1"), { AwsCredentials("accessKeyId", "secretKey") }, http.debug())

    // all operations return a Result monad of the API type
    val invokeResult: Result<Resp, RemoteFailure> = client.invokeFunction(deployedLambda, Req("hello"), Moshi)
    println(invokeResult)
}
```

Note that the http4k-connect Fake Lambda implementation is designed to provide a runtime environment for function
HttpHandlers that will be invoked directly using the Lambda URL
pattern (`https://lambda.${scope.region}.amazonaws.com/2015-03-31/functions/$name/invocations`), rather than being
deployed behind APIGateway (where you have total control over the URL pattern where the lambda can be invoked).

The client APIs utilise the `http4k-platform-aws` module for request signing, which means no dependencies on the incredibly fat
Amazon-SDK JARs. This means this integration is perfect for running Serverless Lambdas where binary size is a
performance factor.

### Default Fake port: 50322

To start:

```kotlin
FakeLambda().start()
```
