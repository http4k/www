---
layout: howto
title: "Routing API (Simple)"
description: Recipes for using the http4k composable routing API
---
This example shows how to use the simple routing functionality to bind several routes.

For the typesafe contract-style routing, refer to [this](/howto/integrate_with_openapi/) recipe instead,

### Gradle setup

```kotlin
dependencies {
    {{< http4k_bom>}}
    implementation("org.http4k:http4k-core")
}
```

### Code

{{< kotlin file="example.kt" >}}
