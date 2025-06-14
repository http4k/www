---
draft: true
title: "http4k AI - Because AI Without Tests is Just Expensive Random Number Generation"
description: "Introducing http4k AI: the first JVM ecosystem that treats AI providers as what they really are – testable HTTP APIs with predictable contracts."
date: 2025-06-14
image: circuit.webp
---

<img class="imageMid my-4" src="./circuit.webp" alt="http4k logo"/>

**TL;DR:** Due to significant interest in our existing AI integrations, we've spun off **[http4k AI](/ecosystem/ai)** as its own dedicated ecosystem. v6.14.0.0 (available today) includes a universal LLM API, unified tool model, 5 LLM provider shims, our industry-first MCP SDK with Elicitation support, and LangChain4j integration. We're going hard on AI because the demand is undeniable.

---

<br/>

Since early 2024, we've been quietly building AI integrations within **[http4k-connect](https://connect.http4k.org)**. What started as LLM API adapters and Fake LLM implementations for testing has exploded into our fastest-growing module family. The response from our users has been clear, despite what the hype-train drivers will tell you - **there has never been a more important time for testable, observable code**. Well - **[Team http4k](/company)** is happy to step up - and we're building AI tooling that doesn't sacrifice engineering principles for buzzword compliance.

The numbers (and our users!) told the story: our AI modules became the fastest-growing part of http4k-connect. So we made a decision. AI deserves its own ecosystem.

## Why http4k AI?

As is (sadly) normal, JVM AI frameworks are mostly heavyweight, opinionated monsters that hide HTTP details and make testing and observability a nightmare. They force you into vendor lock-in patterns or require real API calls during development – burning tokens and creating unpredictable tests.

We're building the opposite: lightweight, functional, testable AI tooling that treats AI providers as what they are – HTTP APIs with predictable contracts.

## What's In The Box

### 1. Universal, Testable LLM API
One consistent interface across all providers. Build once, swap providers without code changes. Every integration includes comprehensive Fake implementations for deterministic testing using the existing http4k-connect fakes – no API costs, no network dependencies, no surprises.

### 2. Unified Tool Model
Local and remote tools use identical interfaces. Your LLM can call a local function or a remote API through the same mechanism. No special handling, no vendor-specific patterns.

### 3. LLM Provider Shims
We currently are shipping support for the following model families, using a combination of Chat and Image generation models:
- OpenAI (including GPT-4 and beyond)
- Anthropic (Claude family)
- GitHub Models
- Google Gemini
- Azure (OpenAI-compatible)

All with identical interfaces. All with Fake implementations. All testable offline.

### 4. LangChain4j Integration
We recognise that we can't cover all bases up front, so we're providing support for plugging testable, observable http4k clients into any LangChain4j-compatible AI model, embedding, or vector store. The key words here are **testable** and **observable** – you can see exactly what's happening and the HTTP clients will fit right into both your production and testing standard http4k client stacks.

## Model Context Protocol: The Crown Jewel

Our [Pro-tier MCP suite](https://mcp.http4k.org) was the **first JVM MCP SDK released** to implement the latest 2025-03-26 specification including the updated OAuth security model. And we're not just trailing behind, we're implementing new features as they land and releasing them into the wild on a weekly basis.

### What is MCP?
The Model Context Protocol, introduced by Anthropic in November 2024, standardizes how AI systems access external data sources and tools. It's becoming the de-facto standard for AI-application integration.

### http4k MCP Goes Beyond
- **Latest spec support**: Stateless and stateful connections, OAuth security
- **Zero-compromise testability**: Test MCP integrations with pure unit tests
- **Type-safe tooling**: Leverages http4k's Lens system for compile-time safety
- **Flexible transport**: HTTP streaming, SSE, WebSockets, Standard IO
- **Full client support**: Build custom agents programmatically

### Draft Features & Elicitation Support
We're also implementing draft MCP features including OAuth security for protected resources and elicitation capabilities. Elicitation is something we're very excited about - it allows dynamic user interfaces presented through the client – your MCP tools can request additional input from users when needed, creating interactive AI experiences.

## The Roadmap: Going All In

The response to the AI modules has been strong enough that we've found ourselves dedicating significant engineering time to this ecosystem. Here's what's next:

### 1. Google's Agent2Agent Protocol
Google announced A2A (Agent2Agent) in April 2025 – an open protocol for AI agents to communicate with each other across different frameworks. A2A complements MCP by handling agent-to-agent communication while MCP handles agent-to-tool integration. We'll be giving the spec the http4k treatment, naturally with full testability.

### 2. MCP Evolution
We're staying on the bleeding edge of MCP development, implementing draft features as they're integrated into the spec for those adventurous teams who want to stay on the cutting edge. Expect us to be first with new capabilities as the protocol evolves.

### 3. Custom Graphical LLM Clients
A framework for building your own customized chat clients that interface with MCP and local tools. Want your own ChatGPT? Build it. Includes elicitation support for dynamic UIs and fine-grained tool consumption controls.

### 4. More LLM Models
Expanding provider support based on demand. The beauty of our architecture is that adding new providers is straightforward – they're just HTTP APIs with different contracts.

### 5. Agent Testing & Building Tools
Comprehensive tooling for building and testing autonomous agents. Because if you can't test it reliably, you can't ship it reliably.

## Why This Matters

AI is eating software development, but most AI tooling ignores basic engineering principles. Testability, observability, and predictable behavior aren't nice-to-haves – they're requirements for production systems.

[http4k AI](/ecosystem/ai) brings these principles to AI development:
- **Testable**: Everything has Fake implementations
- **Observable**: Full access to HTTP layer for debugging
- **Predictable**: Pure functions, immutable data
- **Vendor-neutral**: Consistent interfaces across providers
- **Lightweight**: Minimal dependencies, maximum capability

## Available Now

http4k AI is available now with v6.14.0.0. The basic AI integrations are open source. Advanced features like MCP are available in our [Pro tier](/pro) which remains free for non-profit and non-commercial use.

This isn't about jumping on the AI hype train. This is about recognizing that AI integration is becoming as fundamental as database connectivity or HTTP handling. And when something becomes fundamental, it needs to be done right.

The future of AI development is functional, testable, and observable. Welcome to [http4k AI](/ecosystem/ai).

/happy coding!

#### // the http4k team
