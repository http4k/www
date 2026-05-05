---
title: Build an A2A Agent
description: Build an Agent2Agent agent with http4k that streams task updates
weight: 8
series: Building A2A Agents
---

This tutorial walks through building a **Recipe Agent** — an A2A agent that receives messages from clients, streams back task updates, and advertises its capabilities via an Agent Card.

The [Agent2Agent (A2A) Protocol](https://a2a-protocol.org/) is an open standard for communication between independent AI agents. Where MCP connects models to tools and data, A2A connects agents to each other — letting them discover capabilities, exchange messages, and coordinate work.

By the end you will have:

- An Agent Card describing your agent's identity and skills
- A streaming message handler that returns task updates
- An in-memory test suite with no network required
- A client that discovers and talks to your agent

> **Prerequisites:** Kotlin, Gradle, Java 21. Familiarity with [http4k](https://http4k.org) basics.

# A2A in 60 seconds

An A2A agent exposes three things:

- **Agent Card** — metadata describing who the agent is, what it can do, and how to talk to it. Served at `/.well-known/agent-card.json` so clients can discover it automatically.
- **MessageHandler** — a function `(MessageRequest) -> MessageResponse` that does the actual work. It can return a `Message` (immediate reply), a `Task` (long-running operation), or a `ResponseStream` (multiple streamed results).
- **Tasks** — the unit of work in A2A. Each task has a state (`WORKING`, `COMPLETED`, `FAILED`, etc.) and can carry messages back to the client.

http4k provides `a2aJsonRpc()` — a single function that wires an Agent Card and a MessageHandler into a server. That's the whole setup.

# 1. Project setup

Use the [http4k Toolbox](https://toolbox.http4k.org) to generate a project. In the **Core** step, select **A2A Agent (Pro)**. Everything else can be left at default.

Your `build.gradle.kts` will include:

```kotlin
dependencies {
    implementation(platform("org.http4k:http4k-bom:${http4kVersion}"))

    implementation("org.http4k.pro:http4k-ai-a2a-sdk")

    implementation("org.http4k:http4k-server-jetty")

    testImplementation("org.http4k.pro:http4k-ai-a2a-client")
}
```

You can delete all of the pre-existing content in the source directories.

# 2. Agent Card

The Agent Card is your agent's public identity. Clients fetch it to learn what the agent does before sending any messages.

{{< kotlin file="agentCard.kt" >}}

Key points:

- **`AgentCapabilities(streaming = true)`** — advertises that this agent supports streaming responses via Server-Sent Events.
- **`defaultInputModes` / `defaultOutputModes`** — declares the MIME types the agent accepts and produces.
- **`AgentSkill`** — each skill has an ID, name, description, and tags. Clients use these to decide whether an agent can handle a given request.

# 3. Message handler

This is the core of the agent. `a2aJsonRpc()` takes an Agent Card and a `MessageHandler` function and returns a server-ready `PolyHandler`.

{{< kotlin file="RecipeAgent.kt" >}}

Key points:

- **`a2aJsonRpc()`** — wires the agent card and handler into a JSON-RPC server. For REST-style endpoints, use `a2aRest()` instead.
- **`request.message.parts`** — messages contain typed parts. Here we extract `Part.Text` values to build the query string.
- **`ResponseStream`** — wraps a `Sequence` of task updates that are streamed to the client via SSE.
- **Task state transitions** — the handler emits two updates: first `TASK_STATE_WORKING` (with the original message in `history`), then `TASK_STATE_COMPLETED` with the result in the status message. This gives clients real-time visibility into progress.

# 4. Start the server

{{< kotlin file="agentMain.kt" >}}

Run this and your agent is listening on `http://localhost:9000`. The Agent Card is available at `http://localhost:9000/.well-known/agent-card.json`.

# 5. Test it

A2A servers can be tested fully in-memory using `testA2AJsonRpcClient()`. No network, no ports, no waiting — just fast, deterministic tests.

{{< kotlin file="RecipeAgentTest.kt" >}}

Key points:

- **`RecipeAgent().testA2AJsonRpcClient()`** — creates an in-memory client wired directly to the agent's `PolyHandler`. The full A2A protocol stack runs, but no HTTP server is started.
- **Agent Card test** — verifies the card is discoverable and matches the expected definition.
- **Streaming test** — sends a message via `messageStream()`, collects the streamed items, and asserts on the task state transitions from `WORKING` to `COMPLETED`.

# 6. Build a client

This client connects to a running agent, discovers its capabilities, and sends a streaming message:

{{< kotlin file="clientMain.kt" >}}

Key points:

- **`HttpA2AClient`** — connects to a JSON-RPC agent at the given URI. For REST agents, use `RestA2AClient`.
- **`client.agentCard()`** — fetches the Agent Card to discover what the agent can do.
- **`client.messageStream()`** — sends a message and returns a `ResponseStream` of task updates, streamed via SSE.
- **`client.use { ... }`** — the client is `Closeable`, so `use` ensures it's cleaned up.

# Recap

| Piece           | File              | Role                                              |
|-----------------|-------------------|----------------------------------------------------|
| Agent Card      | `agentCard.kt`    | Agent identity, capabilities, and skills           |
| Message handler | `RecipeAgent.kt`  | Core logic: receives messages, streams task updates |
| Server          | `agentMain.kt`    | Starts the agent on Helidon                        |
| Tests           | `RecipeAgentTest.kt` | In-memory tests with no network                 |
| Client          | `clientMain.kt`   | Discovers agent and sends streaming messages       |
