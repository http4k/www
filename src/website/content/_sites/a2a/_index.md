---
sitemap:
    disable: true
title: "http4k A2A (Agent2Agent Protocol)"
tagline: "Interoperable agents, testable by design"
layout: "product"
type: "product"
subdomain: a2a
tier: pro
description: Full A2A protocol support for building interoperable multi-agent systems. JSON-RPC and REST bindings, Agent Cards, Tasks, Streaming, Multi-tenancy - all testable, all type-safe.
docs_link: https://http4k.org/ecosystem/ai/reference/a2a/
navigation:
    -   name: docs
        title: Documentation
        url: https://http4k.org/ecosystem/ai/reference/a2a/
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
    - title: Complete</br>Protocol
      icon: testability
      colour: blue
      description: "Full **A2A spec** support with both **JSON-RPC** and **REST** protocol bindings. **Agent Cards**, **Tasks**, **Messages**, **Artifacts**, **Streaming**, **Push Notifications**, and **multi-turn conversations** - all from a single server definition."
    - title: Agent</br>Connectivity
      icon: supportive
      colour: red
      description: "Type-safe **A2A clients** for both **JSON-RPC** and **REST** bindings. Plug your agent straight into **Claude Desktop**, **Cursor**, or any MCP client via the built-in bridge - zero integration code."
    - title: Testable</br>Design
      icon: footprint
      colour: green
      description: "**Pure functions**, no side effects. Test agent interactions fully **in-memory** - no network, no ports, no flaky CI."
    - title: Agent</br>Discovery
      icon: support
      colour: violet
      description: "**Agent Cards** for capability advertisement and discovery. **Extended Agent Cards** for authenticated access. Publish **skills**, **security schemes**, and **JWS signatures** for card integrity."
    - title: Task</br>Lifecycle
      icon: support
      colour: orange
      description: "Full task state management with **pagination** and **filtering**. **Push notifications** via webhooks for status changes. Long-running agent collaboration built in."
    - title: Streaming
      icon: footprint
      colour: pink
      description: "Real-time **SSE streaming** for large outputs and continuous updates. **Subscribe to task updates** for live status changes. Progressive results without polling."
    - title: Multi-tenancy
      icon: supportive
      colour: indigo
      description: "First-class **tenant isolation** across tasks, storage, and routing. Build **multi-tenant agent platforms** with tenant-scoped **task storage** and **protocol endpoints**."
    - title: Wiretap</br>Integration
      icon: testability
      colour: cyan
      description: "Built-in **Wiretap** support for A2A servers. **Intercept** JUnit extension. **Wiretap Console** for live **traffic monitoring** and **OpenTelemetry tracing** across agent interactions."
how_tos:
    - section: Getting started with Agent2Agent in http4k
      steps:
          - description: "Step 1 - Define agent capabilities with **type-safe Agent Cards** and **Skills**"
            kotlin_file: step1.kt
            alt: Define Agent Cards
            image: a2a-step1.webp
          - description: "Step 2 - Handle tasks and messages with **pure**, **testable** functions"
            kotlin_file: step2.kt
            alt: Handle tasks
            image: a2a-step2.webp
          - description: "Step 3 - Test **in-memory** - no server, no ports, no flaky CI"
            kotlin_file: step3.kt
            alt: Test in-memory
            image: a2a-step3.webp
          - description: "Step 4 - Connect to other agents using the **pure Kotlin A2A Client**"
            kotlin_file: step4.kt
            image: a2a-step4.webp
            alt: Connect to agents
#email_form_id: 8b025c558c
pricing_table:
    id: prctbl_1TTTrDG47sNzv4yXHvLYP1Yr
    key: pk_live_51QVe22G47sNzv4yXpAdUo8zZKsS97wLXlkTOBr6WILnYRIm3UYQ1WhMwz3azZMoTRnUzOwebV1m5E4FDicDtGUaG001uo16uL0
---
