---
title: "http4k AI - Because AI Without Tests is Just Expensive Random Number Generation"
description: "http4k AI brings deterministic, testable AI integrations to the JVM - comprehensive fakes, universal APIs, zero token costs in tests. Built on 8 years and 50+ million downloads. Plus: updated commercial license terms to support small business."
date: 2025-06-14
image: circuit.webp
---

<img class="imageMid my-4" src="./circuit.webp" alt="http4k logo"/>

**TL;DR:** We're launching **[http4k AI](/ecosystem/ai)** - a dedicated ecosystem for building testable, observable AI integrations on the JVM, backed by 8 years of experience and 50+ million downloads. v6.14.0.0 ships today with universal LLM APIs, comprehensive fakes, MCP SDK with bleeding-edge Elicitation capabilities, and 5 major provider integrations. **Plus:** Updated [commercial license](/commercial-license/) now supports qualifying small businesses (under $1M ARR) with free Pro access.

---

<br/>

## Why We're Building http4k AI

Since [mid 2023](https://github.com/http4k/http4k-connect/releases/tag/3.39.1.0), we've been building AI integrations into **[http4k-connect](https://connect.http4k.org)** - LLM API adapters and fake implementations following the same patterns as modules targeting AWS and friends. While we were building, we became convinced that the JVM AI landscape has a testing problem: while there are "AI mocks" that allow you to test your code by replacing the model entirely, they don't let you test the actual AI interactions your code is making, simulate realistic failure modes, or generate consistent multimedia content for testing workflows.

Our users noticed immediately. These became our most asked about Connect modules, with common feedback along the lines of "Finally, AI integrations that actually work in all my tests!" The message was clear: AI tooling needs the http4k treatment.

Just like with the http4k core that we built in 2017, we're building what we wish existed: lightweight, functional AI tooling that brings determinism and transparency to the chaos of LLM behaviour.

## What's In The Box

### 1. Universal, Testable LLM API

A Kotlin-native API that doesn't feel like a Java port. Sure, you create OpenAI and Anthropic adapters differently, but once you have them, all chat calls work identically. The real win: http4k's pluggable HTTP clients that slot right into your existing stack - tracing, logging, observability - all there via standard http4k Filters and Events. Plus comprehensive fakes that can generate realistic text, images, or structured data - perfect for testing failure scenarios like rate limits, malformed responses, or content moderation blocks. Test complete AI workflows including multimedia generation and chat memory persistence completely in-memory without burning tokens or depending on external services.

### 2. Unified Tool Model
Your LLM can call a local function or hit a remote API through identical interfaces. No vendor-specific tool handling, no special cases. http4k's lens system generates typesafe tool schemas automatically.

### 3. 5 Major LLM Providers
OpenAI, Anthropic, GitHub Models, Google Gemini, and Azure - all with identical model interfaces (chat/streaming chat/image), all with supported offline fakes, all benefiting from http4k's pluggable HTTP ecosystem.

### 4. LangChain4j Bridge
We can't support every model type and AI primitive immediately, so this bridge opens up LangChain4j's full catalogue to http4k users. Our pluggable HTTP client brings the same observability and testability to LangChain models (note: not all LangChain models support this yet as they migrate to their HTTP client model).

### 5. Model Context Protocol That's Actually Testable
Released just 2 days after it landed, our [Pro-tier MCP suite](https://mcp.http4k.org) was the **first JVM MCP SDK released** to implement the latest 2025-03-26 specification including the updated OAuth security model. And we're not just trailing behind, we're implementing new features as they land and releasing them into the wild on a weekly basis.

We've built comprehensive MCP capabilities that go well beyond basic compliance with the spec:

- **Latest spec support**: Streaming HTTP, resumable sessions, sampling, and stateless MCP that allows deployment to Serverless platforms with zero code changes to your app
- **Zero-compromise testability**: Test MCP integrations with pure unit tests
- **Type-safe tooling**: Leverages http4k's Lens system for compile-time safety
- **Flexible transport**: HTTP streaming, SSE, WebSockets, Standard IO
- **Full client support**: Build custom agents programmatically

For those that want to stay on the bleeding edge, http4k MCP also implements up-to-date draft MCP features including [Tool Output schemas](https://modelcontextprotocol.io/specification/draft/server/tools#output-schema), the revised [OAuth security model for protected resources](https://modelcontextprotocol.io/specification/draft/basic/authorization#standards-compliance), and [Elicitation](https://modelcontextprotocol.io/specification/draft/client/elicitation) capabilities. Elicitation is something we're very excited about - it allows dynamic user interfaces presented through the client. Your MCP tools can request additional input from users when needed, creating interactive AI experiences. Expect a demo soon!

## What's Next

[Team http4k](/company) is just getting started. Here's what we're working on next:

### Intelligent Tool Orchestration
Automatic tool calling system that lets you plug in any combination of MCP servers, local functions, and remote APIs. Define your tools, start a chat session, and the LLM automatically decides when and how to call them based on user input. Think "ChatGPT with custom tools" but with full E2E http4k testability - fake custom LLM responses and tool calls, or test complex multi-tool workflows in pure unit tests.

### Google's A2A Protocol
Google recently announced A2A (Agent2Agent) – an open protocol for AI agents to communicate with each other across different frameworks. A2A complements MCP by handling agent-to-agent communication while MCP handles agent-to-tool integration. We'll be implementing A2A with the same testability and observability principles.

### Expanding what's possible with Testing & Observability
We've got the testing basics covered, but that's never been good enough! We're planning to supercharge this with ideas like seamless universal LLM simulation across the testing pyramid - bringing the same best-in-class testing approach that made http4k the go-to choice for testable HTTP services. Plus deep observability integration leveraging our years of experience with HTTP observability patterns - automatic token usage tracking, OpenTelemetry spans for AI interactions, and pluggable metrics for monitoring LLM performance in production. The goal: make AI operations as observable and testable as any other HTTP service in your stack, with the same engineering rigor http4k is known for.

### Expanding Provider / LLM Primitive Support
http4k AI currently supports the core LLM primitives: Chat/Streaming Chat, Image Generation, and Chat Memory (with in-memory storage or any of our pluggable storage backends). We'll be expanding this to cover Embeddings, vector operations, enhanced chat memory management, and other AI primitives in the near future. Our architecture makes new providers and primitives straightforward to add while maintaining the universal interface. And hey - if you want to contribute a new provider, we're happy to accommodate!

## Available Now

http4k AI ships today with v6.14.0.0. Like most of http4k, the base modules join our 180+ open source integrations. The advanced features (MCP, upcoming tool orchestration) live in our [Pro-tier](/pro), but we've got news about that below.

Ready to build testable AI? **[Get going!](/ecosystem/ai)**

## Supporting Smaller Teams

Here's some good news: we've updated our [commercial license](/commercial-license/) so qualifying small businesses (under $1M ARR) get free access to Pro modules, just like personal users and non-profits.

Why? Because the best innovations often come from scrappy teams who don't have enterprise budgets yet. We want http4k's simplicity and rock-solid testing capabilities in the hands of people building cool stuff, not just those with big wallets.

Advanced MCP, tool orchestration, and future Pro features are now free for smaller teams. Go build something awesome.

## We Want to Hear From You

We'd love to showcase how teams are using http4k AI in their work! Whether you're building intelligent agents, integrating LLMs into existing systems, or creating innovative MCP tools, we want to hear your story.

Get in touch at [contact@http4k.org](mailto:contact@http4k.org) or find us on the [Kotlin Slack](https://slack.http4k.org) to tell us how http4k AI is helping you succeed with smaller, simpler, testable, and observable code. Your experience could inspire others and help us continue building tools that matter.

The future of AI development is functional, testable, and observable. Welcome to [http4k AI](/ecosystem/ai).

/happy coding!

#### // the http4k team
