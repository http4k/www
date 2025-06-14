---
category: Reference
type: ecosystem
ecosystem: http4k AI
title: "GeminiAI"
description: Feature overview of the http4k AI Gemini modules
---

### Installation

```kotlin
dependencies {
    {{< http4k_bom >}}

    // for the Universal LLM adapter (uses the OpenAI fake)
    implementation("org.http4k:http4k-ai-llm-gemini")
    implementation("org.http4k:http4k-connect-openai-fake")
}
```

The http4k-ai Gemini integration provides:
- Universal LLM adapter
- Testing support using the [FakeOpenAI](../openai) connect module

## Universal LLM adapter

The Universal LLM adapter converts the http4k LLM interface into the underlying API, allowing you to swap out providers without changing your application code.

{{< kotlin file="universal_adapter.kt" >}}
