---
layout: howto
title: "Leverage GraphQL"
description: Recipe for using GraphQL plugins 
tags: [ "http4k Core" ]
---

### Gradle setup

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-core")
    implementation("org.http4k:http4k-api-graphql")
}
```

### Code

{{< kotlin file="example.kt" >}}
