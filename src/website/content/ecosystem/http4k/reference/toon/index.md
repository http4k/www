---
category: Reference
type: ecosystem
ecosystem: http4k Core
title: "Format: Toon"
description: Feature overview of the Toon http4k-format module which supports auto-marshalling into data classes.
---

### Installation (Gradle)

```kotlin
dependencies {
    {{< http4k_bom >}}
 
    implementation("org.http4k:http4k-format-toon")
}
```

### About
This module add the ability to use Toon as a first-class citizen when reading from and to HTTP messages. 

We can use this facility in http4k to automatically marshall HTTP message bodies using **Lenses**. Note that this approach also sets the appropriate `Content-Type` header for the message.

#### Code

{{< kotlin file="autoToon.kt" >}}

[http4k]: https://http4k.org
