---
category: Reference
type: ecosystem
ecosystem: http4k Connect
title: "AWS: Container Credentials"
description: Feature overview of the http4k Connect AWS Container Credentials modules
---

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-connect-amazon-containercredentials")
    implementation("org.http4k:http4k-connect-amazon-containercredentials-fake")
}
```


The Container Credentials connector provides the following Actions:

     *  GetCredentials

The client APIs utilise the `http4k-platform-aws` module for request signing, which means no dependencies on the incredibly fat
Amazon-SDK JARs. This means this integration is perfect for running Serverless Lambdas where binary size is a
performance factor.

### Default Fake port: 63556

To start:

```kotlin
FakeContainerCredentials().start()
```
