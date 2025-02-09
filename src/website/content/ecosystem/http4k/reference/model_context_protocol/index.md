---
draft: true
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
    
    // If you want to build the MCP desktop client from source
    implementation("org.http4k.pro:http4k-mcp-desktop")
}
```

### About

The [Model Context Protocol](https://modelcontextprotocol.info/) is an open standard created by Anthropic that defines
how apps can feed information to AI language models. It creates a uniform way to link these models with various data
sources and tools, which streamlines the integration process.

MCP itself is based on the JSON RPC standard, which is used to communicate between the client and server. Messages are
sent from client to server and then asynchronously from server to client. MCP defines a set of standardised capabilities
which can be provided by the server or client. One use of these capabilities is to allow pre-trained models to have
access to both live data and APIs that can be used by the model to provide answers to user requests. Another use is to
provide agentic behaviour by providing standard communications between several MCP entities.

Currently the MCP standard supports the following transports:

- **Server Sent Events + HTTP:** Clients initiate an SSE connection to the server which is used to send messages to the
  client asynchronously at any time. Further client -> server requests and notifications are sent via HTTP, with
  responses being sent back via the SSE.
- **Standard IO:** Clients start a process that communicates with the server via JSON RPC messages via standard input
  and output streams.

The MCP capabilities include:

- **Prompts:** given a set of inputs by a client, the server can generate a prompt parameterised to those inputs. This
  allows servers to generate prompts that are tailored to the client's data.
- **Tools:** are exposed by the server and can be used by the client to perform tasks. The tool consists of a
  description and a JSON Schema definition for the inputs and outputs.
- **Resources:** are exposed by the server and can be used by the client to access text or binary content an example of
  this is a browser tool that can access web pages.
- **Roots:** the client supplies a list of file roots to the server which can be used to resolve file paths.
- **Completions:** The server provides auto-completion of options for a particular Prompt or Resource.
- **Sampling:** One MCP entity can request an LLN completion for text or binary content from another entity.

#### Example: Restaurant booking

Let's say you want to use an LLM to help you book a restaurant for you and some friends. An LLM could help coordinate
this by checking everyone's diary for free dates, finding a restaurant that everyone likes, and then booking a table.
The LLM would need to be able to communicate with some "tools" - your calendar, a restaurant database, and the booking
system. This is where MCP comes in. The LLM in this case would be the MCP client, and can use the protocol to
communicate with one or more "servers" providing live data to solve the problem.

- **Client:** Claude Desktop
- **Server Tools:** User diaries, Restaurant Database, Booking System

### http4k ❤️ Model Context Protocol

http4k provides very good support for the Model Context Protocol, and has been designed to make it easy to build your
own MCP-compliant servers in Kotlin, using the familiar http4k methodology of simple and composable functional
protocols. Each of the capabilities is modelled as a "binding" between a capability description and a function that
exposes the capability. For example, the Tool capability is modelled as a function `(ToolReqeust) -> ToolResponse`, and
can be bound to a tool definition which describes it's arguments using the http4k Lens system:

```kotlin
val date = Tool.Arg.localDate().required("date", "date in format yyyy-mm-dd")
val tool = Tool("name", "description", date) bind { req: ToolRequest ->
    ToolResponse.Ok(listOf(Content.Text("The date is ${date(req)}")))
}
```

The MCP support in http4k consists of two modules:

- `http4k-mcp-sdk` - The core SDK for working with the Model Context Protocol. You can build your own MCP-compliant
  applications using this module by plugging in capabilities into the server. The SDK provides a simple way to create
  either SSE or StdIO servers.
- `http4k-mcp-desktop` - A desktop client that bridges StdIO-based clients such as Claude Desktop with your own MCP
  servers operating over HTTP/SSE, either locally or remotely.

#### Example

{{< kotlin file="server.kt" >}}


