---
draft: true
category: Reference
type: ecosystem
ecosystem: http4k Core
title: "Web: HTMX"
description: Feature overview of the http4k-web-datastar module
---


### Installation (Gradle)

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-web-datastar")
}
```

### About

Utilities to support [datastar](https://data-star.dev) development. Allows you to add reactivity to your application by using the Datastar library without the need for a heavy frontend framework.


### Code

{{< kotlin file="example.kt" >}}
