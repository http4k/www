---
layout: howto
title: "Lookup a user principal"
description: Recipes for looking up and populating a user principal from a request
---

### Gradle setup

```kotlin
dependencies {
    {{< http4k_bom>}}
    implementation("org.http4k:http4k-core")
}
```

When authorising requests, it is common to need to store some credentials or a user principal object to be accessible by a further Filter or the eventual HttpHandler.

This can be easily achieved by combining the typesafe RequestContext functionality with one of the built-in authorisation Filters:

### Code

{{< kotlin file="example.kt" >}}
