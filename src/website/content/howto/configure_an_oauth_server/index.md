---
layout: howto
title: "Configure an OAuth_Server"
description: Recipe for using http4k to create an authorization server that provides an *authorization code* access flow
---
### Gradle setup

```kotlin
dependencies {
    implementation(platform("org.http4k:http4k-bom:5.32.1.0"))
    implementation("org.http4k:http4k-core")
    implementation("org.http4k:http4k-security-oauth")
}
```

For this example, you need to configure `OAuthServer` instance with the correct implementations of your login pages, generation of authentication codes and access tokens.

### Code

{{< kotlin file="example.kt" >}}
