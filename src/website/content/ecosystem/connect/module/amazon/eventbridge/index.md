---
layout: module
type: module
ecosystem: http4k Connect
title: "AWS: EventBridge"
description: Feature overview of the http4k Connect AWS EventBridge modules
---

```kotlin
dependencies {
    {{< http4k_connect_bom>}}
    implementation("org.http4k:http4k-connect-amazon-eventbridge")
    implementation("org.http4k:http4k-connect-amazon-eventbridge-fake")
}
```


The EventBridge connector provides the following Actions:
     *  CreateEventBus
     *  DeleteEventBus
     *  DescribeEventBus
     *  PutEvents

### Example usage
```kotlin
```

The client APIs utilise the `http4k-aws` module for request signing, which means no dependencies on the incredibly fat Amazon-SDK JARs. This means this integration is perfect for running Serverless Lambdas where binary size is a performance factor.

### Default Fake port: 13577

To start:
```kotlin
FakeEventBridge().start()
```
