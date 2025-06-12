---
type: ecosystem
category: Concept
ecosystem: http4k AI
title: LLM Models
description: An overview of the LLM generation concepts in http4k AI.
---

http4k AI provides a **unified, type-safe and functional API** for interacting with various Large Language Models (LLMs)
and AI services. Rather than dealing with vendor-specific APIs, you get consistent functional interfaces that work
across different providers. As with all things http4k, the focus is on simplicity, composability, and functional
programming principles, rather than complex abstractions and magic.

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
