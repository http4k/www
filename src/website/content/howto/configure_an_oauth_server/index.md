---
layout: howto
title: "Configure an OAuth_Server"
description: Recipe for using http4k to create an authorization server that provides an *authorization code* access flow
tags: [ "http4k Core" ]
---

### Gradle setup

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-core")
    implementation("org.http4k:http4k-security-oauth")
}
```

For this example, you need to configure `OAuthServer` instance with the correct implementations of your login pages, generation of authentication codes and access tokens.

### Code

{{< kotlin file="example.kt" >}}
