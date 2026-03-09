---
title: "We missed shipping our 200th module. Guess we were too busy building the next four!"
description: "Somewhere between module #199 and #204, http4k quietly sailed past 200. No cake, no fanfare - just another Tuesday release."
date: 2026-03-09
draft: true
image: image.webp
---

<img class="imageMid my-4" src="./image.webp" alt="http4k 200 modules"/>

We missed it. Sorry. Our bad.

Somewhere between module 199 and 204, http4k quietly sailed past 200 modules. No cake, no countdown, no confetti cannon - just another weekly release. By the time anyone thought to check, we were already at 204 and we're already working on the next one.

That's rather on-brand for us, if we're honest. Not really ones for fanfare.

## The Numbers

Let's put this in perspective - here's how the http4k ecosystem has grown over the years:

- 2017: http4k v1. 9 modules - a 43-line spike, a dream, and 9 modules
- 2017: v2. 12 modules, including our first template engine
- 2017: v3. 21 modules, and now covering 6 server backends
- 2021: v4. 69 modules, featured in the ThoughtWorks Radar
- 2023: v5. 127 modules, Loom support, TracerBullet, and our first AI module
- 2025: v6. 180+ modules, Pro tier, bridge modules, and the MCP SDK
- 2026: (today). 204 modules (oops, we missed 200)

842 releases of `http4k-core` since v0.21.0 first landed on Maven Central back in May 2017 - plus another 263 releases of `http4k-connect` before it was merged into the monorepo. Nearly nine years of consistent weekly shipping will do that.

## The Shape of 204 Modules

We're not going to list all 204. Life's too short and I we'd be worried if you wanted us to. But here's the shape of the ecosystem - a sense of where those modules live:

- **Core & Platform** - the foundational building blocks, lenses, routing, and the patterns everything else builds on
- **Servers** - twelve backends from Apache to Helidon to Undertow, including virtual thread support
- **Serverless** - AWS Lambda, Google Cloud Functions, Azure Functions, OpenWhisk
- **Clients** - HTTP, WebSocket, and SSE clients with the same functional approach
- **AI & MCP** - OpenAI, Anthropic, LangChain4j integrations, plus the most complete MCP SDK on the JVM
- **Connect** - lightweight, zero-reflection adapters for AWS, Azure, GCP and other cloud services, complete with fakes for testing
- **Formats** - JSON (multiple flavours), XML, YAML, CSV, DataFrame - all through the lens API
- **Templating** - Handlebars, Pebble, Thymeleaf, JTE, and more
- **Web** - HTMX and Datastar for hypermedia-driven frontends
- **Bridge** - migration modules for Spring, Ktor, Micronaut, Quarkus, and others
- **Testing & Tools** - approval testing, chaos testing, traffic capture, TracerBullet, and the new MCP testing module
- **Ops & Observability** - OpenTelemetry, Micrometer, and cloud-native configuration

People like to call it a framework (we hate that word!). We think it's more of an ecosystem. And every single piece of it follows the same functional patterns we established on day one.

## 204 Modules, One Set of Rules

Here's the thing most people don't quite believe until they try it: module 204 works exactly like module 1.

No special cases. No "well, for *this* integration you need to start a container." No annotations that secretly do twelve things. No classpath scanning. No enforced reflection. The 204th module follows the same rules as the first - it's just a function, it composes with everything else, and you can test it without a network.

And it's not an accident - it's a design constraint we've maintained for nine years, across every module, through 6 major versions. When you can test your server, your MCP, your AWS client, your WebSocket handler, and your serverless function all in-memory with the same simple patterns - everything else seems just hilariously overcomplicated and slow.

## The AI Chapter

The jump from 180 to 204 tells its own story. A significant chunk of those new modules live in the AI and MCP space - OpenAI, Anthropic, LangChain4j, and the MCP SDK that now covers the full 2025-11-25 spec plus now the first MCP Apps support on the JVM. You can expect A2A support in the future, along with a few other surprises.

And this wasn't a pivot. We started as an HTTP toolkit because HTTP is the universal transport layer. AI systems communicate over HTTP. MCP runs over HTTP. It turns out that if you build a really solid foundation for HTTP, everything that travels over it can just compose from the parts - including the stuff that didn't exist when you started.

## Community

We know that none of this happens without the contributors, users, and teams who've built with http4k, reported  issues, submitted PRs, and told their colleagues about it. We love you all!

The open source Community Edition continues under Apache 2, with the Pro and Enterprise tiers sustaining continued development. It's a model that lets us keep shipping every week without burning out, and keeps the ecosystem growing for everyone.

## What's Next

We genuinely don't know what module 210 will be (well - we maybe a few ideas but TBH we can't guess which one will land first!). That's the fun part. http4k has always grown by responding to what teams actually need and used in the real world - not by chasing trends or padding module counts. Whatever comes next, it'll follow the same rules: functional, compositional, and testable.

Less vibe, more value. That's the http4k way.

# /the http4k team
