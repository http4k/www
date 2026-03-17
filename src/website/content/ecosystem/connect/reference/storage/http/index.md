---
category: Reference
type: ecosystem
ecosystem: http4k Connect
title: "Storage: HTTP"
description: Feature overview of the http4k Connect HTTP Storage module
---

### Installation 

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-connect-storage-http")
}
```


This storage implementation provides the ability to mount another storage implementation remotely over HTTP inside an OpenAPI compatible server.

You can mount the storage with: 
{{< kotlin file="example.kt" >}}

Then simply use your browser to see the OpenAPI specification at http://localhost:8000:

<img class="imageMid" alt="openapi.png" src="openapi.png">
