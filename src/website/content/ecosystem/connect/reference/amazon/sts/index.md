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

{{< kotlin file="example.kt" >}}

### Default Fake port: 20434

To start:

{{< kotlin file="fake.kt" >}}
