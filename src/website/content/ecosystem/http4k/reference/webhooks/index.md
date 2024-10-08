---
category: Reference
type: ecosystem
ecosystem: http4k Core
title: Webhooks
description: Feature overview of the http4k-webhooks module
---


### Installation (Gradle)

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-webhooks")
}
```

### About

This module provides infrastructure for the [Webhook standard](https://www.standardwebhooks.com/), providing infrastructure for 
signing and verifying of Webhook requests (HMAC256 only currently) as per the standard, and support for the defined Webhook event wrapper format.

The example below shows how to use sign and verify filters to automatically provide security and marshalling for the Standard Webhook format.

#### Code

{{< kotlin file="example.kt" >}}

[http4k]: https://http4k.org
