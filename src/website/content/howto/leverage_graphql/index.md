---
layout: howto
title: "Leverage GraphQL"
description: Recipe for using GraphQL plugins 
ecosystem: http4k Core
---
### Gradle setup

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-core")
    implementation("org.http4k:http4k-graphql")
}
```

### Code

{{< kotlin file="example.kt" >}}
