---
category: Reference
type: ecosystem
ecosystem: http4k Core
title: "Web: Datastar"
description: Feature overview of the http4k-web-datastar module
---

### Installation (Gradle)

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-web-datastar")
}
```

### About

Utilities to support applications supporting [Datastar] development. Allows you to add reactivity
to your application by using the lightweight Datastar library without the need for a heavy frontend framework.

### What is Datastar?

In it's own words, _"Datastar helps you build reactive web applications with the simplicity of server-side rendering and
the power of a full-stack SPA framework"_. The (tiny) library it works on a system of adding `data-*` attributes (hence
the
name!) to HTML elements which react to DOM events and then can trigger effects such as loading content from the backend.

A simple example of this is to add a `data-*` attribute to form buttons, which when clicked will trigger a request to
the backend, and the response will be merged into the DOM at a specific location.

{{< kotlin file="example.html" >}}

### About SSE with Datastar

Interactions with the backend from Datastar are done via HTTP requests from an `EventSource` in the browser,
which are replied to in the Server-Sent Event (SSE) messaging format. SSE is a simple message protocol that allows the
server to send one or more messages to the client over a single HTTP connection - often by keeping the connection open
to allow streaming, and is activated simply by requesting the `text/event-stream` content type from the server.

An on-the-wire SSE message looks like this - a set of name/value field pairs, with each message terminated by 2
newlines:

```text
event: new content 
data: Hello, world!
```

Note that there is nothing inherently special about SSE - these requests and responses are just HTTP requests, and
can be handled by any `HttpHandler`, optionally using another thread to send out-of-band messages. However, http4k also
provides the [SSE](/ecosystem/http4k/concepts/server-sent-events/) protocol which supports a `SseHandler` function type
that can be used to push data to the client in a more idiomatic async way within your applications. The SSE protocol is
supported by several http4k server implementations, including Jetty, Undertow and Helidon.

There are 2 main types of message that you will normally send in Datastar responses, although there are more supported:

1. **Merge-Fragment:** This is a message that contains a fragment of HTML which is to be merged into the DOM at a
   specific location, given the set merging strategy (or "mode"). This is done by using the `data-merge-fragment`
   attribute on an element in the DOM, which is then replaced with the new content. Many fragments can be sent in a
   single event, and only the events name and the fragments data are required.

```text
event: datastar-merge-fragments
data: fragments <div id="hello">Hello, world!</div>
data: settleDuration 300
data: mergeMode morph
data: useViewTransition false

```

2. **Merge-Signals:** This is a message that contains a set of signals (data) which is used to update the state of
   various models on the client. See the [Datastar] docs for more information on how to use these signals.

```text
event:datastar-merge-signals
data:signals {foo: {bar: 1}}
data:onlyIfMissing false
```

# Why this is useful?

Akin to popular frameworks such as React, the fragmenting of page content allows each part of the page to be developed,
loaded and updated independently, which leads to smaller more modular and reusable templates and better user experience.
But it doesn't stop there...

Datastar allows you to essentially decide the reactivity dynamically on the backend without the need to make any
client-side changes -
as far as the browser is concerned, it's just requesting everything in a consistent way and by merging HTML/data from
the response as it receives it. The server can decide how to respond to these requests - be it individual fragments, or
as a stream of updates.

Additionally - the tools inside Datastar allow you to recreate the vast majority of the single-page application (SPA)
experience without the need for a heavy frontend framework/toolchain - all state and validation can be kept out of the
client (and on the backend where it is more easily tested). This both simplifies the FE development and provides a more
lightweight experience for both network and browser.

On the backend, you can service Datastar requests as a stream using a `SseHandler` or as a "single-shot" using a
standard `HttpHandler`, or use a combination of both to "fallback" from one to the other (SSE is always served first if
possible). http4k provides the common classes and response lenses to inject data into either the HTTP `Response` object
or as raw SSE events into the stream.

The http4k Datastar module also integrates with the standard Template Engine rendering system, so you can use a familiar 
templating system to render both initial page and fragment content.

### Code

This is a simple example of how to use the Datastar module to serve a simple application. The `DatastarApp`

{{< kotlin file="example.kt" >}}


[Datastar]: https://data-star.dev
