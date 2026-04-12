# How Kotlin Powers Functional Design: MCP Edition — v2 Outline

## Presentation Structure (~45 minutes, ~25 slides)

---

## Part 1: A Recipe for Functional Protocol Design (~12 min)

### Slide 1: Title
**How Kotlin Powers Functional Design — MCP Edition**
David Denton, http4k

---

### Slide 2: Start with a Function
```kotlin
val app = { r: Request -> Response(OK).body(r.body) }
```
Point out: **everything here is a function.**
- `app` is a function: `(Request) -> Response`
- `Response(OK)` is a function — it hides the implementation (`MemoryResponse`)
- The protocol message encodes OK and ERROR cases in the response type

**Kotlin feature:** Top-level functions, function types as first-class citizens.

---

### Slide 3: Separate Application from Runtime
```kotlin
app.asServer(Jetty(9000)).start()
```
- `asServer` is an **extension function on a function**
- The app doesn't know or care what runs it

Swap the runtime:
```kotlin
app.asServer(Helidon(9000)).start()
app.asServer(KtorCIO(9000)).start()
app.asServer(Lambda()).start()   // serverless — same app
```

**Kotlin feature:** Extension functions — add capabilities without inheritance.

---

### Slide 4: Add Routing
```kotlin
val app = routes(
    "/" bind GET to echoHandler,
    "/foo" bind PUT to fooHandler
)
```
- `bind` creates a predicate (on the request) matched to a handler
- `routes()` is a **top-level function** that composes these pairs
- The result? Another `HttpHandler`. Composition all the way down.

**Kotlin feature:** Infix functions (`bind`, `to`), top-level functions.

---

### Slide 5: Test It — Just Call the Function
```kotlin
@Test
fun `echo returns request body`() {
    val response = app(Request(GET, "/").body("hello"))
    assertThat(response.bodyString(), equalTo("hello"))
}
```
- No server started. No port. No HTTP. Just a function call **in memory**.

---

### Slide 6: Add a Filter
```kotlin
val timed = Filter { next ->
    { req ->
        val start = System.nanoTime()
        next(req).also { println("Took ${System.nanoTime() - start}ns") }
    }
}

val app = timed.then(routes(...))
```
- A filter is a function: `(HttpHandler) -> HttpHandler`
- Easy to add. Easy to test. Easy to compose.

**Kotlin feature:** Higher-order functions, lambdas.

---

### Slide 7: Type-Safe Data Handling — Lenses
```kotlin
val name = Header.required("X-Name")
val id = Query.int().required("id")

val handler: HttpHandler = { req ->
    Response(OK).body("Hello ${name(req)}, id=${id(req)}")
}
```
- A lens is a function that knows how to extract (and inject) typed data
- Same API regardless of where the data lives (header, query, body, tool arg...)
- No reflection. Compile-time safety. Reusable across domains.

**Kotlin feature:** Extension functions, function types — lenses are just functions.

---

### Slide 8: The Recipe
**A recipe for functional protocol design:**

1. **Handler** — a function: `Request → Response` (with success/failure cases)
2. **Binding** — a specification (predicate) bound to a handler
3. **Routing** — compose bindings to direct traffic
4. **Filters** — cross-cutting logic that decorates handlers
5. **Lenses** — type-safe data extraction (reusable across domains)
6. **Transport** — attach to a runtime (swappable, cross-portable)
7. **Testable** — everything works in memory, no infra required

*This is what we extracted from building http4k. It's not framework-specific — it's a general approach to protocol design.*

---

### Slide 9: Composition — The Payoff
- http4k v1: **3 modules** (core, server, client)
- http4k today: **200+ modules** covering servers, clients, serverless, messaging, templating, OpenAPI, OAuth, OpenTelemetry, and more
- All built on the same composable parts

*(Visual: module growth graph from v1 → now)*

**The parts compose. That's what makes this sustainable.**

---

### Slide 10: But Does the Recipe Transfer?
- We used this recipe for HTTP
- We applied it again for SSE
- And again for WebSocket
- Then Anthropic released the Model Context Protocol...
- **Could we do it again?**

---

## Part 2: MCP — Same Recipe, New Protocol (~14 min)

### Slide 11: What is MCP?
**Model Context Protocol** — the "USB-C for AI"
- Anthropic's open standard for AI ↔ tool integration
- Capabilities: Tools, Resources, Prompts, Completions, Elicitation, Sampling
- The standard way for AI to interact with the world

---

### Slide 12: MCP is a Layered Protocol
MCP is higher-level than HTTP because it defines:
```
┌─────────────────────────────────┐
│  Capabilities (Tools, etc.)     │  ← Domain handling
├─────────────────────────────────┤
│  Sessions + Security            │  ← OAuth, state
├─────────────────────────────────┤
│  JSON-RPC                       │  ← Serialisation + routing + errors
├─────────────────────────────────┤
│  Transport (HTTP, SSE, WS)      │  ← Wire protocol
└─────────────────────────────────┘
```
JSON-RPC carries routing and error handling — it's the equivalent of our HTTP message, but at a higher level of abstraction.

**We can build this from http4k parts:** core, JSON-RPC, SSE, JSON, lenses, security filters.

---

### Slide 13: Apply the Recipe — Handler
```kotlin
// HTTP
typealias HttpHandler = (Request) -> Response

// MCP
val tool = Tool("add", "Adds two numbers") bind { req ->
    Ok(ToolContent.text("${req.args["a"] + req.args["b"]}"))
}
```
Same pattern: **specification bound to a handler function.**

The response encodes success/failure as sealed types (`Ok` / `Error`).

**Kotlin features:** Type aliases, infix functions, sealed classes for Result.

---

### Slide 14: Apply the Recipe — Transport Separation
```kotlin
tool.asServer(Jetty(9000)).start()
```
Same extension function pattern. Same runtime separation.

*(Acknowledge: this is a bit of a cheat — there's more going on under the hood because of the layered protocol. But it looks great, so we thought we'd show it.)*

---

### Slide 15: Why Layering Matters — The SSE → HTTP Story
When the MCP spec changed transport from SSE to HTTP Streaming:
- **Other SDKs:** Significant rewrites across the codebase
- **http4k MCP:** Swapped the transport layer. Protocol logic untouched.

**This is the payoff of separating layers. It's not theoretical — it saved us.**

---

### Slide 16: Lenses Transfer Too
```kotlin
// HTTP — extract typed data from a request
val id = Query.int().required("id")
val name = Header.required("X-Name")

// MCP — same lens pattern for tool arguments
val a = Tool.int().required("a")
val b = Tool.int().required("b")

// Instance-based schema derivation (no reflection)
val input = Tool.Input.auto(ToolInput(0, 0)).toLens()
```
- Same API: `Tool.int().required("a")` mirrors `Query.int().required("id")`
- Lenses extract typed tool arguments exactly like HTTP query params
- Data classes become JSON Schemas via instances, not reflection

**Kotlin feature:** Extension functions, data classes — same extraction pattern, new domain.

---

### Slide 17: Every Capability — Same Pattern
```kotlin
// Tools
val tool = Tool("calculator", "...") bind { req -> Ok(ToolContent.text("result")) }

// Prompts
val prompt = Prompt("summarize", "...") bind { req -> Ok(PromptContent(messages)) }

// Resources
val resource = Resource("config", "...") bind { req -> Ok(ResourceContent(data)) }

// Completions
val completion = Completion("autocomplete", "...") bind { req -> Ok(CompletionContent(suggestions)) }
```
**Learn the pattern once, apply it to every capability.**

---

### Slide 18: Compose Capabilities
```kotlin
val capabilities = CapabilityPack(
    calculator,
    summarizePrompt,
    configResource
)
```
Same composition principle — build up from small pieces.

---

### Slide 19: Compose a Server
```kotlin
val server = McpServer(
    capabilities = listOf(mathPack, docsPack),
    security = OAuthSecurity(config),
    identity = ServerIdentity("my-server", "1.0"),
    transport = HttpTransport(Jetty(9000))
)
```
An MCP server is composed from:
- Capabilities (or packs of capabilities)
- Security (OAuth, basic, none)
- Server identity
- Transport (SSE, HTTP, WebSocket)

---

### Slide 20: MCP Apps
**What is an MCP App?**
- An MCP App is a CapabilityPack that binds tools to resources
- Interactions happen through tool calls (handled by the AI client)
- Same composition model, one level up

```kotlin
val app = McpApp(
    name = "Document Manager",
    tools = listOf(searchTool, editTool),
    resources = listOf(documentResource)
)
```

**This is what we'll demo.**

---

## Part 3: Demo (~8 min)

### Slide 21: Demo — MCP App in Claude
Everything we've discussed composes into this:

1. Show the code — it's just the patterns you've already seen
2. Run the tests — in memory, no server needed
3. Launch in Claude
4. Interact with it live
5. Make a change, re-test, see it update

---

## Part 4: Closing (~4 min)

### Slide 22: Three Takeaways

**1. Functional patterns aren't domain-specific**
HttpHandler → ToolHandler → PromptHandler. Same recipe. Stop rebuilding. Start remixing.

**2. Simplicity makes you agile**
When the MCP spec changed, we swapped one layer. Complexity would have trapped us.

**3. Kotlin is a force multiplier**
Top-level functions, extension functions, sealed classes, type aliases, infix functions — these features make the patterns transferable.

---

### Slide 23: The Broader Point
- This isn't about http4k
- These patterns apply to **any protocol** — GraphQL, gRPC, whatever's next
- Build simple, composable parts once
- Watch them work everywhere

---

### Slide 24: Resources
- **http4k.org** — docs and guides
- **github.com/http4k/http4k** — source + examples
- **http4k MCP SDK docs** — getting started with MCP
- **Slack community** — join the conversation

---

### Slide 25: Thank You
**Questions?**
David Denton · @daviddenton · http4k.org

---

## Timing Summary

| Part | Duration | Slides |
|------|----------|--------|
| Part 1: Functional Protocol Design | ~13 min | 1–10 |
| Part 2: MCP — Same Recipe | ~15 min | 11–20 |
| Part 3: Demo | ~8 min | 21 |
| Part 4: Closing | ~4 min | 22–25 |
| **Total** | **~40 min** | **25 slides** |

*~5 minutes buffer for audience interaction, pauses, and Q&A overflow.*

---

## Kotlin Features (woven throughout, in order of appearance)

1. **Function types / top-level functions** (Slide 2) — functions as the core abstraction
2. **Extension functions** (Slide 3) — `asServer` on a function type
3. **Infix functions** (Slide 4) — `bind`, `to` for readable DSLs
4. **Higher-order functions / lambdas** (Slide 6) — filters as function composition
5. **Lenses as functions** (Slide 7) — type-safe extraction, reusable across domains
6. **Type aliases** (Slide 13) — readable domain-specific function types
7. **Sealed classes** (Slide 13) — Ok/Error result encoding
8. **Data classes** (Slide 16) — instance-based schema derivation

---

## Open Questions

- **Demo content:** What specific MCP App will you demo? Slide 20 should set it up.
- **Code accuracy:** All code examples need verification against actual http4k/MCP APIs before slide generation.
