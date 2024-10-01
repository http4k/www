---
layout: howto
title: "Lookup a user principal"
description: Recipes for looking up and populating a user principal from a request
---

### Gradle setup

```kotlin
dependencies {
    implementation(platform("org.http4k:http4k-bom:5.32.1.0"))
    implementation("org.http4k:http4k-core")
}
```

When authorising requests, it is common to need to store some credentials or a user principal object to be accessible by a further Filter or the eventual HttpHandler.

This can be easily achieved by combining the typesafe RequestContext functionality with one of the built-in authorisation Filters:

### Code [<img class="octocat"/>](https://github.com/http4k/http4k/blob/master/src/docs/howto/lookup_a_user_principal/example.kt)

{{< kotlin file="example.kt" >}}
