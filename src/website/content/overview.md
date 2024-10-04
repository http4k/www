---
title: Why http4k?
description: Overview of why we created http4k, the problems it solves, and the core concepts
---

http4k is a Kotlin-based HTTP toolkit which is designed to be simple, lightweight, and easy to use. It is built on functional programming principles, and is designed to be testable and easy to reason about. Since it's release it has been used in production by a vast number of companies, and has been used to build a wide range of applications,  from simple microservices to complex distributed systems, garnered millions of downloads per month and powers several globally significant websites.

The basis of http4k is inspired by the ["Your Function as a Server"](https://monkey.org/~marius/funsrv.pdf) paper produced at Twitter in 2013, which stated that all systems boundaries could be modelled as a simple function which takes a request and returns a response. This idea is the core of http4k, and is the basis for all of the libraries and tools that have been built around it. We have refined the generic concept presented in the paper to be specific to the HTTP protocol, and have built a set of tools and libraries that make it easy to build HTTP services in a functional way. See the [concepts](/ecosystem/http4k/concepts/http/) pages for more information.

Over time, we have extended the functional core concept to model other types of function covering WebSockets, Server-Sent Events, provided type-safe (de)construction of HTTP messages using Lenses, created function-based API clients, and proved that many things can be modelled by purely composition. But the core idea of a server as a function remains the same - simplicity.

So in a world where there are HTTP libraries by boatload, what makes http4k different? Mostly, it's the way it's designed with a ruthless focus on testability, control and best-in-class Developer Experience in mind. Every part of the now sizable http4k ecosystem has been developed in accordance with a real-world need and against it's set of core principles.

### Rationale & Ethos

**http4k** was created as the distillation of 15 years worth of experience of using various server-side libraries and we've stolen good ideas from everywhere we can. For instance - the routing module is inspired by [UtterlyIdle](https://github.com/bodar/utterlyidle), the "Server as a function" and filter model is stolen from [Finagle](https://twitter.github.io/finagle/), and the contract module OpenApi generator is ported from [Fintrospect](http://fintrospect.github.io). With the growing adoption of Kotlin, we wanted something that would fully leverage the features of the language and it felt like a good time to start something from scratch.

For our purposes, we wanted something that:

1. Was based on simple functional concepts and embraced **immutability**.
1. Embraced the **"Server as a Function"** model a uniform server/client API.
1. Absolutely **no magic** involved: No reflection. No annotations.
1. **Lightweight** with minimal dependencies (apart from the Kotlin StdLib, `http4k-core` has zero).
1. Embraced **Test-Driven** approaches, was testable outside of an HTTP container, and testing should require no custom infrastructure.
1. Starts/stops ultra **quickly**.
1. Provides **typesafe** HTTP message deconstruction/construction.
1. Automatically dealt with contract breaches to **remove boilerplate**.
1. Automatic generation of **OpenApi documentation** (including JSON Schema models).

**http4k** ticks all of these boxes.

It allow us to construct entire suites of services which can be tested either wired together without HTTP, or spun up in containers using a single line of code. The symmetric HTTP API also allows Filter chains (often called "Middleware" or "Interceptors" in other frameworks) to be constructed into reusable units/stacks for both server and client sides (eg. logging/metrics/caching...) since they can be composed together for later use.

As a bonus, we can also easily create simple Fake servers for any HTTP contract, which means (in combination with CDC suites) you can end-to-end test micro-services in an outside-in way (using [GOOS](http://www.growing-object-oriented-software.com/)-style acceptance tests).

Scenarios such as "what happens if this HTTP dependency continually takes > 5 seconds to respond?" are easily modelled - answers you can't easily get if you're faking out your dependencies inside the HTTP boundary.

