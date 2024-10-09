---
layout: howto
title: "Client as a function"
description: Recipes for using http4k to consume HTTP services
tags: [ "http4k Core" ]
---

This example demonstrates using http4k as a client, to consume HTTP services. A client is just another HttpHandler.

### Gradle setup

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-core")
}
```

### Code

{{< kotlin file="example.kt" >}}
