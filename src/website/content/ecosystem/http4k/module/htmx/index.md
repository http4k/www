---
layout: module
type: module
ecosystem: http4k Core
title: htmx
description: Feature overview of the http4k-htmx module
---


### Installation (Gradle)

```kotlin
dependencies {
    {{< http4k_bom>}}
    implementation("org.http4k:http4k-htmx")

    implementation("org.http4k:http4k-template-handlebars") // Handlebars
}
```

### About

Utilities to support [htmx](https://htmx.org) development. Includes the htmx and hyperscript Webjar support and a set of classes/functions to ease development of htmx-powered applications.

### Code

{{< kotlin file="example.kt" >}}
