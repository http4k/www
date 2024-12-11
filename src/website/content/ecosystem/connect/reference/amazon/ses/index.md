---
category: Reference
type: ecosystem
ecosystem: http4k Connect
title: "AWS: Simple Email Service"
description: Feature overview of the http4k Connect AWS Simple Email Service modules
---

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-connect-amazon-ses")
    implementation("org.http4k:http4k-connect-amazon-ses-fake")
}
```


The SES connector provides the following Actions:

* SendEmail

The client APIs utilise the `http4k-platform-aws` module for request signing, which means no dependencies on the incredibly fat
Amazon-SDK JARs. This means this integration is perfect for running Serverless Lambdas where binary size is a
performance factor.

### Example usage

### Default Fake port: 59920

To start:

```kotlin
FakeSES().start()
```
