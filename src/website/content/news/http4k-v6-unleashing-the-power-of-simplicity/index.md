---
draft: true
title: "http4k v6: Unleashing the Power of Simplicity: Unified routing, the reactive web revolution, and more!"
description:
date: 2025-01-01
---

**http4k-core** : [Breaking] Complete rewrite of the routing logic to work identically across HTTP, WS and
SSE. [FIXME] <-- Insert breaking changes

- **http4k-contract** : [Breaking] withPostSecurityFilter() removed as is part of the contract DSL.
- sse change packages
- sse now logs transactions
- sse debugging
- ws change packages
- ws now logs transactions
- ws debugging
- KMS CustomerKeySpec removed -> replaced with KeySpec
- HttpEvent is now a subclass of ProtocolEvent
- routing changes mean things are no longer data classes
- All extraneous server configuration removed - we now support only simple examples of servers and people need to


- Rewrite routing algorithm - unify all protocol routing
- Upgrade dependencies that are out of date with Java 8
- Revamp of SSE and WS support, including fallbacks from SSE to HTTP- added debugging support for all protocols and
  consistentified the interface so all protocols are
  now equally loved
- added support for datastar and htmx lightweight reactive web frameworks
- Full helidon support - virtual threads + SSE!
- Simplified ServerConfig for the various servers
- Change coordinates of some modules to make things easier to find

# http4k v5 to v6 Migration guide

Whilst we attempt to keep compatability across major http4k versions as much as possible

## Notable breaking changes

- As per the pre-release announcement, the minimum Java version for http4k has been raised to Java 21. If your
  organisation is interested in maintaining support for Java versions lower than 21, you may be interested to learn
  about the Long Term Support options available under the http4k Enterprise Edition subscription. You can read more
  about this programme [here], and feel free to reach out to the  <a href="enterprise@http4k.org">http4k team</a>.
- The http4k routing system had been rewritten and unified across protocols (HTTP/SSE/WS). This has resulted in some
  small repackaging although the API should not have changed for users. Any breaks should be easily fixable using the
  IDE.
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

## Module reorganisation

As http4k has grown, we've found that the existing module naming system has become a little unwieldy. To make things
easier to find, we've reorganised some of them. Here's a list of the changes:

| SOURCE MODULE - v5.X.X.X   | DESTINATION MODULE(S) - v6.X.X.X                                    |
|----------------------------|---------------------------------------------------------------------|
| http4k-aws                 | http4k-platform-aws                                                 |
| http4k-azure               | http4k-platform-azure                                               |
| http4k-cloudevents         | http4k-api-cloudevents                                              |
| http4k-cloudnative         | Split into http4k-config, http4k-platform-core, http4k-platform-k8s |
| http4k-contract            | http4k-api-openapi                                                  |
| http4k-contract-jsonschema | http4k-api-jsonschema                                               |
| http4k-contract-ui-redoc   | http4k-api-ui-redoc                                                 |
| http4k-contract-ui-swagger | http4k-api-ui-swagger                                               |
| http4k-failsafe            | http4k-ops-failsafe                                                 |
| http4k-gcp                 | http4k-platform-gcp                                                 |
| http4k-graphql             | http4k-api-graphql                                                  |
| http4k-htmx                | http4k-web-htmx                                                     |
| http4k-jsonrpc             | http4k-api-jsonrpc                                                  |
| http4k-metrics-micrometer  | http4k-ops-micrometer                                               |
| http4k-opentelemetry       | http4k-ops-opentelemetry                                            |
| http4k-resilience4j        | http4k-ops-resilience4j                                             |
