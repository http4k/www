---
layout: howto
title: "Use Multipart Forms"
description: Recipes for using http4k with Multipart forms
---
Multipart form support is provided on 2 levels:

1. Through the creation of a `MultipartFormBody` which can be set on a `Request`.
1. Using the Lens system, which adds the facility to define form fields in a typesafe way, and to validate form contents (in either a strict (400) or "feedback" mode).

### Gradle setup

```kotlin
dependencies {
    implementation(platform("org.http4k:http4k-bom:5.32.1.0"))
    implementation("org.http4k:http4k-core")
    implementation("org.http4k:http4k-multipart")
}
```

### Standard (non-typesafe) API 

{{< kotlin file="example_standard.kt" >}}

### Lens (typesafe, validating) API - reads ALL contents onto disk/memory 

{{< kotlin file="example_lens.kt" >}}

### Streaming - iterate over Multiparts 

{{< kotlin file="example_streaming.kt" >}}

### Processing Files with a Filter and convert to standard form 

{{< kotlin file="example_processing.kt" >}}

### Multipart combined with typesafe contract (OpenApi) 

{{< kotlin file="example_contract.kt" >}}
