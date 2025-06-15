---
title: "http4k AI - Because AI Without Tests is Just Expensive Random Number Generation"
description: "Introducing http4k AI: the first JVM ecosystem that treats AI providers how they should be – testable HTTP APIs with mockable interfaces. Plus: updated commercial license terms to support small business."
date: 2025-06-14
image: circuit.webp
---

<img class="imageMid my-4" src="./circuit.webp" alt="http4k logo"/>

**TL;DR:** Due to significant interest in our existing AI integrations, we've spun off **[http4k AI](/ecosystem/ai)** as its own dedicated ecosystem. v6.14.0.0 (available today) includes a universal LLM API, unified tool model, 5 LLM provider shims, our industry-first MCP SDK with Elicitation support, and LangChain4j integration. We're going hard on AI because the demand is undeniable. **Plus:** We've updated our [commercial license](/commercial-license/) to support qualifying small businesses (under $1M ARR) with free access to Pro modules.

---

<br/>

Since early 2024, we've been quietly building AI integrations within **[http4k-connect](https://connect.http4k.org)**. What started as LLM API adapters and Fake LLM implementations for testing has exploded into our most popular module family. The response from our users has been clear, **there has never been a more important time for testable, observable AI integration libraries**. Well - **[Team http4k](/company)** is happy to step up - and we're building AI tooling that doesn't sacrifice engineering principles for buzzword compliance.

The numbers (and our users!) told the story: our AI modules became the fastest-growing part of http4k-connect. So we made a decision: AI deserves its own ecosystem.

## Why http4k AI?

As is (predictably) normal, AI frameworks are mostly heavyweight, opinionated monsters that hide HTTP details and make testing and observability a nightmare. They force you into vendor lock-in patterns or require real API calls during development – burning tokens and creating unpredictable tests. That's assuming testing was even considered important enough to be included in the first place.

We're building the opposite: lightweight, functional AI tooling that brings determinism and transparency to chaotic AI providers.

## What's In The Box

### 1. Universal, Testable LLM API
One consistent interface across all providers. Build once, swap providers without code changes. Every integration includes comprehensive Fake implementations for deterministic testing using the existing http4k-connect fakes – no API costs, no network dependencies, no surprises.

### 2. Unified Tool Model
Local and remote tools use identical interfaces. Your LLM can call a local function or a remote API through the same mechanism. No special handling, no vendor-specific patterns. http4k brings automatic typesafe tool schema generation to the table through our lens system.

### 3. LLM Provider Shims
We currently ship support for the following model families, using a combination of Chat and Image generation models to integrate into the universal LLM API:
- OpenAI (including GPT-4 and beyond)
- Anthropic (Claude family)
- GitHub Models
- Google Gemini
- Azure (OpenAI-compatible)

All with identical interfaces. All with Fake implementations. All testable offline.

### 4. LangChain4j Integration
We recognise that we can't cover all bases up front, so we're providing support for plugging testable, observable http4k clients into any LangChain4j-compatible AI model, embedding, or vector store. The key words here are **testable** and **observable** – you should be able to see exactly what's happening and the HTTP clients will fit right into both your production and testing standard http4k client stacks.

### 5. Comprehensive Model Context Protocol tools
Our [Pro-tier MCP suite](https://mcp.http4k.org) was the **first JVM MCP SDK released** to implement the latest 2025-03-26 specification including the updated OAuth security model. And we're not just trailing behind, we're implementing new features as they land and releasing them into the wild on a weekly basis.

We've built comprehensive MCP capabilities that go well beyond basic compliance with the spec:

- **Latest spec support**: Streaming HTTP, resumable sessions, and stateless MCP that allows deployment to Serverless platforms with zero code changes
- **Zero-compromise testability**: Test MCP integrations with pure unit tests
- **Type-safe tooling**: Leverages http4k's Lens system for compile-time safety
- **Flexible transport**: HTTP streaming, SSE, WebSockets, Standard IO
- **Full client support**: Build custom agents programmatically

For those that want to stay on the bleeding edge, http4k MCP also implements up-to-date draft MCP features including revised OAuth security model for protected resources and elicitation capabilities. Elicitation is something we're very excited about - it allows dynamic user interfaces presented through the client. Your MCP tools can request additional input from users when needed, creating interactive AI experiences.

## The Roadmap: Going All In

The response to the AI modules has been strong enough that we've found ourselves dedicating significant engineering time to this ecosystem. Here's what's on our radar:

### 1. Google's A2A Protocol
Google recently announced A2A (Agent2Agent) – an open protocol for AI agents to communicate with each other across different frameworks. A2A complements MCP by handling agent-to-agent communication while MCP handles agent-to-tool integration. We'll be giving the spec the http4k treatment, naturally with full testability.

### 2. MCP Evolution
We're staying up to date with MCP developments, implementing draft features as they're integrated into the spec for those adventurous teams who want to stay on the cutting edge. We aim to be among the first with new capabilities as the protocol evolves.

### 3. Build Your Own AI Chat Interface
Framework for building tailored LLM chat clients that connect to MCP servers and local tools. Want your own ChatGPT? You can build it. Includes elicitation support for dynamic UIs and precise control over tool usage.

### 4. More LLM Models
Expanding provider support based on demand. The beauty of our architecture is that adding new providers is straightforward – they're just HTTP APIs with different contracts.

### 5. Agent Testing & Building Tools
Comprehensive tooling for building and testing autonomous agents. Because if you can't test it reliably, you can't ship it reliably.

## Why This Matters

AI is eating software development, but most AI tooling ignores basic engineering principles. Testability and observability aren't nice-to-haves – they're requirements for production systems.

We want [http4k AI](/ecosystem/ai) to bring these principles to AI development:
- **Testable**: Providers have Fake implementations and are fully testable with pure unit tests
- **Observable**: Full access to HTTP layer for debugging
- **Reliable**: Pure functions, immutable data
- **Vendor-neutral**: Consistent interfaces across providers
- **Lightweight**: Minimal dependencies, maximum capability

## Available Now

http4k AI is available now with v6.14.0.0. The basic AI integrations are open source. Advanced features like MCP are available in our [Pro tier](/pro) which remains free for personal use, non-profit organizations, non-commercial research, and qualifying small businesses.

This isn't about jumping on the AI hype train. This is about recognising that AI integration is becoming as fundamental as database connectivity or HTTP handling. And when something becomes fundamental, it needs to be done right.

## Supporting Smaller Teams

Based on feedback from our user community, we've updated our [commercial license](/commercial-license/) to better support smaller organizations. We believe that great engineering tools should be accessible to teams building the future, regardless of their current scale.

Our Pro modules are now free for qualifying small businesses (those with less than $1M annual recurring revenue), alongside personal use, non-profit organizations, and non-commercial research. This change reflects our commitment to supporting smaller outfits as they grow and succeed – because the best ideas often come from teams that aren't yet household names.

We want http4k's advanced AI capabilities in the hands of innovators who are pushing boundaries, not just those with big budgets.

## We Want to Hear From You

We'd love to showcase how teams are using http4k AI in their work! Whether you're building intelligent agents, integrating LLMs into existing systems, or creating innovative MCP tools, we want to hear your story.

Get in touch at [contact@http4k.org](mailto:contact@http4k.org) or find us on the [Kotlin Slack](https://slack.http4k.org) to tell us how http4k AI is helping you succeed with smaller, simpler, testable, and observable code. Your experience could inspire others and help us continue building tools that matter.

The future of AI development is functional, testable, and observable. Welcome to [http4k AI](/ecosystem/ai).

/happy coding!

#### // the http4k team
