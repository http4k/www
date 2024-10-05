---
layout: module
type: module
ecosystem: http4k Core
title: Multipart
description: Feature overview of the http4k-multipart form module
---


### Installation (Gradle)

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-multipart")
}
```

### About

Multipart form support for fields and files, including a set of lens extensions for fields/files.

See the [how-to guides](/howto/use_multipart_forms/) for example use.

### Receiving Binary content with http4k Contracts

With binary attachments, you need to turn ensure that the pre-flight validation does not eat the stream. This is possible by instructing http4k to ignore the incoming body for validation purposes:

```kotlin
routes += "/api/document-upload" meta {
    preFlightExtraction = PreFlightExtraction.IgnoreBody
} bindContract POST to { req -> Response(OK) }
```
