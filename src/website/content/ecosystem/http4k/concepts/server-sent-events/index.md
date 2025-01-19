---
type: ecosystem
category: Concept
ecosystem: http4k Core
title: Server-Sent Events
description: An explanation of the core function types for dealing with Server-Sent Events
---

**http4k** provides SSE (Server-Sent Events) support using a simple, consistent, typesafe, and testable API on supported server backends.

### About SSE

In SSE, interactions with a backend from the client are done via HTTP requests  - typically from an `EventSource` in the browser,
which are replied to in the Server-Sent Event (SSE) messaging format. SSE is a simple message protocol that allows the
server to send one or more messages to the client over a single HTTP connection - often by keeping the connection open
to allow streaming, and is activated simply by requesting the `text/event-stream` content type from the server.

An on-the-wire SSE message looks like this - a set of name/value field pairs, with each message terminated by 2
newlines:

```
event: new content 
data: Hello, world!

```


Sse communication consists of a few main concepts:

### SseMessage
As per the **http4k** ethos, an immutable message object to be pushed from the server to the connected client. There are 2 types of SseMessage - Events (for sending known constructs), and Data (for sending byte streams). [Lenses](/ecosystem/http4k/concepts/lens/) can be used to provide typesafe object marshalling with SseMessages. 

### Sse
```kotlin
interface Sse {
    val connectRequest: Request
    fun send(message: SseMessage)
    fun close()
    fun onClose(fn: () -> Unit)
}
```
An interface representing the available server callback API to the Server-Sent Event channel. Sse objects can `send()` SseMessages to the client, or `close()` the connection. The Sse has a reference to the incoming [HTTP Request](/ecosystem/http4k/concepts/http/) which was used during connection.

### SseConsumer
```kotlin
typealias SseConsumer = (Sse) -> Unit
```

The primary callback received when an Sse server is connected to a client. API user behaviour is configured here.

### SseHandler
```kotlin
typelias SseHandler = (Request) -> SseResponse
```

Provides the route mapping of an [HTTP Request](/ecosystem/http4k/concepts/http/) to a particular SseResponse (which contains a SseConsumer).

### SseFilter
```kotlin
fun interface SseFilter : (SseConsumer) -> SseConsumer
```

Applies decoration to a matched SseConsumer before it is invoked. SseFilters can be used to apply tangental effects to the matched SseConsumer such as logging/security, or to modify the incoming [HTTP Request](/ecosystem/http4k/concepts/http/).

### SseRouter
```kotlin
interface SseRouter {
    fun match(request: Request): SseRouterMatch
    fun withBasePath(new: String): SseRouter
    fun withFilter(new: SseFilter): SseRouter
}
```

Applies the route matching functionality when requests for Sse connections are received by the server.

