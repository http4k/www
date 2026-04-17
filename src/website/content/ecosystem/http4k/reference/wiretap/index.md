---
category: Reference
type: ecosystem
ecosystem:
  - http4k Core
  - http4k Enterprise
tier: pro
title: "Wiretap"
description: See everything. Capture OpenTelemetry traces from tests with rich reports, monitor live traffic, and debug MCP servers.
---

## What is Wiretap?

When something goes wrong in a distributed system, the hardest part is understanding what actually happened. Which services were called? What did the requests and responses look like? Where did the time go? Where did the error originate?

**http4k Wiretap** answers these questions by capturing everything — OpenTelemetry traces, HTTP traffic, logs, and events — and presenting it in rich, shareable reports. It has two components:

**[Intercept](#intercept-junit-extension)** is a JUnit extension that instruments your tests. Add one annotation and every test automatically captures traces, traffic, and console output. On failure (or always, if you prefer), Wiretap generates a self-contained HTML report with trace timelines, sequence diagrams, interaction topology, error isolation, and critical path analysis. It also produces a [Living Test Document](#living-test-document) — a markdown specification of what your test actually exercised, with HTTP request/response contracts you can commit to your repo.

**[Wiretap Console](#wiretap-console)** is a pure Kotlin reverse proxy that sits in front of your running application. All traffic flows through it and is captured automatically. The console UI is served at `/_wiretap/` on the same port — real-time traffic monitoring, OpenTelemetry trace visualisation, chaos engineering, MCP debugging, and an embedded HTTP client. Every feature is also available as [MCP tools](#mcp-tools) at `/_wiretap/mcp` for AI-assisted debugging.

### Installation

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k.pro:http4k-wiretap")
}
```

## Intercept: JUnit Extension

### Zero-Config

Add `@ExtendWith(Intercept::class)` to any test class. OpenTelemetry traces, logs, and events are captured automatically. On test failure, a self-contained HTML report is generated.

{{< kotlin file="example_zero_config.kt" >}}

### HTTP Traffic Capture

Use `@RegisterExtension` to capture all HTTP traffic flowing through your app:

{{< kotlin file="example_http_traffic.kt" >}}

### Multi-Service with OTel

For apps that make outbound HTTP calls, instrument the client with `http()` and `otel()` from the `Context` receiver. This captures both inbound and outbound traffic with full OpenTelemetry trace context across service boundaries.

{{< kotlin file="example_registered.kt" >}}

The `Context` receiver provides:
- `http()` — wraps an outbound HTTP client with traffic recording and OTel tracing
- `otel(serviceName)` — creates an OpenTelemetry instance that records to the trace store
- `clock()` — deterministic clock
- `random()` — deterministic random

By default, `GlobalOpenTelemetry` is used for trace capture. Use `otel()` for explicit control.

### Parameter Injection

Intercept injects test parameters based on their type:

**`ChaosEngine`** — controls failure injection on outbound calls:

{{< kotlin file="example_chaos.kt" >}}

**`McpClient`** — connects to an MCP server under test (use `Intercept.poly` for `PolyHandler` apps):

{{< kotlin file="example_mcp.kt" >}}

### RenderMode

- `RenderMode.OnFailure` (default) — generate reports only when tests fail
- `RenderMode.Always` — generate reports for every test
- `RenderMode.Never` — disable report generation

## Test Reports

Reports are written to `build/reports/http4k/wiretap/`.

### HTML Report

Self-contained HTML report with three main sections:

**Trace Detail** — Gantt-style span timeline with expandable attributes, events, and logs. Below the timeline, a set of diagrams for each trace:
- **Sequence** — temporal flow across services with HTTP status codes and duration
- **Interactions** — service topology with call count and total duration per edge
- **Errors** — filtered sequence showing only paths to error spans
- **Critical Path** — filtered sequence showing only the slowest root-to-leaf path
- **Timing** — span breakdown table sorted by duration with percentage bars

**HTTP Traffic** — full request/response detail with one-click cURL copy

**Stdout/Stderr** — captured console output from the test

### Living Test Document

Auto-generated markdown file alongside the HTML report. Contains:
- Mermaid sequence diagrams per trace
- MCP operation payloads (when [detail span modifiers](/ecosystem/ai/reference/mcp/#opentelemetry-span-modifiers) are enabled)
- Span events (exceptions, state transitions)
- HTTP transactions with headers table and collapsible bodies

The document uses deterministic labels — MCP operations show as `tools/call show_ui`, `resources/read ui://a-ui` etc.

## Trace Diagrams

All diagrams are available in both the Wiretap web UI and Intercept reports.

| Diagram | Description |
|---------|-------------|
| **Sequence** | Temporal flow with HTTP status codes and duration annotations |
| **Interactions** | Service topology with call count and total duration per edge |
| **Timing** | Span breakdown sorted by duration with percentage bars |
| **Errors** | Filtered sequence showing only paths to error spans |
| **Critical Path** | Filtered sequence showing only the slowest root-to-leaf path |

## Wiretap Console

Add the full Wiretap console to your app using either a **LocalTarget** (in-process) or **RemoteTarget** (proxy to a running server):

{{< kotlin file="example_console.kt" >}}

### MCP Tools

All Wiretap features are exposed as MCP tools at `/_wiretap/mcp`:
- `get_trace_diagrams` — all diagrams for a trace
- `export_trace_markdown` — living document for a trace
- `list_traces`, `list_transactions` — browse captured data
- Traffic replay, chaos control, and more

### Markdown Export

Click **Export Markdown** in the trace detail toolbar to download a living document for any trace. Also available via the `export_trace_markdown` MCP tool.
