---
title: http4k AI
description: About the http4k AI ecosystem.
ecosystem: http4k AI
type: ecosystem
weight: 2
image : "/images/logo-ai.png"
---

http4k AI is a lightweight AI integration toolkit which provides simplified adapters for connecting to popular LLM providers and AI services using [http4k](https://http4k.org) compatible APIs, along with comprehensive Fake implementations for deterministic testing. These are all underpinned by the uniform [Server as a Function](https://monkey.org/~marius/funsrv.pdf) model powered by the `HttpHandler` interface exposed by [http4k Core](https://www.http4k.org/ecosystem/http4k/), so you can:

1. Build AI-powered applications using http4k's proven functional design patterns.
2. Test AI integrations completely offline with predictable, deterministic behavior.
3. Access underlying HTTP clients for observability, metrics, and debugging.
4. Run comprehensive Fake AI services locally without external dependencies or API costs.
5. Avoid vendor lock-in through consistent, composable interfaces.

Although designed for http4k-based projects, http4k-ai libraries are usable from any JVM application.

## Rationale
Most AI integration libraries are heavyweight, opinionated frameworks that hide HTTP details and make testing difficult. They often require real API calls during development, leading to unpredictable tests and mounting token costs. http4k-ai provides a lightweight alternative focused on:

- **Testability First**: Every AI service includes a full Fake implementation for deterministic testing
- **Functional Design**: Pure functions and immutable data structures throughout
- **HTTP Transparency**: Full access to underlying HTTP for debugging and observability
- **Minimal Dependencies**: Lightweight implementations covering essential use cases
- **Vendor Neutrality**: Consistent interfaces across different AI providers

## Installation
```kotlin
dependencies {
    // install the platform...
    {{< http4k_bom >}}

    // ...then choose an AI provider
    implementation("org.http4k:http4k-ai-openai")

    // ...a fake for testing (essential)
    testImplementation("org.http4k:http4k-ai-openai-fake")
}
