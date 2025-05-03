---
title: "Simplifying MCP: http4k's Updated Authentication Model - Less Code, More Power"
description: The http4k MCP SDK has been updated to support the latest Draft OAuth specification,
    enhancing security and flexibility in AI integrations.
date: 2025-05-02
image: oauth.png
---

<img class="imageMid my-4" src="./http4k-oauth-mcp.png" alt="http4k MCP logo"/>

We're excited to share a significant update to our [http4k Model Context Protocol (MCP) SDK](https://mcp.http4k.org)! 🚀 Based on community feedback and our commitment to making AI integration as frictionless as possible, we've streamlined the authentication model in our latest release.

## TL;DR - What's New?

- **Simplified Auth Model**: We've implemented the new draft specification that requires only resource server capabilities, eliminating the need for dual server implementation
- **Version 6.7.0.0 Released**: Available now with these improvements
- **http4k-mcp-desktop 1.5**: Updated desktop client to support the new auth model
- **Streamlined Client API**: Connecting to MCP servers is now even easier
- **Same http4k Testability**: All our changes maintain the testability-first approach you expect

## Why We Made These Changes

The newest (2025-3-26)  MCP authentication specification was widely considered too complicated for server creators to implement effectively as it required the MCPs to act as both an OAuth Resource and Authentication servers. The MCP community took this on board and based on feedback from our SDK users, we thoroughly agreed that a simplification was required. From the latest changes in the MCP community around the [specification](https://modelcontextprotocol.io/specification/draft/basic/authorization), we've embraced the changes in the new draft spec that dramatically reduces this complexity while maintaining robust OAuth security. Whilst these are not yet officially part of the spec, the direction of travel is clear and we believe they are sensible and will be adopted by the community.

For http4k, the developer experience has always been our north star. Along with testability, simplicity, and functional purity, we believe that working with our libraries should feel natural and frictionless. These changes reflect that ongoing commitment to putting developers first.

## One-Line Server Setup

Setting up an MCP server with proper authentication is now as simple as using the new simplified auth model with just one parameter addition. This small change means you can create a fully compliant MCP server with minimal ceremony - exactly how http4k should be! 

{{< kotlin file="server.kt" >}}

As before, we also have implementations for Basic, Api Key and Bearer token authentication if they are more appropriate for your use case.

## Improved Client API

We've also made securely connecting to MCP servers more intuitive with our streamlined client connection API which
is implemented via a standard http4k Filter. The same simple API for interacting with the server remains the same.

{{< kotlin file="client.kt" >}}

Filters for the other authentication methods are also available, so you can easily switch between them as needed.

## Still Fully Testable

True to http4k's principles, these changes maintain complete testability, allowing you to test authentication flows directly without spinning up real servers.

## Staying Ahead of the Curve

Despite what you may have read elsewhere, the http4k MCP SDK remains currently the only production-ready way to run distributed MCPs in a JVM cloud-based or Serverless environment. While official JVM MCP SDKs are still catching up with even the previous spec version, http4k gives you access to the latest features today.

## Embrace the change today!

The latest version of http4k supporting these changes is available now through Maven Central. For http4k MCP desktop integration, this update is also available via Homebrew and GitHub releases. Check it out at [http4k-mcp-desktop](https://github.com/http4k/mcp-desktop).

## What's Next?

We're committed to evolving the http4k MCP SDK alongside the official specification. Our focus will always remain on making AI integration simpler, more secure, and more testable - without compromise. We will continue to aim to implement the latest (and sensible!) specification changes as soon as possible so our users can take advantage of them, and maintaining our position as first-to-market with a production-ready MCP SDK for the JVM ecosystem.

As always, the http4k MCP SDK is included in our [Pro tier](https://www.http4k.org/pro/) and all [Enterprise Edition subscriptions](https://www.http4k.org/enterprise/), with free use for non-commercial, non-profit organizations and personal projects.

We'd love to hear how you're using these improvements! Reach out on GitHub or the Kotlin Slack with your feedback and questions.

Happy coding!

# /the http4k team
