---
layout: module
type: module
ecosystem: http4k Core
title: Azure
description: Feature overview of the http4k-azure module
---


### Installation (Gradle)

```kotlin
dependencies {
    implementation(platform("org.http4k:http4k-bom:5.32.1.0"))
    implementation("org.http4k:http4k-azure")
}
```

### About
This module provides a http4k compatible `HttpClient` so you can http4k-ise your use of the standard Azure SDKs libraries by plugging in a standard `HttpHandler`. This simplifies fault testing and means that you can print out the exact traffic which is going to Azure - which is brilliant for both debugging and writing Fakes. :)

#### Code

{{< kotlin file="example_sdk.kt" >}}
