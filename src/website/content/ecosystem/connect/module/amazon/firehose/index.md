---
layout: module
type: module
ecosystem: http4k Connect
title: "AWS: Firehose"
description: Feature overview of the http4k Connect AWS Firehose modules
---

```kotlin
dependencies {
    implementation(platform("org.http4k:http4k-connect-bom:5.22.1.0"))
    implementation("org.http4k:http4k-connect-amazon-firehose")
    implementation("org.http4k:http4k-connect-amazon-firehose-fake")
}
```


The Firehose connector provides the following Actions:
     *  CreateDeliveryStream
     *  DeleteDeliveryStream
     *  ListDeliveryStreams
     *  PutRecord
     *  PutRecordBatch

### Example usage
```kotlin
```

The client APIs utilise the `http4k-aws` module for request signing, which means no dependencies on the incredibly fat Amazon-SDK JARs. This means this integration is perfect for running Serverless Lambdas where binary size is a performance factor.

### Default Fake port: 30879

To start:
```
FakeFirehose().start()
```