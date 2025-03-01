---
category: Reference
type: ecosystem
ecosystem: http4k Connect
title: "Storage: Redis"
description: Feature overview of the http4k Connect Redis Storage module
---

### Installation 

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-connect-storage-redis")
}
```


This implementation uses the Lettuce Client library to store the data in Redis. All data is serialised to disk by
passing it though an http4k AutoMarshalling adapter (see the `http4k-format-XXX` modules). In the example below we use a
JSON adapter backed by Moshi (which is the default).

```kotlin

data class AnEntity(val name: String)

val storage = Storage.Redis<AnEntity>(Uri.of("redis://host:8000"), Moshi)

storage["myKey"] = AnEntity("hello")

println(storage["myKey"])

storage.removeAll("myKey")
```
