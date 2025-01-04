---
draft: true
category: Reference
type: ecosystem
ecosystem: http4k Core
title: "Tools: Hot Reload"
description: Feature overview of the http4k-tools-hotreload module
---

### Installation (Gradle)

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-tools-hotreload")
}
```

### About

The `http4k-tools-hotreload` module provides a simple mechanism to dynamically reload the application when the source
code changes. This is extremely useful for web development when used in combination with a templating system such as
Handlebars, as it allows you to see live changes in both the application logic and the templates without having to
restart the application.

The reloading mechanism works implementing a class which implements the `HotReloadable` interface to create a fresh
instance of your application (`HttpHandler` or `PolyHandler`), and using a reference to this class to the
`HotReloadServer` class which acts like a standard `Http4kServer`.

Additionally, the `HotReloadServer` class intercepts HTML responses and injects a JavaScript `EventSource` that will 
automatically reload a page in the browser when the server restarts.

Note that by default the `HotReloadServer` will listen on port 8000 and uses the `SunHttp` server as it is both lightweight, has no dependencies, and starts incredibly quickly.

#### Example

Once the server is started, open a browser at any page, edit the code (or template), and see
the page refresh.

A production application (lives in `src/main`)
{{< kotlin file="MyApp.kt" >}}

The Hot Reload is setup in `src/test`  by implementing the `HotReloadable` interface and 
then configuring the `HotReloadServer`.
{{< kotlin file="main.kt" >}}


