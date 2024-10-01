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

### Code [<img class="octocat"/>](https://github.com/http4k/http4k/blob/master/src/docs/howto/leverage_graphql/example.kt)

{{< kotlin file="example.kt" >}}
