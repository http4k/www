---
category: Reference
type: ecosystem
ecosystem: http4k Core
title: "API: JSON-RPC"
description: Feature overview of the http4k-api-jsonrpc module
---


### Installation (Gradle)

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-api-jsonrpc")
}
```

### About

Support for JSON-RPC handlers, with support for both manual and automatic marshalling modes.

Each service method "name" is bound to a particular endpoint function and then the entire API is 
exposed as a standard http4k `HttpHandler`, so it can be composed with other HttpHandlers and Filters.

A specialised ErrorHandler can also be assigned to the RPC contract.

Note that in order to activate JSON RPC, you need to import one of the supported JSON modules.

#### Code

{{< kotlin file="example.kt" >}}
