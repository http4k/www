---
layout: howto
title: "Use a custom OAuth provider"
description: Recipe for using http4k with custom OAuth provider
---
It is very easy to configure http4k to integrate with any OAuth2 provider who supports the Authorisation Code Grant.

### Gradle setup

```kotlin
dependencies {
    {{< http4k_bom>}}
    implementation("org.http4k:http4k-core")
    implementation("org.http4k:http4k-security-oauth")
}
```

For this example, simply reconfigure the `OAuthProvider` instance with the correct details, and provide custom logic for persisting and retrieving the CSRF and AccessToken.

### Code

{{< kotlin file="example.kt" >}}
