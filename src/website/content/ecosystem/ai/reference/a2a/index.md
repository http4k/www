---
category: Reference
type: ecosystem
tier: pro
ecosystem:
  - http4k AI
  - http4k Enterprise
title: "A2A SDK"
description: Feature overview of the http4k-ai-a2a modules
aliases:
    - /ecosystem/http4k/reference/a2a/
---

### Installation (Gradle)

```kotlin
dependencies {
    {{< http4k_bom >}}

    // for A2A server development
    implementation("org.http4k.pro:http4k-ai-a2a-sdk")

    // for connecting to A2A agents
    implementation("org.http4k.pro:http4k-ai-a2a-client")

    // for exposing an A2A agent to MCP clients (the MCP bridge)
    implementation("org.http4k.pro:http4k-ai-mcp-a2a-bridge")
}
```

## About

The [Agent2Agent (A2A) Protocol](https://a2a-protocol.org/) is an open standard designed to facilitate communication and interoperability between independent AI agent systems. In an ecosystem where agents might be built using different frameworks, languages, or by different vendors, A2A provides a common language and interaction model.

A2A supports two protocol bindings:

- **JSON-RPC:** Single endpoint for all operations. Streaming via Server-Sent Events on the same path.
- **REST/HTTP:** RESTful endpoints for tasks, messages, and push notification configs. Streaming via SSE.

Both bindings support the same capabilities: Agent Cards, Tasks, Messages, Artifacts, Streaming, Push Notifications, and multi-turn conversations.

## http4k and Agent2Agent

http4k provides a complete, type-safe implementation of the A2A protocol - both server and client - using the familiar http4k patterns of composable functional protocols. The core extension point is the `MessageHandler`, a simple function that receives a message and returns a response.

```kotlin
typealias MessageHandler = (MessageRequest) -> MessageResponse
```

A `MessageHandler` can return:
- A **Task** for long-running operations with state tracking
- A **Message** for immediate responses
- A **ResponseStream** for streaming multiple results

# Server: http4k-ai-a2a-sdk

## Creating a Server

The simplest way to create an A2A server is to define an `AgentCard` and a `MessageHandler`:

{{< kotlin file="server_example.kt" >}}

Both `a2aJsonRpc()` and `a2aRest()` return a `PolyHandler` which can be served by any http4k server backend.

## Agent Cards

Agent Cards describe your agent's identity, capabilities, and skills. They are served at `/.well-known/agent-card.json` and discovered by clients automatically.

{{< kotlin file="agent_card_example.kt" >}}

For agents that expose additional capabilities to authenticated users, use `AgentCardProvider` to serve both standard and extended cards:

{{< kotlin file="extended_card_example.kt" >}}

## Streaming Responses

To stream responses back to clients, return a `ResponseStream` from your handler. The stream is delivered via Server-Sent Events:

{{< kotlin file="streaming_example.kt" >}}

## Task Management

Tasks are the core unit of work in A2A. The server automatically manages task storage and lifecycle. You can provide custom storage implementations:

{{< kotlin file="task_storage_example.kt" >}}

## Push Notifications

Push notifications allow agents to notify clients of task status changes via webhooks:

{{< kotlin file="push_notification_example.kt" >}}

## Multi-tenancy

Both protocol bindings support multi-tenant routing. REST endpoints accept an optional `/{tenant}` path prefix, and JSON-RPC passes tenant via request parameters:

{{< kotlin file="multi_tenant_example.kt" >}}

## Message Filters

Similar to http4k's `Filter`, the `MessageFilter` allows cross-cutting concerns to be applied to all message handling:

{{< kotlin file="message_filter_example.kt" >}}

# Client: http4k-ai-a2a-client

## Connecting to an Agent

{{< kotlin file="client_example.kt" >}}

## Streaming Messages

{{< kotlin file="client_streaming_example.kt" >}}

## Task Operations

{{< kotlin file="client_tasks_example.kt" >}}

# Exposing an A2A Agent to MCP Clients

The `http4k-ai-mcp-a2a-bridge` module wraps any A2A agent as an [MCP](/ecosystem/ai/reference/mcp/) HTTP server. This lets an LLM in any MCP client (Claude Desktop, Cursor, etc.) discover and talk to your A2A agent without writing custom MCP integration code.

The bridge fetches the agent card once at startup and folds the name, description, and skill catalog into the `send_message` tool description, then exposes four MCP tools: `send_message`, `get_task`, `cancel_task`, and `list_tasks`. Responses come back as MCP `structuredContent` carrying the full A2A `Task` / `Message` shape - including `taskId` and `contextId` - so the LLM can chain follow-up calls.

The inbound MCP request's `Authorization` header is forwarded to the A2A agent on every tool call, so each MCP caller authenticates as itself.

{{< kotlin file="mcp_bridge_example.kt" >}}

For finer control - custom auth headers, a pre-built `A2AClient`, or composing the bridge alongside other MCP capabilities - use `mcpA2aBridge(...)` directly and wrap it with `mcp(...)` yourself.

# Testing

A2A servers can be tested fully in-memory without starting a real server, using the test client factories:

{{< kotlin file="testing_example.kt" >}}
