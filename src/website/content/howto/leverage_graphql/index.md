---
layout: howto
title: "Leverage GraphQL"
description: Recipe for using GraphQL plugins 
---
### Gradle setup

```kotlin
dependencies {
    implementation(platform("org.http4k:http4k-bom:5.32.1.0"))
    implementation("org.http4k:http4k-core")
    implementation("org.http4k:http4k-graphql")
}
```

### Code

{{< kotlin file="example.kt" >}}
