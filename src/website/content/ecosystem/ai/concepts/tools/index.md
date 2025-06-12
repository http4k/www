---
type: ecosystem
category: Concept
ecosystem: http4k AI
title: Using Tools
description: An overview of the how LLMss use Tools to gather data and effect change.
---

Tool usage in LLMs follows a **request-response cycle** where the model can invoke external tools during conversation and incorporate their results into responses.

## Tool Flow

```
User Message → LLM → Tool Request → Tool Execution → Tool Result → LLM → Final Response
```

1. **User sends message** with available tools
2. **LLM decides** which tools to use (if any)
3. **Tool requests** are executed by your application
4. **Tool results** are fed back to the LLM
5. **LLM generates** final response incorporating tool data

## Tool Definition

{{< kotlin file="tool_definition.kt" >}}

Tools interfaces are defined with **JSON schemas** that specify input parameters and expected outputs.

## Message Flow Example

### 1. User Request with Tools
{{< kotlin file="user_request_with_tools.kt" >}}

### 2. LLM Response with Tool Request
{{< kotlin file="llm_response_with_tool_request.kt" >}}

### 3. Tool Execution

The caller is responsible for executing the tool request.

### 4. User submits follow-up request, including the history and tool results
{{< kotlin file="follow_up_request.kt" >}}


### 5. Final LLM Response
{{< kotlin file="final_response.kt" >}}

## Schema Validation

The **JSON schema support** ensures:
- **Input validation** - Tool arguments match expected schema
- **Type safety** - Compile-time checking of tool definitions
- **Documentation** - Self-describing tool capabilities
- **Interoperability** - Standard schema format across providers

Tools enable LLMs to access real-time data, perform calculations, interact with APIs, and extend their capabilities beyond their training data.
