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

### Standard (non-typesafe) API [<img class="octocat"/>](https://github.com/http4k/http4k/blob/master/src/docs/howto/use_multipart_forms/example_standard.kt)

{{< kotlin file="example_standard.kt" >}}

### Lens (typesafe, validating) API - reads ALL contents onto disk/memory [<img class="octocat"/>](https://github.com/http4k/http4k/blob/master/src/docs/howto/use_multipart_forms/example_lens.kt)

{{< kotlin file="example_lens.kt" >}}

### Streaming - iterate over Multiparts [<img class="octocat"/>](https://github.com/http4k/http4k/blob/master/src/docs/howto/use_multipart_forms/example_streaming.kt)

{{< kotlin file="example_streaming.kt" >}}

### Processing Files with a Filter and convert to standard form [<img class="octocat"/>](https://github.com/http4k/http4k/blob/master/src/docs/howto/use_multipart_forms/example_processing.kt)

{{< kotlin file="example_processing.kt" >}}

### Multipart combined with typesafe contract (OpenApi) [<img class="octocat"/>](https://github.com/http4k/http4k/blob/master/src/docs/howto/use_multipart_forms/example_contract.kt)

{{< kotlin file="example_contract.kt" >}}
