---
title: "http4k MCP Has Landed: Build Your Own AI Agents with Zero Compromise on Testability!"
description: The http4k MCP SDK brings AI integration capabilities to your http4k applications with the same testability-first approach you know and love.
date: 2025-03-28
image: mcp.png
---

<img class="imageMid my-4" src="./http4k-mcp.png" alt="http4k MCP logo"/>

We're thrilled to announce the launch of the http4k [Model Context Protocol (MCP) SDK](https://mcp.http4k.org)! 🚀
This powerful addition to the http4k ecosystem brings seamless AI integration capabilities to your applications through
a clean, functional API that stays true to http4k's core principles.

This module represents our implementation of the [Model Context Protocol](https://modelcontextprotocol.io/) - an open
standard that enables AI systems like Claude to interact with your data, tools, and services. And naturally, we've built
it with the same unwavering commitment to testability that defines everything we do.

The MCP SDK also supports the latest version of the MCP specification which supports stateless and stateful
connections - this is a major advancement in the abilities of the protocol and should unlock mass adoption of the
technology - especially with the recent announcement that OpenAI will also be supporting MCP.

## TL;DR - What is MCP?

MCP (Model Context Protocol) is an open protocol that standardises how AI assistants like Claude can access external
data sources and execute actions through your code. The http4k MCP SDK provides a type-safe, functional Kotlin
implementation that makes it easy to expose your applications' capabilities to AI systems.

With the http4k MCP SDK, you can (amongst other things!):

- Create **Tools** that let AI models perform actions through your code
- Share **Resources** that provide context for AI reasoning
- Define **Prompts** that standardize how users interact with AI
- Build **Agents** that can be deployed to both server and serverless environments.

## Why http4k MCP?

From the beginning, we designed the http4k MCP SDK with the same principles that have made http4k so beloved:

### Testable Design

Just like the rest of http4k, our MCP implementation is built around pure functions with no side effects. This means you
can verify your AI integrations without spinning up servers or making external calls - the entire capability surface can
be tested with simple unit tests.

{{< kotlin file="testable.kt" >}}

### Flexible Transport

The module supports all standard MCP transports, including the latest Streamable HTTP protocol, SSE, WebSocket, and
Standard IO. This means you can run your MCP server anywhere - from cloud platforms to desktop applications.

{{< kotlin file="transport.kt" >}}

### Type-safe Tooling

Our implementation leverages http4k's powerful Lens system to provide type-safe tool definitions and capability
bindings. This gives you compile-time safety when defining your AI integrations.

{{< kotlin file="typesafe.kt" >}}

### Protocol Client

Beyond just serving MCP capabilities, we've included a fully-featured MCP protocol client that you can build directly
into your applications. This enables you to create custom agents and advanced AI workflows by consuming MCP services
programmatically.

{{< kotlin file="client.kt" >}}

## Getting Started

It's incredibly easy to get started with the http4k MCP SDK:

{{< kotlin file="server.kt" >}}

## Desktop Integration with Claude

It's no good having a server if you can't connect it to your favourite LLM - and this is the job of the
[http4k-mcp-desktop](https://github.com/http4k/mcp-desktop), which allows you to connect any MCP server directly to
Claude and other AI assistants with zero coding:

```bash
# Connect your MCP server to Claude
http4k-mcp-desktop --url http://localhost:3000/mcp
```

This opens up endless possibilities for enhancing AI assistants with access to your data and tools.

## Serverless Ready

Deploy your MCP capabilities to AWS Lambda, GCP Functions, and other FaaS platforms using http4k's serverless adapters:

{{< kotlin file="serverless.kt" >}}

## What's Next?

In order to provide the best possible support for our users, we're offering the [http4k MCP SDK](https://mcp.http4k.org)
as a commercially licensed module as a part of our Pro tier and is automatically included in all http4k Enterprise
Edition subscriptions. As with all of the other Pro features, it is free to use for non-commercial, non-profit
organisations and personal use.

The http4k team is just at the start of it's MCP journey, We're diving deeper into what really matters: making AI
development safer, smarter, and more intuitive. Think easier security controls, more graceful ways to build autonomous
agents, and tools that just understand developers. We're not chasing buzzwords—we're creating an environment where
innovation meets responsibility, and complexity transforms into clarity.

**Less vibe, more value. That's the http4k way.**

Happy coding!

# /the http4k team
