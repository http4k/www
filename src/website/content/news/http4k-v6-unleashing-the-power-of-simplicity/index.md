---
draft: true
title: "http4k v6: Unleashing the Power of Simplicity: Unified routing, http4k EE, the reactive web revolution, and more!"
description:
date: 2025-01-01
image: takeoff.png
---

<img class="imageMid" src="./takeoff.png" alt="http4k lego"/>

As previewed in our previous post, http4k 6 is finally here! We’ve been incredibly busy planning this release for about
6 months and have been exploring the best ways to expand the http4k universe to make things even better. You can see
from the below that http4k’s downloads have been on a very pleasing upward trend.

## A review of http4k v5

Released at KotlinConf 2023,

<img class="imageMid" src="./maven.png" alt="http4k lego"/>

### The first CVE: CVE-2024-12345

It might seem strange to celebrate your first CVE, but it’s a rite of passage for any open-source project and an
excellent opportunity to learn. We were very pleased to have been able to fix the issue quickly and to have been able to
provide a fix to all users within a few hours of the issue being reported by a security researcher. This is a testament
to the efforts the team have made with http4k to ensure we provide a secure and reactive system, and the power of the
open-source model in general.

You can read about the CVE, the fix and the timeline [here](/security/CVE-2024-55875)

We were also particularly impressed with the score of the CVE - 9.8. Higher numbers are better, right?

## What’s new in http4k v6?

There's a lot to cover, so let's dive in!

### Minimum supported Java version

Ever since the inception of http4k, we’ve made sure to support the widest array of Java versions possible - meaning that
every one of the over 700 http4k releases supported every Java version down to v8 (released in 2014). However, we always
knew that this decision could not last indefinitely and we are taking this opportunity to move forward into the future
in a more strategic way, so we are bumping the minimum JDK version to Java 21 (which also went out of free support in
October 2024!). As well as learning from our mistakes and making some changes to the core of the library, this will
allow us to take advantage of newer JVM features and optimisations in the target class file format. This decision should
provide a performance boost for all users, and allowed us to update all of those old dependencies which were holding us
back from adopting them.

### Module/Code reorganisation and nomenclature

As http4k has grown to now over 180 modules, we've found that the existing module naming system has become a little
unwieldy, so we took the opportunity to reorganise our thinking and the existing modules around the core conceptual
arenas. Going forward, the various parts of http4k will now be referred in 4 levels:

- **Level 1: Modules** - these are the individual modules or integrations, representing a single JAR file with a single
  set
  of Maven coordinates. eg. `http4k-core`, `http4k-client-apache`
- **Level 2: Feature** - a group of modules that represent a set of modules that all accomplish the same task with
  different implementations, eg. `http4k-platform`, `http4k-format`, `http4k-template`.
- **Level 3: Project** - a grouping of features based around a common theme - eg. `http4k Core` or `http4k Connect`
- **Level 4: Ecosystem** - this is http4k in it's entirety and is represented in code by the http4k Bill-of-materials -
  `http4k-bom`

Almost all of the http4k code in unaffected by these changes apart from splitting out of some of the functionality in
`http4k-core` which has been moved to new Feature modules.

As such, we've reorganised some of the existing modules and these will need to be migrated as part of the upgrade to v6.
Here's a list of the movements:

| SOURCE MODULE - v5.X.X.X        | Project | Feature  | DESTINATION MODULE(S) - v6.X.X.X                                                   |
|---------------------------------|---------|----------|------------------------------------------------------------------------------------|
| http4k-aws                      | Core    | Platform | http4k-platform-aws                                                                |
| http4k-azure                    | Core    | Platform | http4k-platform-azure                                                              |
| http4k-core                     | Core    | Various  | Some functionality moved to: http4k-tooling-traffic-capture, http4k-bridge-servlet |
| http4k-connect-ai-openai-plugin | Core    | AI       | Removed due to deprecation of the functionality in OpenAI                          |
| http4k-cloudevents              | Core    | API      | http4k-api-cloudevents                                                             |
| http4k-cloudnative              | Core    | Various  | Split into http4k-config, http4k-platform-core, http4k-platform-k8s                |
| http4k-contract                 | Core    | API      | http4k-api-openapi                                                                 |
| http4k-contract-jsonschema      | Core    | API      | http4k-api-jsonschema                                                              |
| http4k-contract-ui-redoc        | Core    | API      | http4k-api-ui-redoc                                                                |
| http4k-contract-ui-swagger      | Core    | API      | http4k-api-ui-swagger                                                              |
| http4k-failsafe                 | Core    | Ops      | http4k-ops-failsafe                                                                |
| http4k-gcp                      | Core    | Platform | http4k-platform-gcp                                                                |
| http4k-graphql                  | Core    | API      | http4k-api-graphql                                                                 |
| http4k-htmx                     | Core    | Web      | http4k-web-htmx                                                                    |
| http4k-jsonrpc                  | Core    | API      | http4k-api-jsonrpc                                                                 |
| http4k-metrics-micrometer       | Core    | Ops      | http4k-ops-micrometer                                                              |
| http4k-opentelemetry            | Core    | Ops      | http4k-ops-opentelemetry                                                           |
| http4k-resilience4j             | Core    | Ops      | http4k-ops-resilience4j                                                            |

### Universal protocol routing

Whilst http4k has supported WebSockets and Server-Sent Events for a while, we found it quite annoying that the routing
logic was different for each protocol. This has now been unified across all protocols, which has necessitated a complete
rewrite of the routing code, but now the API should remain the largely same for users. Any breaks should be easily
fixable using the IDE. Additionally, for applications that serve multiple protocols, the above changes have allowed a
new DSL builder for Polyhandlers, which allows you mix and match the protocols you want to support in a single
application. Here's an example:

{{< kotlin file="polyhandler.kt" >}}

### Multi protocol support

Speaking of WebSockets and SSE, http4k was lacking the same type of experience around these protocols as it had for
HTTP. As well as the routing rewrite to unlock the power of the previously only HTTP model, we've added debugging
support for all protocols and consistentified the interface so all protocols are now equally loved.

### The future of web development

Although mostly known for being backend engineers, the http4k team have always been interested in the full stack.
Frontend development however, has gotten a bit out of hand with the complexity of the frameworks and the amount of
JavaScript that needs to be written, so we were very interested in the rise of hypermedia frameworks such as HTMX and
Datastar as a way to simplify the frontend and to provide an accessible, lightweight experience. As such, we've added
support for these frameworks in http4k and homed them in a new Features `http4k-web`. These frameworks are well worth a
look in the way they keep a lot of server state on the backend and thus open up opportunities to unify logic whilst
still providing a reactive experience. Of special mention are the possibilities opened up by Datastar's seamless
SSE/HTTP fallbacks and the ability to control the flow of reactive data from the serverside. Expect more of this in the
future!

### Developer tooling

http4k has always been a developer-first framework and we are always looking for ways to make the developer experience
better. The new `http4k-tools` Feature is the new home for all developer tooling, and we've added a new module

### Holy Hot Reload Batman!

The http4k Template Feature has supported HotReload for a while, but this only supported the templating layer. We've
made this better and you can now hot reload your entire application, including the routing layer. This is a game changer
for using http4k with libraries like KotlinX HTML or HTMX/Datastar, where you can now see your changes in real time.
Check it out at `http4k-tools-hotreload`.

### Bridging the divide to http4k

It's easy enough to start from scratch with http4k, but we know that many of you have existing codebases that you want
to migrate to http4k. Having done this ourselves, we know that it can be a bit of a pain to get started, which is why to
make this easier, we've added a new `http4k-bridge` Feature which provides a set of modules to help you bridge the gap
between http4k and other JVM technologies. This will be accompanied by a set of how-to guides which will cover the
basics and strategies for migration. Here's a list of the initial technologies in the Bridge Feature:

- Helidon
- Jakarta (handles Quarkus any any other Jakarta EE server)
- Ktor
- Micronaut
- Ratpack
- Spring Web
- Servlet (Tomcat or any other servlet container)
- Vertx

### Full protocol support using Virtual Threads

We were very excited to see the release of Helidon 4.0 with support for virtual threads as a http4k v5 module, and we
have expanded it to support SSE and WS as well. This is a game changer for the performance of reactive applications.

### Simplified ServerConfig

With a choice of 12 or so servers, the ServerConfig was getting a bit unwieldy with various options and a non-uniform
approach. We have now removed all of the options that were not strictly necessary and have provided a simple example of
each server. This should make it easier to get started with http4k and to understand the options available. The old
versions of the code have been moved to examples in the http4k source code, so if you need explicit support for these
options in Undertow Apache etc then you can still access them.

# http4k v5 to v6 Migration guide

Whilst we attempt to keep compatability across major http4k versions as much as possible

## Notable breaking changes

**http4k-core** : [Breaking] Complete rewrite of the routing logic to work identically across HTTP, WS and
SSE. [FIXME] <-- Insert breaking changes

- **http4k-api-openapi** : [Breaking] withPostSecurityFilter() removed as is part of the contract DSL.
- sse change packages
- sse now logs transactions
- sse debugging
- ws change packages
- ws now logs transactions
- ws debugging
- Java SSE client
- KMS CustomerKeySpec removed -> replaced with KeySpec
- HttpEvent is now a subclass of ProtocolEvent
- routing changes mean things are no longer data classes
- All extraneous server configuration removed - we now support only simple examples of servers and people need to
- Rewrite routing algorithm - unify all protocol routing


- As per the pre-release announcement, the minimum Java version for http4k has been raised to Java 21. If your
  organisation is interested in maintaining support for Java versions lower than 21, you may be interested to learn
  about the Long Term Support options available under the http4k Enterprise Edition subscription. You can read more
  about this programme [here], and feel free to reach out to the  <a href="enterprise@http4k.org">http4k team</a>.
- The http4k routing system had been completely rewritten and unified across all supported protocols (HTTP/SSE/WS). This
  has resulted in some small repackaging although the API should not have changed for users. Any breaks should be easily
  fixable using the IDE.
- For ease, `ServerConfigs` across all server-backends have been simplified, removing any extraneous setup from the
  default configuration. The standard implementations have been designed to be duplicated and tweaked as required by end
  users.
- As http4k has grown to over 160 modules, we've found that the existing module naming system has become a little
  unwieldy. To make things easier to find, we've reorganised some of them and given them better Maven co-ordinates (
  documented below).
- We have unified some events - notably `HttpEvent` - to be the same implementation across all protocols, which has
  necessitated the replacement of `HttpEvent` with `ProtocolEvent`. The API is the same as before as are the names, but
  the hierarchy is different, and the event has gained a `protocol` field to indicate what type of server request
  caused the connection.

## Suggested migration path

1. Update your project to use the latest version of http4k v5.
2. Deal with any deprecation warnings - these should all have replacements and be inlinable using your IDE.
3. Start using the new module names from the table below. These modules are identical in functionality to the old ones,
   but have been renamed for consistency.
4. Build and check your project is still working as expected.
5. Update your project to use the latest version of http4k v6.
6. Deal with any breaking changes - these are detailed below.



### Notes:

- Move security to security core - share between SSE and HTTP
- Debug to all protocols
- Unify routing across all protocols
- MCP Desktop - no reflection
- MCP SDK - no reflection
- Hot reload - debuggable
- Bridge modules
- Upgrades to all modules
- Upgrade to java 21
- polyhandler DSL
- moving code out of core, bridge
- traffic module
- module moves
- datastar module
- simplify request context

