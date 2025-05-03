---
title: "Simplifying MCP: http4k's Updated Authentication Model - Less Code, More Power"
description: The http4k MCP SDK has been updated to support the latest Draft OAuth specification,
    enhancing security and flexibility in AI integrations.
date: 2025-05-03
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

The newest (2025-3-26)  MCP authentication specification was widely considered too complicated for server creators to implement effectively. Based on consistent feedback from our SDK users, we thoroughly agreed that a simplification was required. Front Front remains the latest changes in the MCP community, we've embraced a new draft spec that dramatically reduces this complexity while maintaining robust security.

For http4k, developer experience has always been our north star. Along with testability, simplicity, and functional purity, we believe that working with our libraries should feel natural and frictionless. These changes reflect that ongoing commitment to putting developers first.

## One-Line Server Setup

Setting up an MCP server with proper authentication is now as simple as using the new simplified auth model with just one parameter addition. This small change means you can create a fully compliant MCP server with minimal ceremony - exactly how http4k should be!

{{< kotlin file="server.kt" >}}

## Improved Client API

We've also made connecting to MCP servers more intuitive with our streamlined client connection API which
is implemented via a simple Filter. The same simple API for interacting with the server remains the same.

{{< kotlin file="client.kt" >}}

## Still Fully Testable

True to http4k's principles, these changes maintain complete testability, allowing you to test authentication flows directly without spinning up real servers.

## Staying Ahead of the Curve

Despite what you may have read elsewhere, the http4k MCP SDK is currently the only production-ready way to run distributed MCPs in JVM cloud-based or serverless environments. While official JVM MCP SDKs are still catching up with even the previous spec version, http4k gives you access to the latest features today.

## Get Started Today

The latest version is available now through Maven Central. For http4k MCP desktop integration, this update is also available via Homebrew and GitHub releases. Check it out at [http4k-mcp-desktop](https://github.com/http4k/mcp-desktop).

## What's Next?

We're committed to evolving the http4k MCP SDK alongside the MCP specification. Our focus will always remainson making AI integration simpler, more secure, and more testable - without compromise.

As always, the http4k MCP SDK is included in our Pro tier and all Enterprise Edition subscriptions, with free use for non-commercial, non-profit organizations and personal projects.

We'd love to hear how you're using these improvements! Reach out on GitHub or the Kotlin Slack with your feedback and questions.

Happy coding!

# /the http4k team

---

*We will continue to support MCP and implement the latest specification changes well ahead of any official SDK, maintaining our position as first-to-market with production-ready MCP implementations for the JVM ecosystem. Developer experience, testability, and functional elegance will always remain our guiding principles.*
