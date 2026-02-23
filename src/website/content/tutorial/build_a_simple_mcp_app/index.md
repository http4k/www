---
title: Building an MCP App with http4k
description: A step-by-step guide to building an interactive MCP App with http4k
weight: 5
---

This tutorial walks through building **GitHub Release Planner** — an MCP App that displays an interactive UI inside an MCP host (like Claude Desktop), fetches
issues from the GitHub API, and lets users select issues for a release.

We'll use the [http4k MCP SDK](https://mcp.http4k.org) — a Kotlin-first MCP implementation that stays up to date with the latest protocol spec, embraces functional simplicity over annotation magic, and is totally testable from top to bottom. It also has first-class support for MCP Apps, which most other MCP SDKs don't offer yet.

By the end you will have:

- A Handlebars-based UI that runs inside the MCP host
- Two MCP tools — one visible only to the UI, one visible only to the model
- A streaming MCP server wired together by composing MCP Capabilities
- A local test harness for development

> **Prerequisites:** Kotlin, Gradle, Java 21. Familiarity with [http4k](https://http4k.org) basics.

# MCP in 60 seconds

[MCP (Model Context Protocol)](https://modelcontextprotocol.io/specification) is a protocol for connecting AI models to external capabilities. An MCP **server**
exposes capabilities to a **host** application (such as Claude Desktop), which in turn makes them available to the model. See
the [http4k MCP SDK docs](/ecosystem/ai/reference/mcp/) for the full integration guide.

The protocol defines four standard capability types:

- **Tools** — functions the model can call to perform actions or retrieve information. This is the capability everyone obsesses about, but it's only one piece
  of the protocol.
- **Resources** — data the model can read (files, API responses, rendered content)
- **Prompts** — reusable message templates the model can expand
- **Completions** — argument auto-complete suggestions for tool and prompt parameters, so the host can offer typeahead as users fill in values

In http4k these are `ToolCapability`, `ResourceCapability`, `PromptCapability`, and `CompletionCapability` — all implement the `ServerCapability` interface.

### What is an MCP App?

[MCP Apps](https://modelcontextprotocol.io/docs/extensions/apps) extend the protocol with **interactive UIs that render inside the host**. An MCP App is
composed of two capabilities working together:

1. A **Tool** — when the model calls it, the host knows to display the associated UI
2. A **Resource** — serves the HTML content that the host renders in a sandboxed iframe

The UI can call back to the server using the MCP App SDK. These calls go through the host via the MCP protocol — not via direct HTTP requests to the server.

**Visibility** controls who can see and call each tool:

- `model` — only the LLM can call it (the default for standard tools)
- `app` — only the embedded UI can call it (for UI → server communication)

Both can be set on a single tool if needed.

### Composition all the way down

http4k is built on a single composability rule: small, typed pieces combine into larger pieces of the same shape. An `HttpHandler` is a function; a `Filter`
wraps one `HttpHandler` to produce another; `routes()` combines multiple handlers into one. The result is always the same type, so you can keep composing.

MCP in http4k follows the same principle. A `ServerCapability` is the atomic unit — a single tool, resource, or prompt. Capabilities compose into larger
groups (we'll see how in step 7). And an **MCP server** is a composition of three things:

1. **Server identity** (`ServerMetaData`) — name, version, and supported extensions
2. **Security** (`McpSecurity`) — how clients authenticate
3. **Capabilities** — one or more `ServerCapability` instances

That's the whole server. No registration step, no lifecycle hooks — just function composition.

# 1. Generate your project

Use the [http4k Toolbox](https://toolbox.http4k.org) to generate a project with these modules. Choose "Server-based application", and select the following on the subsequent screens - everything else can be left at default:

- HTTP server backend - **Jetty** - MCP server backend with support for SSE
- Templating library - **Handlebars** - HTML templating engine
- http4k AI integrations - **Model Context Protocol SDK**  - MCP SDK and MCP App SDK dependencies
- Testing & Tooling - **Testing utilities for the MCP SDK** — local test harness for MCP Apps

Finish the Wizard and download the generated project. Your `build.gradle.kts` will include:

```kotlin
dependencies {
    implementation(platform("org.http4k:http4k-bom:${http4kVersion}"))

    implementation("org.http4k:http4k-ai-mcp-sdk")
    implementation("org.http4k:http4k-server-jetty")
    implementation("org.http4k:http4k-template-handlebars")

    testImplementation("org.http4k:http4k-ai-mcp-testing")
}
```

You can delete all of the pre-existing content in the source directories.

# 2. Domain model

Start with a simple data class to hold the user's selection, and a `ViewModel` for the Handlebars template:

{{< kotlin file="model.kt" >}}

`ViewModel` is an http4k interface that Handlebars uses to resolve the template by class name — `ReleasePlannerUI` maps to `ReleasePlannerUI.hbs`.

# 3. Save tool (UI → server)

This tool is called **from the UI** when the user clicks "Save Selection". Because it should only be callable by the app (not by the LLM), we set
`visibility = app`. This means the host hides this tool from the model's tool list and only makes it available to the embedded app iframe via
`callServerTool()`.

{{< kotlin file="SaveReleaseSelectionTool.kt" >}}

Key points:

- **`Tool.Arg` DSL** — `string()`, `int()`, `.multi`, `.required()` build a typed schema for the tool's arguments.
- **`visibility = app`** — the host shows this tool to the embedded app only; the LLM cannot call it.
- **`bind`** — connects the `Tool` definition to a handler lambda. The `args` parameter is a typesafe map extracted using the arg lenses (`repo(args)`,
  `issues(args)`).
- The `save` callback is a closure — no global state, no framework magic.

# 4. Get tool (model-only)

This tool lets the LLM read the current selection. It has `visibility = model` so the UI cannot call it — only the model can. This is the default for normal MCP
tools, but we set it explicitly here since we're in an MCP App context where both visibilities exist.

{{< kotlin file="GetReleaseSelectionTool.kt" >}}

No args needed here — the tool just reads the closure state and formats it as text for the model.

# 5. HTML UI

Create the Handlebars template at `src/main/resources/ReleasePlannerUI.hbs`. This is standard HTML that uses the [MCP App SDK](https://www.npmjs.com/package/@modelcontextprotocol/ext-apps) to call back to the server. You can put it in `src/main/resources`:

{{< html file="ReleasePlannerUI.hbs" >}}

The important bit is the [MCP App SDK](https://www.npmjs.com/package/@modelcontextprotocol/ext-apps) integration:

1. **`new App(...).connect()`** — establishes a message channel between the embedded iframe and the MCP host.
2. **`app.callServerTool(...)`** — calls our `save_release_selection` tool on the MCP server, through the host. This is how the UI communicates back to the
   server — it goes through the MCP protocol, not a direct HTTP call.

The GitHub API call (`fetch`) is a direct browser request — this works because we declare the appropriate Content Security Policy domains in the next step.

# 6. UI renderer

`RenderMcpApp` is a convenience that bundles two capabilities together:

1. A **Tool** — when the model calls it, the host knows to display the associated UI (via the `uri` in the tool's metadata)
2. A **Resource** — serves the actual HTML content at that URI

This is the core pattern of an MCP App: a tool triggers display, a resource provides content. `RenderMcpApp` also declares CSP metadata so the host knows which
external domains the UI needs:

{{< kotlin file="RenderReleasePlanner.kt" >}}

- **`resourceDomains`** — domains the UI can load stylesheets/scripts from (Bootstrap CSS, MCP App SDK JS).
- **`connectDomains`** — domains the UI can make fetch requests to (GitHub API, unpkg for ES module imports).
- The trailing lambda `{ templates(ReleasePlannerUI()) }` renders the Handlebars template to an HTML string.

# 7. App composition

A `CapabilityPack` is a container that groups multiple `ServerCapability` instances into a single unit. It enables modularity — you can compose an app from independent capability packs and combine them freely.

Here we combine the UI renderer (itself a pack of tool + resource), the save tool, and the get tool. The closure pattern is idiomatic http4k — capabilities
share state through captured variables, no DI container needed:

{{< kotlin file="GitHubReleasePlannerApp.kt" >}}

The `var releaseSelection` is captured by both tool closures. When the UI saves a selection, the model can immediately read it. No database, no DI framework —
just a closure. (This in-memory approach is fine for a tutorial; in production you'd persist state to a database or external store.)

# 8. MCP server

This is where the three parts of an MCP server come together: server identity, security, and capabilities. `mcpHttpStreaming` composes them into a
`PolyHandler` — http4k's type for services that speak multiple protocol types (here HTTP + SSE for the streaming transport):

{{< kotlin file="GitHubReleasePlanner.kt" >}}

- **`mcpHttpStreaming`** — creates a `PolyHandler` that speaks
  the [MCP Streamable HTTP transport](https://modelcontextprotocol.io/specification/2025-11-25/basic/transports) (HTTP for requests, SSE for server-pushed
  events).
- **`withExtensions(McpApps)`** — advertises MCP Apps support in the server metadata so the host knows to look for app resources.
- **`NoMcpSecurity`** — no auth for local development. Replace with real security for production.

# 9. Main entry point

{{< kotlin file="GithubReleasePlannerMain.kt" >}}

Run this and your MCP server is listening on `http://localhost:9000`.

# 10. Local test harness

In production, an MCP App renders inside a host like Claude Desktop. During development you don't want to restart Claude Desktop every time you change a
template or tweak a tool — you need a faster feedback loop.

`McpAppsHost` solves this. It is a lightweight standalone web server that acts as an MCP host: it connects to your MCP server, discovers its capabilities, and
renders any MCP Apps in a browser tab. You get the full MCP protocol stack (tool calls, visibility rules, CSP enforcement) without needing a real AI host in the
loop.

This uses the `http4k-ai-mcp-testing` dependency we added in step 1.

{{< kotlin file="RunMcpAppAndHost.kt" >}}

Run this and open `http://localhost:10000` in your browser. You will see the Release Planner UI rendered in the test host. You can fetch issues, select them,
and save — all going through the full MCP protocol stack.

# 11. Connect to Claude Desktop

Claude Desktop connects to remote MCP servers via **Settings > Connectors**. Since it requires HTTPS, the simplest way to expose your local server is with a [Cloudflare Tunnel](https://developers.cloudflare.com/cloudflare-one/connections/connect-networks/):

```bash
npx cloudflared tunnel --url http://localhost:9000
```

This prints a public URL like `https://xxx-yyy-zzz.trycloudflare.com`.

Then:

1. Start the MCP server (`GithubReleasePlannerMain.kt`)
2. Start the Cloudflare tunnel
3. In Claude Desktop, go to **Settings > Connectors** and add a new connector with the tunnel URL (appending `/mcp` — e.g. `https://xxx-yyy-zzz.trycloudflare.com/mcp`)
4. Ask Claude to show the release planner — it will call the `show_release_planner` tool and render the UI inline
5. Use the UI to fetch issues and save a selection (using the `save_release_selection` tool)
6. Outside of the MCP App UI in the chat, ask Claude about the selection — it will call `get_release_selection` to read and return the selection. This proves how state can be written and read from the MCP server.

> **Note:** The tunnel URL changes every time you restart `cloudflared`, so you'll need to update the connector each time.

# Recap

| Piece        | File                          | Role                                                        |
|--------------|-------------------------------|-------------------------------------------------------------|
| Domain model | `GitHubReleasePlannerApp.kt`  | `ReleaseSelection` data class, `ReleasePlannerUI` ViewModel |
| Save tool    | `SaveReleaseSelectionTool.kt` | UI → server (visibility: `app`)                             |
| Get tool     | `GetReleaseSelectionTool.kt`  | Model reads state (visibility: `model`)                     |
| HTML UI      | `ReleasePlannerUI.hbs`        | Handlebars template with MCP App SDK                        |
| Renderer     | `RenderReleasePlanner.kt`     | Registers UI as MCP App resource with CSP                   |
| Composition  | `GitHubReleasePlannerApp.kt`  | `CapabilityPack` wiring with closure state                  |
| MCP server   | `GitHubReleasePlanner.kt`     | Streaming HTTP transport                                    |
| Entry point  | `GithubReleasePlannerMain.kt` | `main()`                                                    |
| Test harness | `RunMcpAppAndHost.kt`         | `McpAppsHost` for local browser testing                     |
