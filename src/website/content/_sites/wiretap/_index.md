---
sitemap:
    disable: true
draft: true
title: "http4k Wiretap"
tagline: "http4k Wiretap: See everything. Break anything."
layout: "product"
type: "product"
subdomain: wiretap
tier: pro
description: "One line to add, one port to connect. Full visibility into traffic, traces, chaos testing, and MCP — with a browser UI and an AI-ready MCP server built in."
navigation:
    -   name: docs
        title: Documentation
        url: https://http4k.org/ecosystem/core/reference/wiretap
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
    -   title: Traffic</br>Monitor
        icon: supportive
        colour: pink
        description: Real-time HTTP traffic capture with filtering, named views, and live SSE streaming. See every request and response flowing through your app.
    -   title: Chaos</br>Engineering
        icon: support
        colour: indigo
        description: Inject failures, latency, and error responses into inbound and outbound traffic. Test your app's resilience without changing a line of code.
    -   title: OpenTelemetry</br>Traces
        icon: testability
        colour: green
        description: Gantt chart trace visualisation with deep linking to captured traffic. Follow requests across service boundaries.
    -   title: HTTP</br>Client
        icon: footprint
        colour: blue
        description: Built-in request builder for inbound and outbound testing. Fire requests at your app or upstream services directly from the console.
    -   title: MCP</br>Panel
        icon: supportive
        colour: violet
        description: Browse and interact with your app's MCP tools, prompts, and resources. Inspect and invoke MCP capabilities from the browser.
    -   title: AI-Ready</br>MCP Server
        icon: footprint
        colour: cyan
        description: Every Wiretap feature exposed as MCP tools at /_wiretap/mcp. Connect Claude or any MCP client for AI-assisted debugging.
    -   title: Home</br>Dashboard
        icon: testability
        colour: orange
        description: Live stats, latency distribution, traffic timelines, and JVM metrics. A single view of your application's health and activity.
    -   title: OpenAPI</br>Explorer
        icon: supportive
        colour: red
        description: Embedded Swagger UI for your app's API spec. Browse endpoints, try operations, and validate responses without leaving the console.
how_tos:
    -   section: Add a control panel to your http4k app
        steps:
            -   description: Step 1 - Wrap your **HttpHandler** with **Wiretap** — a single function call adds the full control panel to your app.
                image: step1.png
                alt: Wrap your app with Wiretap
            -   description: Step 2 - Start your server as normal — your app and the Wiretap console share **one port**, no extra infrastructure needed.
                image: step2.png
                alt: Start the server
            -   description: Step 3 - Open **/__wiretap** in your browser for the full control panel — traffic, traces, chaos, client, and more.
                image: step3.png
                alt: Open the browser UI
            -   description: Step 4 - Connect **Claude** or any MCP client to **/_wiretap/mcp** for AI-assisted debugging and testing.
                image: step4.png
                alt: Connect an MCP client
        cta: https://http4k.org/ecosystem/core/reference/wiretap
email_form_id: 8b025c558c
---
