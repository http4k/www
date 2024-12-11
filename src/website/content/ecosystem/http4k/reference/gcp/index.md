---
category: Reference
type: ecosystem
ecosystem: http4k Core
title: "Platform: GCP"
description: Feature overview of the http4k-platform-gcp module
---


### Installation (Gradle)

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-platform-gcp")
}
```

### About
This module provides a http4k compatible `HttpClient` so you can http4k-ise your use of the standard GCP SDKs libraries by plugging in a standard `HttpHandler`. This simplifies fault testing and means that you can print out the exact traffic which is going to Azure - which is brilliant for both debugging and writing Fakes. :)

#### Code

{{< kotlin file="example_sdk.kt" >}}
