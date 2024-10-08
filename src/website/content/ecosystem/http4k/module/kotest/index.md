---
category: Reference
type: ecosystem
ecosystem: http4k Core
title: Kotest
description: Feature overview of the http4k-testing-kotest module
---


### Installation (Gradle)

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-testing-kotest")
}
```

### About

A set of [Kotest] matchers for use when testing http4k apps.

#### Code

{{< kotlin file="example.kt" >}}

[http4k]: https://http4k.org
[kotest]: https://github.com/kotest/kotest
