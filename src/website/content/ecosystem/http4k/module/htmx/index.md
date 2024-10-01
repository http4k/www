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
    implementation(platform("org.http4k:http4k-bom:5.32.1.0"))
    implementation("org.http4k:http4k-htmx")

    implementation("org.http4k:http4k-template-handlebars") // Handlebars
}
```

### About

Utilities to support [htmx](https://htmx.org) development. Includes the htmx and hyperscript Webjar support and a set of classes/functions to ease development of htmx-powered applications.

### Code [<img class="octocat"/>](https://github.com/http4k/http4k/blob/master/src/docs/ecosystem/http4k/module/htmx/example.kt)

{{< kotlin file="example.kt" >}}
