---
category: Reference
type: ecosystem
ecosystem: http4k Core
title: "Testing: Power Assert"
description: Feature overview of the http4k-testing-powerassert module
---


### Installation (Gradle)

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-testing-powerassert")
}
```

### About

A set of [Power Assert] matchers for use when testing http4k apps.

#### Code

{{< kotlin file="example.kt" >}}

[http4k]: https://http4k.org
[Power Assert]: https://kotlinlang.org/docs/power-assert.html
