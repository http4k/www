---
layout: howto
title: "Client as a function"
description: Recipes for using http4k to consume HTTP services
---
This example demonstrates using http4k as a client, to consume HTTP services. A client is just another HttpHandler.

### Gradle setup

```kotlin
dependencies {
    implementation(platform("org.http4k:http4k-bom:5.32.1.0"))
    implementation("org.http4k:http4k-core")
}
```

### Code

{{< kotlin file="example.kt" >}}
