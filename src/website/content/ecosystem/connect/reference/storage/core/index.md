---
category: Reference
type: ecosystem
ecosystem: http4k Connect
title: Storage
description: Feature overview of the http4k Connect Storage Core module
---

### Installation 

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-connect-storage-core")
}
```

http4k-connect contains a simple lightweight pluggable Key-Value storage abstraction in the `http4k-connect-storage-core` module, which can be used to serialise objects to an underlying store.

Standard Operations are:

- Set
- Get
- Remove
- Get all keys with a particular prefix
- Remove all keys with a particular prefix

### In-Memory Storage

All data is held in process memory.

{{< kotlin file="in_memory.kt" >}}


### On-Disk Storage

All data is serialised to disk by passing it though an http4k AutoMarshalling adapter (see the `http4k-format-XXX` modules). In the example below we use a JSON adapter backed by Moshi (which is the default).

{{< kotlin file="on_disk.kt" >}}

