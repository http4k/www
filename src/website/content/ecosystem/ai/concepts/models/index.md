---
type: ecosystem
category: Concept
ecosystem: http4k AI
title: LLM Models
description: An overview of the LLM generation concepts in http4k AI.
---

The http4k AI/LLM module provides a **unified, type-safe API** for interacting with various Large Language Models (LLMs)
and AI services. Rather than dealing with vendor-specific APIs, you get consistent interfaces that work across different
providers.

## Core Interfaces

### Chat

```kotlin
fun interface Chat {
    operator fun invoke(request: ChatRequest): LLMResult<ChatResponse>
}
```

Standard synchronous chat completions for request-response interactions.

### StreamingChat

```kotlin
fun interface StreamingChat {
    operator fun invoke(request: ChatRequest): LLMResult<Sequence<ChatResponse>>
}
```

Real-time streaming responses for applications requiring incremental text generation.

### ImageGeneration

```kotlin
fun interface ImageGeneration {
    operator fun invoke(request: ImageRequest): LLMResult<ImageResponse>
}
```

AI-powered image generation from text prompts.

## Result-Based Error Handling

All operations return `LLMResult<T>` - a `Result4k` type that forces explicit error handling:

```kotlin
typealias LLMResult<T> = Result4k<T, LLMError>
```

**LLM Error Types:**

- `Http` - HTTP-level failures
- `Timeout` - Request timeouts
- `NotFound` - Missing resources
- `Internal` - System errors
- `Custom` - Provider-specific errors
