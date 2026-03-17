---
title: Add MCP Capabilities
description: Add typed tool arguments, resources, prompts, and in-memory testing to an http4k MCP server
weight: 6
---

In [Create an MCP Server](/tutorial/create_an_mcp_server/) we built a working MCP server with a single tool. Now we'll add the full range of MCP capabilities: a tool with typed arguments, a resource, and a prompt. Then we'll test the whole thing in-memory — no network, no running server, no squinting at Claude's output.

By the end we'll have a Greeter server that exposes all three capability types, composed into a single server and fully tested.

> **Prerequisites:** Completed [Create an MCP Server](/tutorial/create_an_mcp_server/). Kotlin, Gradle, Java 21.

Add the testing module to your `build.gradle.kts`:

```kotlin
dependencies {
    implementation(platform("org.http4k:http4k-bom:${http4kVersion}"))

    implementation("org.http4k:http4k-ai-mcp-sdk")
    implementation("org.http4k:http4k-server-jetty")

    testImplementation("org.http4k:http4k-ai-mcp-testing")
}
```

# 1. A tool with typed arguments

Our clock tool in Tutorial 1 took no arguments. Most real tools need input. http4k provides a typed argument DSL that builds the JSON Schema for the tool's parameters and gives you type-safe lenses to extract values from requests:

{{< kotlin file="GreetTool.kt" >}}

- **`Tool.Arg.string()`** — creates a string argument lens. This is the standard http4k lens system — the full range of types (`int()`, `boolean()`, `enum<>()`, and more) is available.
- **`.required("name", "Who to greet")`** — makes the argument mandatory and adds a description for the schema. Use `.optional()` for arguments with defaults.
- **`name(it)`** — extracts the value from the tool request using the lens. This is the same lens pattern used throughout http4k.
- **`bind`** — connects the `Tool` definition (with its argument schema) to the handler. The `it` parameter is the incoming `ToolRequest`.

The tool definition includes the arg so that http4k automatically generates the correct JSON Schema — hosts use this to validate arguments before calling your tool.

# 2. A resource

Resources provide data that the model can read. They use URIs to identify content and return it in a structured response:

{{< kotlin file="GreetingGuidelines.kt" >}}

- **`Resource.Static`** — creates a static resource with a URI and description. The URI scheme is up to you (`greeting://` here).
- **`ResourceResponse`** — wraps the content. `Resource.Content.Text` takes the text content and the resource URI.
- Resources are read-only — the model can fetch them but not modify them. They're ideal for providing context, configuration, or reference data.

# 3. A prompt

Prompts are reusable message templates that users can select in the host UI. They give the model structured instructions rather than relying on ad-hoc chat messages:

{{< kotlin file="FormalGreeting.kt" >}}

- **`Prompt.Arg`** — similar to `Tool.Arg`, defines typed arguments for the prompt. Here we take the person's name.
- **`PromptResponse`** — returns a message with a role and content. The model receives this as a user message.
- The prompt references both the resource (`greeting://guidelines`) and the tool (`greet`) — this teaches the model to use the server's other capabilities together.

# 4. Composing capabilities

A `CapabilityPack` groups multiple capabilities into a single unit. This is how you build modular MCP servers — each pack is an independent bundle of related capabilities, and `mcp()` composes them all:

{{< kotlin file="GreeterServer.kt" >}}

- **`CapabilityPack`** — accepts any mix of `ServerCapability` instances (tools, resources) and other packs. It implements `ServerCapability` itself, so packs compose into larger packs.
- **`mcp()`** — the same composition function from Tutorial 1, now with multiple capabilities. Capabilities and packs are passed as varargs — here the `CapabilityPack` and `greetingPrompt` sit side by side.
- We've extracted the server into a function (`GreeterServer()`) so we can reuse it in tests without starting a real server.

# 5. Testing with testMcpClient()

The `http4k-ai-mcp-testing` module provides `testMcpClient()` — an in-memory MCP client that connects directly to your server handler. No network, no ports, no flaky CI:

{{< kotlin file="GreeterServerTest.kt" >}}

`testMcpClient()` exercises the full MCP protocol stack in-memory — serialisation, routing, argument validation, everything. If it passes here, it'll work over the wire.

# Recap

| Capability | What it does                                        | How the model uses it                    |
|------------|-----------------------------------------------------|------------------------------------------|
| **Tool**   | Function the model can call with typed arguments    | Calls it to perform actions or get data  |
| **Resource** | Read-only data identified by URI                  | Reads it for context or reference        |
| **Prompt** | Reusable message template with typed arguments      | User selects it to give structured instructions |

| Composition piece   | What it does                                        |
|---------------------|-----------------------------------------------------|
| `CapabilityPack`    | Groups capabilities into a reusable unit            |
| `mcp()`             | Composes identity + security + capabilities         |
| `testMcpClient()`   | In-memory test client — full protocol, no network   |

Next: [Build an MCP App](/tutorial/build_a_simple_mcp_app/)
