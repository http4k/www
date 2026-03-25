---
sitemap:
    disable: true
title: "http4k Wiretap"
tagline: "See everything. From your tests to production."
layout: "product"
type: "product"
early_access: true
subdomain: wiretap
tier: pro
description: "**Wiretap Intercept** captures OpenTelemetry traces, logs, and events from every test with rich HTML reports. **Wiretap Console** gives you real-time traffic monitoring, chaos engineering, and MCP debugging in your running app."
navigation:
#    -   name: docs
#        title: Documentation
#        url: https://toolbox.http4k.org/ecosystem/http4k/reference/wiretap
#    -   name: pricing
#        title: Pricing
#        url: "#pricing"
    -   name: http4k Pro
        title: http4k Pro
        url: https://http4k.org/pro
    -   name: http4k Enterprise Edition
        title: http4k Enterprise Edition
        url: https://http4k.org/enterprise
features:
    -   title: Intercept:</br>ZeroConfig
        icon: testability
        colour: green
        description: "**@ExtendWith(Intercept::class)** on any test class. Traces, events, logs, and baggage **captured and visualised** in Gantt-style HTML reports on failure. No collector, no server, no setup."
    -   title: Intercept:</br>InMemory
        icon: supportive
        colour: blue
        description: "**@RegisterExtension** for instrumented HTTP clients, full traffic capture, built-in **Chaos Engine** for injecting failure into downstream calls, and auto-generated **Mermaid sequence diagrams** across multiple services. All in-process, no ports, full speed."
    -   title: Intercept:</br>Reports
        icon: footprint
        colour: indigo
        description: "**Self-contained HTML** with Gantt trace timelines, Mermaid sequence diagrams, HTTP transaction detail with one-click cURL copy, and captured stdout/stderr. **Share**, **archive**, **attach** to tickets."
    -   title: Wiretap:</br>TrafficMonitor
        icon: support
        colour: pink
        description: "**Real-time HTTP traffic capture** with filtering, named views, and Request replay. **See every request and response** flowing through your app."
    -   title: Wiretap:</br>OpenTelemetry
        icon: footprint
        colour: orange
        description: "Gantt chart trace visualisation with deep linking to captured traffic. Follow requests across service boundaries."
    -   title: Wiretap:</br>MCPPanel
        icon: support
        colour: cyan
        description: "A **pure Kotlin** MCP Inspector replacement built into your app. **Browse and invoke** tools, prompts, and resources. **Host and render MCP Apps** with interactive UI. Plus every Wiretap feature **exposed as MCP tools** for AI-assisted debugging."
    -   title: Wiretap:</br>ChaosEngine
        icon: supportive
        colour: violet
        description: "Inject **failures**, **latency**, and **error responses** into inbound and outbound traffic. Test resilience **without changing code**."
    -   title: Wiretap:</br>Dashboard & OpenAPI
        icon: testability
        colour: red
        description: "Home dashboard with **live stats**, **latency distribution**, and JVM metrics. **Built-in HTTP client** and embedded **Swagger UI** for your OpenAPI spec."
how_tos:
    -   section: Four ways to Wiretap
        steps:
            -   description: "**@ExtendWith(Intercept::class)** on any test class — OpenTelemetry data captured automatically, Gantt-style HTML report on failure. That's it."
                image: wiretap-intercept.webp
                alt: Add Intercept to your tests
            -   description: "**@RegisterExtension** with your app factory — full in-memory HTTP testing with traffic capture and multi-service sequence diagrams."
                image: wiretap-traffic.webp
                alt: Register extension for in-memory testing
            -   description: "Wrap your **HttpHandler** with **Wiretap()** — one function call adds the full console to your running app on the same port."
                image: wiretap-launch.webp
                alt: Wrap your app with Wiretap
            -   description: "Connect **Claude Code** or any MCP client to **/_wiretap/mcp** for AI-assisted debugging and chaos testing."
                image: wiretap-claude.webp
                alt: Connect an MCP client
pre_features_partial: product/wiretap_hero_image.html
post_features_partial: product/wiretap_screenshots.html
email_form_id: 8b025c558c
#pricing_table:
#    id: prctbl_1TDPBkG47sNzv4yX1N2tTzB6
#    key: pk_live_51QVe22G47sNzv4yXpAdUo8zZKsS97wLXlkTOBr6WILnYRIm3UYQ1WhMwz3azZMoTRnUzOwebV1m5E4FDicDtGUaG001uo16uL0
---
