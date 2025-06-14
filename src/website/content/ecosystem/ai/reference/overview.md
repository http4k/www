---
category: Reference
type: ecosystem
ecosystem: http4k AI
title: Overview
description: Overview of the http4k AI modules
weight: -1
---

A quick reference as to what is what with the http4k AI modules.

# Universal LLM adapters

| Provider      | Chat | Streaming Chat | Image Generation | In-Memory Fake                   | 
|---------------|------|----------------|------------------|----------------------------------|
| AnthropicAI   | ✅    | ❌              | ❌                | http4k-connect-ai-anthropic-fake |       
| Azure         | ✅    | ✅              | ❌                | http4k-connect-ai-openai-fake    |       
| Gemini        | ✅    | ✅              | ❌                | http4k-connect-ai-openai-fake    |       
| Github Models | ✅    | ✅              | ❌                | http4k-connect-ai-openai-fake    |       
| Open AI       | ✅    | ✅              | ✅                | http4k-connect-ai-openai-fake    |       

# LangChain4J

Plug-in http4k clients into any Langchain-compatible AI model, embedding, or vector store.

# Model Context Protocol ([Pro-tier](/pro))

- MCP-SDK: for building Model Context Protocol (MCP) servers
- MCP-client: for connecting to Model Context Protocol (MCP) servers
- MCP-desktop-client: native desktop client to bridge MCP servers with Desktop clients (eg. Claude)

### Low-level Model API clients

| Vendor      | System | In-Memory Fake | Notes                                                      |
|-------------|--------|----------------|------------------------------------------------------------|
| AnthropicAI | API    | ✅              | Includes content generators                                |
| AzureAI     | API    | ✅              | Includes content generators and GitHubModels compatibility |
| LM Studio   | API    | ✅              |                                                            |
| Ollama      | API    | ✅              | Includes content generators and image generation           |
| Open AI     | API    | ✅              | Includes content generators and image generation           |
