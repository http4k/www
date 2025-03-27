---
category: Reference
type: ecosystem
tier: pro
ecosystem: http4k Core
title: "AI: MCP SDK"
description: Feature overview of the http4k-mcp-sdk module
---

### Installation (Gradle)

```kotlin
dependencies {
    { { < http4k_bom > } }
    // If you are developing an MCP enabled service (StdIO or SSE)
    implementation("org.http4k.pro:http4k-mcp-sdk")
}
```

## About

The [Model Context Protocol](https://modelcontextprotocol.info/) is an open standard created by Anthropic that defines
how apps can feed information to AI language models. It creates a uniform way to link these models with various data
sources and tools, which streamlines the integration process. MCP services can be deployed in a Server or Serverless
environment.

MCP itself is based on the JSON RPC standard, which is used to communicate between the client and server. Messages are
sent from client to server and then asynchronously from server to client. MCP defines a set of standardised capabilities
which can be provided by the server or client. One use of these capabilities is to allow pre-trained models to have
access to both live data and APIs that can be used by the model to provide answers to user requests. Another use is to
provide agentic behaviour by providing standard communications between several MCP entities.

Currently the MCP standard supports the following transports:

- **HTTP Steaming:** Clients can interact with the MCP server by either a SSE connection (accepting
  `application/event-stream`or via plain HTTP (accepting `application/json`). Stream resumption and replay is supported
  on the SSE connection by calling GET with the `Last-Event-ID` header. All traffic is served by calling the `/mcp`
  endpoint.
- **Server Sent Events + HTTP:** Clients initiate an SSE connection to the server (on `/sse`) which is used to send
  messages to the
  client asynchronously at any time. Further client -> server requests and notifications are sent via HTTP `/messages`
  endpoint, with
  responses being sent back via the SSE.
- **Standard IO:** Clients start a process that communicates with the server via JSON RPC messages via standard input
  and output streams.

The MCP capabilities include:

- **Tools:** are exposed by the server and can be used by the client to perform tasks. The tool consists of a
  description and a JSON Schema definition for the inputs and outputs.
- **Prompts:** given a set of inputs by a client, the server can generate a prompt parameterised to those inputs. This
  allows servers to generate prompts that are tailored to the client's data.
- **Resources:** are exposed by the server and can be used by the client to access text or binary content an example of
  this is a browser tool that can access web pages.
- **Roots:** the client supplies a list of file roots to the server which can be used to resolve file paths.
- **Completions:** The server provides auto-completion of options for a particular Prompt or Resource.
- **Sampling:** An MCP server can request an LLN completion for text or binary content a connected Client.

## http4k ❤️ Model Context Protocol

http4k provides very good support for the **Model Context Protocol**, and has been designed to make it easy to build
your own MCP-compliant servers in Kotlin, using the familiar http4k methodology of simple and composable functional
protocols. Each of the capabilities is modelled as a **binding** between a capability description and a function that
exposes the capability. See [Capability Types](#capability-types) for more details.

The MCP support in http4k consists of two parts - the `http4k-mcp-sdk` and
the [http4k-mcp-desktop](https://github.com/http4k/mcp-desktop) application which is used to connect the MCP server to
a desktop client such as **Claude Desktop**.

# SDK: http4k-mcp-sdk

The core SDK for working with the Model Context Protocol. You can build your own MCP-compliant applications using this
module by plugging in capabilities into the server. The **http4k-mcp-sdk module** provides a simple way to create either
**HTTP Streaming**, **SSE**, **StdIo** or **Websocket** based servers. For StdIo-based servers, we recommend compiling
your server to GraalVM for ease of distribution.

#### Server Example

Servers are created bu composing capabilities into a lightweight server. The server can be started using any of the
http4k server backends which support SSE (see [servers](/ecosystem/http4k/reference/servers)).

{{< kotlin file="server_example.kt" >}}

#### Serverless Example

MCP capabilities can be bound to [http4k Serverless](/ecosystem/http4k/reference/serverless) functions using the HTTP
protocol in non-streaming mode. To activate this simply bind them into the non-streaming HTTP which is a simple
`HttpHandler`.

{{< kotlin file="serverless_example.kt" >}}

## Capabilities

The MCP protocol is based on a set of capabilities that can be provided by the server or client. Each capability can be
installed separately into the server, and the client can interact with the server using these capabilities.

### Capability: Tools

Tools allow external MCP clients such as LLMs to request the server to perform bespoke functionality such as invoking an
API. The Tool capability is modelled as a function `typealias ToolHandler = (ToolRequest) -> ToolResponse`, and can be
bound to a tool definition which describes it's arguments using the http4k Lens system:

{{< kotlin file="tool_example.kt" >}}

### Capability: Prompts

Prompts allow the server to generate a prompt based on the client's inputs. The Prompt capability is modelled as a
function `(PromptRequest) -> PromptResponse`, and can be bound to a prompt definition which describes it's arguments
using the http4k Lens system.

{{< kotlin file="prompt_example.kt" >}}

[//]: # (### Capability: Sampling)

[//]: # ()

[//]: # (Sampling allows the server to invoke the client LLM model to generate some content. The Sampling capability is modelled)

[//]: # (as a function `&#40;SamplingRequest&#41; -> Sequence<SamplingResponse>`, and you can pass the contents of previous interactions)

[//]: # (as the)

[//]: # (context to the model.)

[//]: # ()

[//]: # ({{< kotlin file="sampling_example.kt" >}})

### Capability: Resources

Resources provide a way to interrogate the contents of data sources such as filesystem, database or website. The
Resource capability is modelled as a function `(ResourceRequest) -> ResourceResponse`. Resources can be static or
templated to provide bounds within which the client can interact with the resource.

{{< kotlin file="static_resource_example.kt" >}}

### Capability: Roots

Roots are provided by the client to the server and determine the base paths that the server can use to act within.

### MCP Client

http4k provides client classes to connect to your MCP servers via HTTP, SSE, JSONRPC or Websockets. The clients take
care of the
initial MCP handshake and provide a simple API to send and receive messages to the capabilities, or to register for
notifications with an MCP server.

{{< kotlin file="client_example.kt" >}}

# [http4k-mcp-desktop](https://github.com/http4k/mcp-desktop)

A desktop client that bridges StdIo-bound desktop clients such as **Claude Desktop** with your own MCP servers operating
over HTTP/SSE, either locally or remotely. The desktop client is a simple native application that can be downloaded from
the http4k GitHub, or built from the http4k source.

### To use mcp-desktop client with Claude Desktop:

1. Download the `mcp-desktop` binary for your platform from: [https://github.com/http4k/mcp-desktop], or install it with
   brew:

```bash
brew tap http4k/tap
brew install http4k-mcp-desktop
```

2. Configure [Claude Desktop](https://claude.ai/download) to use the `mcp-desktop` binary as an MCP server with the
   following configuration. You can find the configuration file in `claude_desktop_config.json`, or by browsing through
   the
   developer settings menu. You can add as many MCP servers as you like:

```json
{
    "mcpServers": {
        "MyMcpServer": {
            "command": "http4k-mcp-desktop",
            // or path to the binary
            "args": [
                "--transport",
                "--http-stream",
                "--url",
                "http://localhost:3001/mcp"
            ]
        }
    }
}
```

### To build mcp-desktop from source:

1. Clone the http4k repo
2. Install a GraalVM supporting JDK
3. Run `./gradlew :http4k-mcp-desktop:native-compile` to build the desktop client binary locally for your platform

[//]: # (# Example: Restaurant booking)

[//]: # ()

[//]: # (Let's say you want to use an LLM to help you book a restaurant for you and some friends. An LLM could help coordinate)

[//]: # (this by checking everyone's diary for free dates, finding a restaurant that everyone likes, and then booking a table.)

[//]: # (The LLM would need to be able to communicate with some "tools" - your calendar, a restaurant database, and the booking)

[//]: # (system. This is where MCP comes in. The LLM in this case would be the MCP client, and can use the protocol to)

[//]: # (communicate with one or more "servers" providing live data to solve the problem.)

[//]: # ()

[//]: # (- **Client:** Claude Desktop)

[//]: # (- **Server Tools:** User diaries, Restaurant Database, Booking System)
