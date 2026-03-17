---
title: Create an MCP Server in 2 Lines of Code
description: Build a working MCP server with http4k in just a few lines of Kotlin
weight: 5
---

This tutorial gets you from zero to a working MCP server in a few lines of Kotlin. We'll build a clock tool that any MCP host (like Claude Desktop) can call, and we'll do it with almost no ceremony. If you want to add more capabilities and testing, continue to [Add MCP Capabilities](/tutorial/add_mcp_capabilities/). For building interactive UIs inside the host, see [Build an MCP App](/tutorial/build_a_simple_mcp_app/).

> **Prerequisites:** Kotlin, Gradle, Java 21.

# 1. Generate your project

Use the [http4k Toolbox](https://toolbox.http4k.org) to generate a project. Choose "Server-based application", and select the following — everything else can be left at default:

- HTTP server backend - **Jetty** - MCP server backend with support for SSE
- http4k AI integrations - **Model Context Protocol SDK** - MCP SDK dependencies

Finish the Wizard and download the generated project. Your `build.gradle.kts` will include:

```kotlin
dependencies {
    implementation(platform("org.http4k:http4k-bom:${http4kVersion}"))

    implementation("org.http4k:http4k-ai-mcp-sdk")
    implementation("org.http4k:http4k-server-jetty")
}
```

You can delete all of the pre-existing content in the source directories.

# 2. MCP in 30 seconds

[MCP (Model Context Protocol)](https://modelcontextprotocol.io/specification) connects AI models to external capabilities. An MCP **server** exposes capabilities to a **host** application (such as Claude Desktop), which makes them available to the model. The protocol defines four capability types: **Tools** (functions the model can call), **Resources** (data the model can read), **Prompts** (reusable message templates), and **Completions** (argument auto-complete suggestions). See the [http4k MCP SDK docs](/ecosystem/ai/reference/mcp/) for the full integration guide.

# 3. Your first MCP server

Here's a complete, spec-compliant MCP server in two lines of Kotlin:

{{< kotlin file="clock_server.kt" >}}

`Tool` takes a name and description. `bind` attaches a handler that returns the current time as a text response. `asServer` turns any `ServerCapability` into a Streamable HTTP MCP server — run it and you have a working endpoint at `http://localhost:3000/mcp`.

This works because http4k treats every capability as a composable unit. A single `Tool` can become a server on its own — no boilerplate, no registration step, no lifecycle hooks.

# 4. Adding server identity

The one-liner is great for demos, but real servers need identity and security configuration. `mcp()` composes these three concerns — identity, security, and capabilities — into a `PolyHandler`:

{{< kotlin file="clock_server_with_identity.kt" >}}

- **`ServerMetaData`** — gives the server a name and version that hosts display to users.
- **`NoMcpSecurity`** — no auth for local development. Replace with real security for production.
- **`mcp()`** — the composition function that wires identity + security + capabilities into a single handler.

The result is a `PolyHandler` — http4k's type for services that speak multiple protocol types (here HTTP + SSE for the streaming transport).

# 5. Connect to Claude Desktop

Claude Desktop connects to remote MCP servers via **Settings > Connectors**. Since it requires HTTPS, the simplest way to expose your local server is with a [Cloudflare Tunnel](https://developers.cloudflare.com/cloudflare-one/connections/connect-networks/):

```bash
npx cloudflared tunnel --url http://localhost:3000
```

This prints a public URL like `https://xxx-yyy-zzz.trycloudflare.com`.

Then:

1. Start the MCP server (`main()` in `clock_server_with_identity.kt`)
2. Start the Cloudflare tunnel
3. In Claude Desktop, go to **Settings > Connectors** and add a new connector with the tunnel URL (appending `/mcp` — e.g. `https://xxx-yyy-zzz.trycloudflare.com/mcp`)
4. Start a new chat and ask Claude: "What time is it?"
5. Watch it call your clock tool and return the current time

> **Note:** The tunnel URL changes every time you restart `cloudflared`, so you'll need to update the connector each time.

# Recap

| Piece              | What it does                                          |
|--------------------|-------------------------------------------------------|
| `Tool`             | Defines a capability with a name and description      |
| `bind`             | Attaches a handler to a capability                    |
| `asServer`         | Turns any single capability into an MCP server        |
| `mcp()`            | Composes identity + security + capabilities           |
| `ServerMetaData`   | Server name and version                               |
| `NoMcpSecurity`    | No-auth security for local development                |

Next: [Add MCP Capabilities](/tutorial/add_mcp_capabilities/)
