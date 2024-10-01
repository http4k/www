---
layout: module
type: module
ecosystem: http4k Connect
title: "Storage: HTTP"
description: Feature overview of the http4k Connect HTTP Storage module
---

### Installation 

```kotlin
dependencies {
    implementation(platform("org.http4k:http4k-connect-bom:5.22.1.0"))
    implementation("org.http4k:http4k-connect-storage-http")
}
```


This storage implementation provides the ability to mount another storage implementation remotely over HTTP inside an OpenAPI compatible server.

You can mount the storage with: 
```kotlin
data class AnEntity(val name: String)

val baseStorage = Storage.InMemory<AnEntity>()
baseStorage.asHttpHandler().asServer(SunHttp(8000)).start()
```

Then simply use your browser to see the OpenAPI specification at http://localhost:8000:

<img alt="openapi.png" src="openapi.png" width="100%">