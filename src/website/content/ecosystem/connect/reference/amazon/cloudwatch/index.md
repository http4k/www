---
category: Reference
type: ecosystem
ecosystem: http4k Connect
title: "AWS: CloudWatch"
description: Feature overview of the http4k Connect AWS CloudWatch modules
---

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-connect-amazon-cloudwatch")
    implementation("org.http4k:http4k-connect-amazon-cloudwatch-fake")
}
```


The CloudWatch connector provides the following Actions:

* DeleteAlarms
* DescribeAlarms
* DescribeAlarmsForMetric
* DisableAlarmActions
* EnableAlarmActions
* GetMetricData
* GetMetricStatistics
* ListMetrics
* ListTagsForResource
* PutCompositeAlarm
* PutMetricAlarm
* PutMetricData
* SetAlarmState
* TagResource
* UntagResource

The client APIs utilise the `http4k-platform-aws` module for request signing, which means no dependencies on the incredibly fat
Amazon-SDK JARs. This means this integration is perfect for running Serverless Lambdas where binary size is a
performance factor.

### Example usage

```kotlin
const val USE_REAL_CLIENT = false

val http: HttpHandler = if (USE_REAL_CLIENT) JavaHttpClient() else FakeCloudWatch()

// create a client
val cloudWatch =
    CloudWatch.Http(Region.US_EAST_1, { AwsCredentials("accessKeyId", "secretKey") }, http.debug())

// all operations return a Result monad of the API type
val result: Result<Metrics, RemoteFailure> = cloudWatch.listMetrics(
    Namespace.of("foobar")
)

println(result)
```

### Default Fake port: 57564

To start:

```kotlin
FakeCloudWatch().start()
```
