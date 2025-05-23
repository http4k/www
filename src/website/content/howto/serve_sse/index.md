---
layout: howto
title: "Serve SSE"
description: Recipes for using http4k with Server-Sent Events
tags: [ "http4k Core" ]
---

### Gradle setup

```kotlin
dependencies {
    { { < http4k_bom > } }
    implementation("org.http4k:http4k-core")
    implementation("org.http4k:http4k-server-undertow")
}
```

**http4k** provides SSE (Server Sent Events) support using a simple, consistent, typesafe, and testable API on supported
server backends (see above). SSE communication consists of 3 main concepts:

1. `SseHandler` - represented as a typealias: `SseHandler =  (Request) -> SseConsumer`. This is responsible for matching
   an HTTP request to an SSE handler.
1. `SseConsumer` - represented as a typealias: `SseConsumer = (Sse) -> Unit`. This function is called on connection of a
   Sse and allow the API user to receive to events coming from the connected SSE handler.
1. `SseMessage` - a message which is sent from the SSE handler SseMessages are immutable data classes.
1. `SseFilter` - represented as a interface: `SseFilter = (SseConsumer) -> SseConsumer`. This allows for the decoration
   of `SseConsumers` to add pre or post matching behaviour in the same way as a standard `Filter`.

### SSE as a Function

The simplest possible SSE handler can be mounted as a `SseConsumer` function onto a server with:

```kotlin
{ sse: Sse -> sse.send(SseMessage.Data("hello")) }.asServer(Undertow(9000)).start()
```

### Mixing HTTP and SSE services

Both SSE and Http handlers in **http4k** are routed using a similar path-based API. We combine them into a single
`PolyHandler`. SSE handlers react to HTTP traffic which send an `Accept` header with `text/event-stream` value:

{{< kotlin file="example_polyhandler.kt" >}}

Note that if the accept header is not set, or the SSE cannot service the request, the HTTP server will be used as a
fallback.

### CORs protection for SSE

CORS (Cross-Origin Resource Sharing) is a security feature implemented by web browsers to prevent malicious websites
from making requests to a different domain than the one that served the web page. When using SSE, you should 
configure your server to allow cross-origin requests only from trusted domains.

{{< kotlin file="cors_and_sse.kt" >}}


