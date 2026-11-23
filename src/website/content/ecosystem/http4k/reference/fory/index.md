---
category: Reference
type: ecosystem
ecosystem: http4k Core
title: "Format: Fory"
description: Feature overview of the Apache Fory http4k-format module which supports binary auto-marshalling into data classes.
---

### Installation (Gradle)

```kotlin
dependencies {
    {{< http4k_bom >}}
 
    implementation("org.http4k:http4k-format-fory")
}
```

### About
This module adds the ability to use [Apache Fory](https://fory.apache.org) binary serialization when reading from and to HTTP and WebSocket messages.

We can use this facility in http4k to automatically marshall message bodies using **Lenses**. The body is set with a `Content-Type` of `application/octet-stream`.

For safety, Fory requires every type to be explicitly registered - unregistered types are refused both when writing and when reading untrusted payloads. Common JDK and http4k types (dates, times, `UUID`, `Uri`, `Status` etc.) are supported out of the box, and values4k types can be added with `value()`.

#### Code

{{< kotlin file="autoFory.kt" >}}
