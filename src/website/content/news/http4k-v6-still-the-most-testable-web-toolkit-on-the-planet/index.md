---
title: "http4k v6: Still the most testable web toolkit on the planet!*"
description: http4k v6 has landed! Discover the groundbreaking features we've packed into our most powerful release yet.
date: 2025-02-01
image: takeoff.png
---

<img class="imageMid my-4" src="./takeoff.png" alt="http4k logo"/>

As previewed in our previous post, http4k v6 is finally here 🚀! We’ve been incredibly busy planning this release for
about 6 months and have been exploring the best ways to expand the http4k universe to make things even better.

This is going to ge a fairly long post, so here's what's coming up:


- [TL;DR - v5 to v5 Migration Guide](/howto/migrate_from_http4k_v5_to_v6)
- [A look back at http4k v5](#a-look-back-at-http4k-v5)
    - [The first CVE: CVE-2024-12345](#the-first-cve-cve-2024-12345)
- [What's new in http4k v6?](#whats-new-in-http4k-v6)
    - [Minimum supported Java version](#minimum-supported-java-version)
    - [Module/Code reorganisation](#modulecode-reorganisation)
    - [Introducing Pro modules: guns for show, knives for a pro](#pro-modules)
    - [Universal protocol routing](#universal-protocol-routing)
    - [Multi protocol debugging](#multi-protocol-debugging)
    - [The future of web development?](#the-future-of-web-development)
    - [Developer tooling](#developer-tooling)
    - [Bridging the divide to http4k](#bridging-the-divide-to-http4k)
    - [http4k-powered SSE Client](#http4k-powered-sse-client)
    - [Full Helidon protocol support using Virtual Threads](#full-helidon-protocol-support-using-virtual-threads)
    - [Simplified ServerConfig](#simplified-serverconfig)
    - [And a million other small changes!](#and-a-million-other-small-changes)
- [Wrap up](#wrap-up)

If you just want to skip straight to the part where you upgrade your codebase, then check out
the [v5 to v5 Migration Guide](/howto/migrate_from_http4k_v5_to_v6) - it's got an overview of how to get started. If
we've done our job correctly, it shouldn't be too bad!

# A look back at http4k v5

Released at [KotlinConf 2023](http://kotlinconf.com/2023), http4k has steadily been growing in both size and audience.
The number of modules has risen
from 127 to a massive 188 in that time. Everything from AI
integrations ([OpenAI](https://openai.com/), [Anthropic](https://claude.ai),
[LangChain4j](https://docs.langchain4j.dev/)), to Hypermedia
libraries ([HTMX](https://htmx.org)), to transport
formats ([Kotlin DataFrame](https://kotlin.github.io/dataframe/overview.html)), to templating engines (JTE) and cloud
platform clients (Azure, GCP) - we've pretty much added modules for anything an everything!

And with it, you can see from the below that http4k’s downloads have been on a very pleasingly upward trend. It's
encouraged the http4k team to keep going and we decided that v6 was a good time to have a really long hard look at how
the project was structured and what we could do better.

<img class="imageMid my-4" src="./maven.png" alt="http4k lego"/>

#### The first CVE: CVE-2024-12345/

It might seem strange to celebrate your first CVE, but it’s a rite of passage for any popular open-source project and an
excellent opportunity to learn. We were very pleased to have been able to fix the issue quickly and to have been able to
provide a fix to all users within a few hours of the issue being reported by a security researcher. This is a testament
to the efforts the team have made with http4k to ensure we provide a secure and reactive system, and the power of the
open-source model in general.

You can read about the CVE, the fix and the timeline [here](/security/cve-2024-12345)

We were also particularly impressed with the score of the CVE - **9.8**. Higher numbers are better, right? 😈

# What’s new in http4k v6?

There's a lot to cover, so let's dive in!

#### Minimum supported Java version

Ever since the inception of http4k, we've made sure to support the widest array of Java versions possible - meaning that
every one of the over 700 http4k releases supported every Java version down to v8 (released in 2014). However, we always
knew that this decision could not last indefinitely and we are taking this opportunity to move forward into the future
in a more strategic way, so we are bumping the minimum JDK version to Java 21 (which also went out of free support in
October 2024). As well as learning from our mistakes and making some changes to the core of the library, this will allow
us to take advantage of newer JVM features such as Virtual Threads, and optimisations in the target class file format.
This decision should provide a performance boost for all users, and allowed us to update all of those old dependencies
which were holding us back from adopting them.

But we aren't abandoning our commitment to stability and long-term support. For organisations that need extended support
for older Java versions, we're announcing http4k Enterprise Edition. This offering includes Long Term Support (LTS) for
previous Java versions, ensuring that teams running mission-critical applications on established Java platforms can
continue to benefit from a stable API and security updates. Enterprise Edition subscribers receive access to dedicated
support channels, priority bug fixes, discounts on training and consultancy from qualified http4k experts, as well as
complimentary access to the http4k Pro modules (more of that later!).

While the open source Community Edition moves forward to embrace new Java features, Enterprise subscribers will continue
to receive support for legacy versions through LTS releases. This approach allows us to serve both forward-looking
projects and organizations requiring longer-term stability. Whether you're building new services on Java 21 or
maintaining critical systems on older versions, we've got you covered!

To find out more about http4k Enterprise Edition - head over to the [docs](/enterprise) and get in touch!

#### Module/Code reorganisation

As http4k has grown to now over 180 modules, we've found that the existing module naming system has become a little
unwieldy, so we took the opportunity to reorganise our thinking and the existing modules around the core conceptual
arenas. You can find the old

As such, we've reorganised some of the existing modules and these will need to be migrated as part of the upgrade to v6.
You can find the list of movements in the [migration guide](/howto/migrate_from_http4k_v5_to_v6).

#### [Introducing Pro modules: guns for show, knives for a pro](https://youtu.be/UyCzZH_hFlA?si=2qKTQ5df0vE6UF23&t=27)! {#pro-modules}

<img class="imageSmall my-4" src="/images/pro.png" alt="http4k pro"/>

Also new to http4k - [Pro](/pro) modules! These extensions represent our implementations of common enterprise tools and
patterns, built with the same unwavering commitment to testability and clean API design that has earned http4k its
reputation, and released under the new [http4k Commercial license](/pro/#license).

We've currently got plans for three powerful modules: [Hot Reload](https://hotreload.http4k.org), which launches today,
enables seamless code updates in running applications. Coming up shortly,
the [Model Context Protocol SDK](https://mcp.http4k.org), providing a complete implementation of the MCP standard for AI
system interoperability and agent communication, and [Transaction Outbox]((https://outbox.http4k.org)), implementing the
critical outbox pattern for reliable message publishing in distributed systems.

Each Pro module delivers battle-tested solutions to common challenges while maintaining http4k's trademark developer
experience - simple, compositional APIs with rock-solid testing support. These implementations embody years of the
http4k team's real-world experience solving complex problems in production systems, packaged into modules that work
seamlessly with the rest of http4k.

#### Universal protocol routing

Whilst http4k has supported WebSockets and Server-Sent Events for a while, we found it quite annoying that the routing
logic was different for each protocol. This has now been unified across all protocols, which has necessitated a complete
rewrite of the routing code, but now the API should remain the largely same for users. Any breaks should be easily
fixable using the IDE. Additionally, for applications that serve multiple protocols, the above changes have allowed a
new DSL builder for `Polyhandlers`, which allows you mix and match the protocols you want to support in a single
application. Here's an example:

{{< kotlin file="polyhandler.kt" >}}

Note that you still need the correct server backend to take advantage of all protocols. There's
a [handy grid](ecosystem/http4k/reference/servers/#feature-support) in the docs.

#### Multi protocol debugging

Speaking of WebSockets and SSE, http4k was lacking the same type of experience around these protocols as it had for
HTTP. As well as the routing rewrite to unlock the power of the previously only HTTP model, we've added debugging
support for all protocols and consistentified the interface so all protocols are now equally loved.

{{< kotlin file="debug.kt" >}}

#### The future of web development?

Although mostly known for being backend engineers, the http4k team have always been interested in the full stack.
Frontend development however, has gotten a bit out of hand with the complexity of the frameworks and the amount of
JavaScript that needs to be written, so we were very interested in the rise of hypermedia frameworks such
as [HTMX](https://htmx.org/) and [Datastar](https://data-star.dev) as a way to simplify the frontend and to provide an
accessible, lightweight experience. As such, we've added support for these frameworks in http4k and homed them in a new
namespace `http4k-web`. These frameworks are well worth a look in the way they keep a lot of server state on the backend
and thus open up opportunities to unify logic whilst still providing a reactive experience. Of special mention are the
possibilities opened up by Datastar's seamless SSE/HTTP interop and the ability to control the flow of reactive data
from the serverside. Expect more of this in the future!

#### Developer tooling

http4k has always been a developer-first framework and we are always looking for ways to make the developer experience
better. The new `http4k-tools` namespace is the new home for all developer tooling modules, and we've promoted
`http4k-tools-traffic-capture` as the first module in this space - it allows you to capture and replay HTTP traffic
to/from a folder structure or any other source you can think of!

{{< kotlin file="traffic.kt" >}}

#### Bridging the divide to http4k

It's easy enough to start from scratch with http4k, but we know that many of you have existing codebases that you want
to migrate to http4k. Having done this ourselves, we know that it can be a bit of a pain to get started, which is why to
make this easier, we've added a new `http4k-bridge` namespace which provides a set of modules to help you bridge the gap
between http4k and other JVM technologies. We have provided a set of [examples](/ecosystem/http4k/reference/bridge/)
which cover the
basics and strategies for migration. Here's a list of the initial technologies in the bridge namespace:

- Helidon
- Jakarta (handles Quarkus any any other Jakarta EE server)
- Ktor
- Micronaut
- Ratpack
- Spring Web
- Servlet (Tomcat or any other servlet container)
- Vertx

#### http4k-powered SSE Client

Yet more SSE! Our work on the Model Context Protocol and Datastar modules led us to create a fully functioning
Server-Sent Events
client for http4k. It takes advantage of all the tricks that we've built up over the last few years, including automatic
reconnection,
tracing and security - we're pretty proud of it!

#### Full Helidon protocol support using Virtual Threads

We were very excited to see the release of Helidon 4.0 with support for virtual threads as a http4k v5 module, and we
have expanded it to support SSE and WS as well. This is a massive win for the performance of reactive applications.

#### Simplified ServerConfig

With a choice of 12 or so servers, the ServerConfig was getting a bit unwieldy with various options and a non-uniform
approach. We have now removed all of the options that were not strictly necessary and have provided a simple example of
each server. This should make it easier to get started with http4k and to understand the options available. The old
versions of the code have been moved to examples in the http4k source code, so if you need explicit support for these
options in Undertow Apache etc then you can still access them.

#### And a million other small changes!

Trust us - there are! 😊

# Wrap up

We're really pleased to have finally gotten http4k v6 done, not just because we need a well earned rest (!), but also
because we've got a lot more planned for the future. We can't wait to see how you'll all put these new features to work
in your teams. From enhanced routing capabilities to our improved routing system, v6 lays the groundwork for
the future of your favourite web toolkit.

Peace out.

# /the http4k team

<br/>

#####   * And yes - we're very keen to be challenged on this claim. If you can point us at a more test-focussed web toolkit, then please let us know as we're always looking for ways to improve (and steal ideas 😉!).
