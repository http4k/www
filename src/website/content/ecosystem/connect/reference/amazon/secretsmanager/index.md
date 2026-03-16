---
category: Reference
type: ecosystem
ecosystem: http4k Connect
title: "AWS: Secrets Manager"
description: Feature overview of the http4k Connect AWS Secrets Manager modules
---

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-connect-amazon-secretsmanager")
    implementation("org.http4k:http4k-connect-amazon-secretsmanager-fake")
}
```


The Secrets Manager connector provides the following Actions:

     *  CreateSecret
     *  DeleteSecret
     *  GetSecretValue
     *  ListSecrets
     *  PutSecretValue
     *  UpdateSecret

### Example usage

{{< kotlin file="example.kt" >}}

The client APIs utilise the `http4k-platform-aws` module for request signing, which means no dependencies on the incredibly fat
Amazon-SDK JARs. This means this integration is perfect for running Serverless Lambdas where binary size is a
performance factor.

### Default Fake port: 58194

To start:

{{< kotlin file="fake.kt" >}}
