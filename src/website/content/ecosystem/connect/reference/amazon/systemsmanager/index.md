---
category: Reference
type: ecosystem
ecosystem: http4k Connect
title: "AWS: Systems Manager"
description: Feature overview of the http4k Connect AWS Systems Manager modules
---

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-connect-amazon-systemsmanager")
    implementation("org.http4k:http4k-connect-amazon-systemsmanager-fake")
}
```


The Systems Manager connector provides the following Actions:

     *  DeleteParameter
     *  GetParameter
     *  PutParameter

### Example usage

{{< kotlin file="example.kt" >}}

The client APIs utilise the `http4k-platform-aws` module for request signing, which means no dependencies on the incredibly fat
Amazon-SDK JARs. This means this integration is perfect for running Serverless Lambdas where binary size is a
performance factor.

### Default Fake port: 42551

To start:

{{< kotlin file="fake.kt" >}}
