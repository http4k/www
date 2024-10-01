---
layout: module
type: module
ecosystem: http4k Core
title: Hamkrest
description: Feature overview of the http4k-testing-hamkrest module
---


### Installation (Gradle)

```kotlin
dependencies {
    implementation(platform("org.http4k:http4k-bom:5.32.1.0"))
    implementation("org.http4k:http4k-testing-hamkrest")
}
```

### About

A set of Hamkrest matchers for use when testing http4k apps.

#### Code

{{< kotlin file="example.kt" >}}

[http4k]: https://http4k.org
