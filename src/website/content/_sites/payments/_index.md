---
sitemap:
    disable: true
title: "http4k Internet Payments Pack"
tagline: "Machine-to-machine payments for HTTP and AI agents."
layout: "product"
type: "product"
subdomain: payments
tier: pro
description: "Add payment gates to any HTTP endpoint or MCP tool with X402 and Machine Payments Protocol. Client and server filters, route-level security, testable fakes, and MCP integration — all with familiar http4k patterns."
navigation:
    -   name: X402 Docs
        title: MPP Docs
        url: https://http4k.org/ecosystem/connect/reference/x402/
    -   name: x402 Docs
        title: x402 Docs
        url: https://http4k.org/ecosystem/connect/reference/mpp/
    -   name: MCP SDK
        title: MCP SDK Docs
        url: https://http4k.org/ecosystem/ai/reference/mcp/
    -   name: http4k Pro
        title: http4k Pro
        url: https://http4k.org/pro
    -   name: http4k Enterprise Edition
        title: http4k Enterprise Edition
        url: https://http4k.org/enterprise
features:
    -   title: X402</br>Protocol
        icon: testability
        colour: blue
        description: "Full **x402 protocol** support with client-side automatic **402 handling**, server-side **payment verification and settlement** via facilitator services, and route-level **X402Security**."
    -   title: Machine</br>Payments Protocol
        icon: supportive
        colour: green
        description: "Flexible payment protocol with **pluggable payment methods**. No facilitator needed — implement **MppSigner** and **MppVerifier** directly for any provider including **Stripe** and **crypto**."
    -   title: MCP</br>Integration
        icon: footprint
        colour: indigo
        description: "**Payment-gated MCP tools and resources**. Add protocol-level and tool-level payment filters to your MCP servers for automated **machine-to-machine commerce** between AI agents."
    -   title: Client</br>Filters
        icon: support
        colour: violet
        description: "Automatic **402 handling** for both X402 and MPP. Your HTTP client detects payment-required responses, **signs the challenge**, and **retries** — transparent to your application code."
    -   title: Server</br>Filters
        icon: footprint
        colour: orange
        description: "**Protect any endpoint** with payment requirements. Verify and settle payments before allowing access. One filter wrapping your handler — **no changes to business logic**."
    -   title: Route</br>Security
        icon: support
        colour: pink
        description: "**X402Security** and **MppSecurity** integrate with http4k's routing DSL for **fine-grained, per-route** payment protection. Different prices for different endpoints."
    -   title: Testable</br>Fakes
        icon: supportive
        colour: cyan
        description: "Built-in **FakeX402Facilitator** for testing payment flows without real transactions. Test your entire payment integration **in-memory** with no external services."
    -   title: Transactional</br>Outbox
        icon: testability
        colour: red
        description: "**(Coming soon)** Reliable message delivery with **database consistency**. Ensure payment events, notifications, and downstream messages are **never lost**, even when services fail."
how_tos:
    -   section: Getting started with Internet Payments
        steps:
            -   description: "Add the X402 or MPP dependency. **Protect a server endpoint** with a payment filter — one line wrapping your handler."
                alt: Add payment filter
            -   description: "Add the **client filter** to automatically handle 402 responses. Your client signs payment challenges and **retries transparently**."
                alt: Add client filter
            -   description: "Use **X402Security** or **MppSecurity** for per-route payment protection in your http4k **routing DSL**."
                alt: Route-level security
            -   description: "Test everything **in-memory** using the built-in **FakeX402Facilitator**. No real transactions, no external services, full-speed tests."
                alt: Test with fakes
email_form_id: 8b025c558c
pricing_table:
    id: prctbl_1TDOnIG47sNzv4yXxEeW5RRv
    key: pk_live_51QVe22G47sNzv4yXpAdUo8zZKsS97wLXlkTOBr6WILnYRIm3UYQ1WhMwz3azZMoTRnUzOwebV1m5E4FDicDtGUaG001uo16uL0
---
