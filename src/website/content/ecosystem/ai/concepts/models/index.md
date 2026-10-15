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

### Decide

```kotlin
fun interface Decide {
    operator fun invoke(request: DecideRequest): LLMResult<DecideResponse>
}
```

Structured decisions evaluated against a piece of state, rather than free text generation. A `DecideRequest` carries the
state (text, or a list of `Content` such as images), a map of named `Question`s and the model to use. Each question is
answered with a matching `Answer`:

| Question   | Asks for                                 | Answer                                                   |
|------------|------------------------------------------|----------------------------------------------------------|
| `Choice`   | one of a set of named options            | the choice, per-option probabilities, confidence         |
| `Score`    | a position on an ordered list of `Level`s | the score, per-level probabilities, confidence          |
| `YesNo`    | the truth of a single statement          | a probability                                            |

A provider may also answer any question with `Answer.Refusal`. Implementations are available for
[OpenAI](/ecosystem/ai/reference/openai/) and [TypeSafe](/ecosystem/ai/reference/typesafe/).

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
