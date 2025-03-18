---
category: Reference
type: ecosystem
ecosystem: http4k Core
title: Servers
description: Feature overview of the http4k-server modules, covering Server backends
---

### Installation (Gradle)

```kotlin
dependencies {
    { { < http4k_bom > } }
    // Apache v5: 
    implementation("org.http4k:http4k-server-apache")

    // Apache v4: 
    implementation("org.http4k:http4k-server-apache4")

    // Jetty & JettyLoom: 
    implementation("org.http4k:http4k-server-jetty")

    // Jetty11 & Jetty11Loom: 
    implementation("org.http4k:http4k-server-jetty11")

    // Helidon (Loom): 
    implementation("org.http4k:http4k-server-helidon")

    // Ktor CIO: 
    implementation("org.http4k:http4k-server-ktorcio")

    // Ktor Netty: 
    implementation("org.http4k:http4k-server-ktornetty")

    // Netty: 
    implementation("org.http4k:http4k-server-netty")

    // Ratpack: 
    implementation("org.http4k:http4k-server-ratpack")

    // Servlet: 
    implementation("org.http4k:http4k-server-servlet")

    // Undertow: 
    implementation("org.http4k:http4k-server-undertow")

    // Java WebSocket:
    implementation("org.http4k:http4k-server-websocket")

    // SunHttp & SunHttpLoom (for development only): 
    implementation("org.http4k:http4k-core")
}
```

### About

Server-backend modules provide a consistent API to mount HttpHandlers into the specified container in 1 LOC, by
simply passing it to the relevant `ServerConfig` implementation (in this case `Jetty`):

### Feature support

**http4k** provides support for the following Server backends:

| Server     | HTTP | WebSockets | SSE | Virtual Threads | Notes                                                                           |  
|------------|------|------------|-----|-----------------|---------------------------------------------------------------------------------|
| Apache     | ✅    | ❌          | ❌   | ❌               |                                                                                 |
| Apache 4   | ✅    | ❌          | ❌   | ❌               | Legacy                                                                          |
| Helidon    | ✅    | ✅          | ✅   | ✅               | Bug: Missing request headers. https://github.com/helidon-io/helidon/issues/9918 |
| Jetty      | ✅    | ✅          | ✅   | ✅               |                                                                                 |
| Jetty 11   | ✅    | ✅          | ✅   | ✅               | Legacy                                                                          |
| Ktor CIO   | ✅    | ❌          | ❌   | ❌               |                                                                                 |
| Ktor Netty | ✅    | ❌          | ❌   | ❌               |                                                                                 |
| Netty      | ✅    | ✅          | ❌   | ❌               |                                                                                 |
| Ratpack    | ✅    | ❌          | ❌   | ❌               |                                                                                 |
| Servlets   | ✅    | ❌          | ❌   | ❌               |                                                                                 |
| SunHttp    | ✅    | ✅          | ❌   | ✅               | Non-production                                                                  |
| Undertow   | ✅    | ✅          | ✅   | ❌               |                                                                                 |
| Websocket  | ❌    | ✅          | ❌   | ❌               |                                                                                 |

#### Code

{{< kotlin file="example_http.kt" >}}

### Servlets
To mount any `HttpHandler` in a container, you can install the `http4k-server-servlet` dependency and then simply be converted to a Servlet by calling ```handler.asServlet()```

### Customisation

Each of the server backends implement an interface `ServerConfig`, which is written with sensible defaults for the
server in questions,
but is also designed to be used as a starting point for tweaking to API user needs. To customize, simply use the
relevant `ServerConfig`
class as a starting point and reimplement as required. See the how-to guides for an example of this in use.


