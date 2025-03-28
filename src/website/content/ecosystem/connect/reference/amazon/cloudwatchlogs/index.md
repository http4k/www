---
category: Reference
type: ecosystem
ecosystem: http4k Connect
title: "AWS: CloudWatchLogs"
description: Feature overview of the http4k Connect AWS CloudWatchLogs modules
---

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-connect-amazon-cloudwatchlogs")
    implementation("org.http4k:http4k-connect-amazon-cloudwatchlogs-fake")
}
```


The CloudWatchLogs connector provides the following Actions:

* CreateLogGroup
* CreateLogStream
* DeleteLogGroup
* DeleteLogStream
* FilterLogEvents
* PutLogEvents

The client APIs utilise the `http4k-platform-aws` module for request signing, which means no dependencies on the incredibly fat
Amazon-SDK JARs. This means this integration is perfect for running Serverless Lambdas where binary size is a
performance factor.

### Example usage

```kotlin
const val USE_REAL_CLIENT = false

val http: HttpHandler = if (USE_REAL_CLIENT) JavaHttpClient() else FakeCloudWatchLogs()

// creatxe a client
val cloudWatchLogs =
    CloudWatchLogs.Http(Region.US_EAST_1, { AwsCredentials("accessKeyId", "secretKey") }, http.debug())

val result: Result<PutLogEventsResponse, RemoteFailure> = cloudWatchLogs.putLogEvents(
    LogGroupName.of("foobar"),
    LogStreamName.of("stream"),
    emptyList()
)

println(result)
```

### Default Fake port: 56514

To start:

```kotlin
FakeCloudWatchLogs().start()
```
