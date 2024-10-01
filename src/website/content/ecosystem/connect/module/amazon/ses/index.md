---
layout: module
type: module
ecosystem: connect
title: AWS Simple Email Service
description: Feature overview of the http4k Connect AWS Simple Email Service modules
---

```kotlin
dependencies {
    implementation(platform("org.http4k:http4k-connect-bom:5.22.1.0"))
    implementation("org.http4k:http4k-connect-amazon-ses")
    implementation("org.http4k:http4k-connect-amazon-ses-fake")
}
```


The SES connector provides the following Actions:

* SendEmail

The client APIs utilise the `http4k-aws` module for request signing, which means no dependencies on the incredibly fat
Amazon-SDK JARs. This means this integration is perfect for running Serverless Lambdas where binary size is a
performance factor.

### Example usage

### Default Fake port: 59920

To start:

```
FakeSES().start()
```
