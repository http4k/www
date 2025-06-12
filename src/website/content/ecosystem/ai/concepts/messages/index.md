---
type: ecosystem
category: Concept
ecosystem: http4k AI
title: Messages and Content
description: An overview of the LLM generated content model in http4k AI.
---

The http4k AI/LLM module uses **sealed class hierarchies** to provide type-safe, compile-time validated message structures with limited, well-defined options.

## Message Types

There are five main message types, each serving a specific purpose in the conversation flow:

```kotlin
sealed class Message {
    // Instructions and context for the AI
    data class System(val text: String) : Message()
    
    // Human input with multimodal content
    data class User(val contents: List<Content>) : Message() 
    
    // AI responses with optional tool requests
    data class Assistant(val contents: List<Content>, val toolRequests: List<ToolRequest>) : Message()

    // Results from tool executions
    data class ToolResult(val id: RequestId, val tool: ToolName, val text: String) : Message()

    // Extensible for provider-specific needs
    data class Custom(val attributes: Map<String, Any>) : Message()
}
```

## Multimodal Content Support

```kotlin
sealed class Content {
    data class Text(val text: String) : Content()
    data class Image(val image: Resource, val detail: DetailLevel) : Content()
    data class Audio(val resource: Resource) : Content()
    data class Video(val resource: Resource) : Content()
    data class PDF(val resource: Resource) : Content()
    data class Custom(val resource: Resource) : Content()
}
```

Messages can contain **multiple content types** - text, images, audio, video, PDFs, and custom formats. This enables rich, multimodal conversations with AI models.

## Resource Handling

Resources can be **referenced by URI** or **embedded as base64 binary data**, providing flexibility in how media is handled.

```kotlin
sealed class Resource {
    data class Ref(val uri: Uri, val mimeType: MimeType?) : Resource()
    data class Binary(val content: Base64Blob, val mimeType: MimeType?) : Resource()
}
```

## Tool Integration

Messages integrate seamlessly with [tool calling](../tools) - assistants can request tool executions, and tool results flow back as structured messages in the conversation.
