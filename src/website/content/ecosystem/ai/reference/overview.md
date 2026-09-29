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

Type-safe implementation of the [MCP protocol](https://modelcontextprotocol.info/), over the HTTP Streaming, SSE and
Standard IO transports. See the [MCP SDK reference](/ecosystem/pro/reference/mcp/) for details.

- MCP-SDK: for building Model Context Protocol (MCP) servers
- MCP-client: for connecting to Model Context Protocol (MCP) servers
- MCP-desktop-client: native desktop client to bridge MCP servers with Desktop clients (eg. Claude)
- MCP-x402: for [x402](/ecosystem/pro/reference/x402/) payment-protected MCP tools
- MCP-MPP: for [Machine Payments Protocol](/ecosystem/pro/reference/mpp/) payment-protected MCP tools

# Agent2Agent ([Pro-tier](/pro))

Type-safe implementation of the [A2A protocol](https://a2a-protocol.org/), over both the JSON-RPC and REST/HTTP
bindings. See the [A2A SDK reference](/ecosystem/pro/reference/a2a/) for details.

- A2A-SDK: for building A2A agents, including Agent Cards, Tasks, Streaming and Push Notifications
- A2A-client: for connecting to A2A agents
- MCP-A2A-bridge: for exposing an A2A agent to MCP clients as tools

### Low-level Model API clients

| Vendor      | System | In-Memory Fake | Notes                                                      |
|-------------|--------|----------------|------------------------------------------------------------|
| AnthropicAI | API    | ✅              | Includes content generators                                |
| AzureAI     | API    | ✅              | Includes content generators and GitHubModels compatibility |
| LM Studio   | API    | ✅              |                                                            |
| Ollama      | API    | ✅              | Includes content generators and image generation           |
| Open AI     | API    | ✅              | Includes content generators and image generation           |
| TypeSafe    | API    | ✅              | Typed question/answer API instead of prompts               |
