---
category: Reference
type: ecosystem
ecosystem: http4k Core
title: "Testing: WebDriver (Datastar)"
description: Feature overview of the http4k-testing-webdriver-datastar module
---


### Installation (Gradle)

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-testing-webdriver-datastar")
}
```

### About

A [Datastar](/ecosystem/http4k/reference/datastar/)-aware extension of the
[http4k-testing-webdriver](/ecosystem/http4k/reference/webdriver/) module. It runs completely out of container (no
network, no browser) for ultra fast tests, while simulating just enough of a Datastar v1 browser to drive a reactive
app end-to-end with the standard Selenium WebDriver API.

On top of the base WebDriver it understands the Datastar runtime:

| Feature | Supported | Notes |
|---------|-----------|-------|
| `data-on-*` event directives | yes | e.g. `data-on-click`, `data-on-load` |
| `@get` / `@post` actions | yes | issued back to your `HttpHandler` |
| SSE patches | yes | `datastar-patch-elements` and `datastar-patch-signals` |
| DOM morphing | yes | applies the configured `MorphMode` |
| Signals / reactivity | yes | client-side signal store with reactive bindings |

When an event fires, the driver makes the corresponding request to your app, reads the returned Server-Sent Events, and
applies the patches to its in-memory DOM - exactly as the real Datastar library would in a browser. This lets you assert
on the resulting page without any JavaScript engine.

Use it like any other WebDriver implementation, by passing your app `HttpHandler` to construct it.

#### Code

{{< kotlin file="example.kt" >}}

[http4k]: https://http4k.org
[Datastar]: https://data-star.dev
