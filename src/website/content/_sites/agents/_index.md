---
sitemap:
    disable: true
title: "http4k Agent Pack"
tagline: "Everything you need to build, debug, and monetise AI agents on the JVM."
layout: "product"
type: "product"
subdomain: agents
tier: pro
description: "The complete http4k toolkit for AI agent systems. MCP servers and Apps, A2A agent interop, machine payments, and full observability — all testable in-memory with familiar http4k patterns."
docs_link: https://www.http4k.org/ecosystem/enterprise
navigation:
    -   name: mcp
        title: MCP
        url: https://mcp.http4k.org/
    -   name: payments
        title: Payments
        url: https://payments.http4k.org/
    -   name: wiretap
        title: Wiretap
        url: https://wiretap.http4k.org/
    -   name: pricing
        title: Pricing
        url: "#pricing"
    -   name: http4k Pro
        title: http4k Pro
        url: https://http4k.org/pro
    -   name: http4k Enterprise Edition
        title: http4k Enterprise Edition
        url: https://http4k.org/enterprise
features:
    -   title: MCP</br>SDK
        icon: testability
        colour: blue
        description: "**Full MCP protocol** support — Tools, Prompts, Resources, Tasks, Sampling, Elicitation, OAuth. **MCP Apps** for server-rendered UI in Claude Desktop and other MCP clients."
    -   title: Agent2Agent</br>Protocol
        icon: supportive
        colour: green
        description: "**Full A2A spec** with both **JSON-RPC** and **REST** bindings. **Agent Cards**, Tasks, Streaming, **Push Notifications**, and **multi-turn conversations** for interoperable multi-agent systems."
    -   title: Wiretap
        icon: footprint
        colour: violet
        description: "**Intercept** JUnit extension with **HTML reports**, sequence diagrams, and living docs. **Wiretap Console** for live traffic monitoring, chaos engineering, and MCP debugging."
    -   title: Machine</br>Payments
        icon: support
        colour: orange
        description: "**X402** and **MPP** support for both HTTP and MCP. Payment-gated endpoints, tools, and resources. **Client and server filters** with automatic 402 handling."
    -   title: Protocol</br>Clients
        icon: support
        colour: pink
        description: "**MCP** and **A2A clients** with full streaming support. Connect to any compliant server. Test **in-memory** with no network, no ports, no flaky CI."
    -   title: Multi-Transport
        icon: footprint
        colour: indigo
        description: "**HTTP Streaming**, **SSE**, **WebSocket**, and **Standard IO** for MCP. **JSON-RPC** and **REST** for A2A. Deploy the same logic over any transport."
    -   title: Observability
        icon: supportive
        colour: red
        description: "**OpenTelemetry** tracing across MCP and A2A with semantic conventions. Plug into Jaeger, Honeycomb, Datadog — **zero custom instrumentation**."
    -   title: Security &</br>Multi-tenancy
        icon: testability
        colour: cyan
        description: "**OAuth**, **API keys**, **mTLS**, and **OpenID Connect** security schemes. First-class **tenant isolation** across tasks, storage, and routing."
how_tos:
    -   section: Build a complete AI agent system
        steps:
            -   description: "Define **MCP tools** and **A2A Agent Cards** with type-safe, testable functions — the building blocks of your agent system."
                alt: Define agent capabilities
            -   description: "Add **Wiretap Intercept** to your tests — one annotation for full OpenTelemetry capture with HTML reports. Use **@RegisterExtension** for multi-agent sequence diagrams."
                alt: Test with Intercept
            -   description: "Compose into production servers with **security**, **payments**, and **observability**. Wrap with **Wiretap()** for a full runtime console."
                alt: Compose and deploy
            -   description: "Connect agents via **A2A** for multi-agent collaboration. Gate premium tools with **X402/MPP** payments. Debug with **Claude** via Wiretap's MCP server."
                alt: Connect and monetise
email_form_id: 8b025c558c
#pricing_table:
#    id: prctbl_1TDP7sG47sNzv4yXlmeptCzt
#    key: pk_live_51QVe22G47sNzv4yXpAdUo8zZKsS97wLXlkTOBr6WILnYRIm3UYQ1WhMwz3azZMoTRnUzOwebV1m5E4FDicDtGUaG001uo16uL0
---
