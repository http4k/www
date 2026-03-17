---
category: Reference
type: ecosystem
ecosystem: http4k Connect
title: "Storage: JDBC"
description: Feature overview of the http4k Connect JDBC Storage module
---

### Installation 

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-connect-storage-jdbc")
}
```


This implementation uses the Jetbrains Exposed library to store the data in the DB. All data is serialised to disk by
passing it though an http4k AutoMarshalling adapter (see the `http4k-format-XXX` modules). In the example below we use a
JSON adapter backed by Moshi (which is the default).

{{< kotlin file="example.kt" >}}
