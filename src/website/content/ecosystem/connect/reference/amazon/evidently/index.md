---
category: Reference
type: ecosystem
ecosystem: http4k Connect
title: "AWS: Cloudwatch Evidently"
description: Feature overview of the http4k Connect AWS Cloudwatch Evidently modules
---

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-connect-amazon-evidently")
    implementation("org.http4k:http4k-connect-amazon-evidently-fake")
}
```


The Evidently connector provides the following Actions:

     *  CreateProject
     *  CreateFeature
     *  EvaluateFeature
     *  BatchEvaluateFeature
     *  DeleteFeature
     *  DeleteProject

### Example usage

{{< kotlin file="example.kt" >}}

The client APIs utilise the `http4k-platform-aws` module for request signing, which means no dependencies on the incredibly fat
Amazon-SDK JARs. This means this integration is perfect for running Serverless Lambdas where binary size is a
performance factor.

### Default Fake port: 45011

To start:

{{< kotlin file="fake.kt" >}}
