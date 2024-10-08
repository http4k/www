---
layout: howto
title: "Use HTML Forms"
description: Recipes for using http4k with HTML forms
tags: [ "http4k Core" ]
---

HTML form support is provided on 2 levels:

1. Through the use of `form()` extension methods on `Request` to get/set String values.
1. Using the Lens system, which adds the facility to define form fields in a typesafe way, and to validate form contents (in either a strict (400) or "feedback" mode).

### Gradle setup

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-core")
}
```

### Standard (non-typesafe) API 

{{< kotlin file="example_standard.kt" >}}

### Lens (typesafe, validating) API 

{{< kotlin file="example_lens.kt" >}}
