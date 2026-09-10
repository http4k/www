---
sitemap:
    disable: true
title: "http4k Transactional Outbox"
tagline: "Reliable, distributed transaction processing"
layout: "product"
type: "product"
subdomain: postbox
tier: pro
description: "Build reliable, distributed transaction processing systems using the http4k Transactional Outbox pattern"
docs_link: https://http4k.org/ecosystem/pro/reference/outbox/
cta_label: "Read the docs"
ee_included: "Reliable, asynchronous HTTP processing for your services - transactional outbox and inbox patterns backed by database transactions, retries, and in-memory testability."
navigation:
    -   name: docs
        title: Documentation
        url: https://http4k.org/ecosystem/pro/reference/outbox/
    -   name: http4k Pro
        title: http4k Pro
        url: https://http4k.org/pro
    -   name: http4k Enterprise Edition
        title: http4k Enterprise Edition
        url: https://http4k.org/enterprise
features:
    - title: Async<br>processing
      icon: testability
      colour: indigo
      description: "Process **HTTP requests asynchronously** using the [Transactional Outbox](https://microservices.io/patterns/data/transactional-outbox.html) pattern - store-and-forward with **database-backed reliability**."
    - title: Built with<br>testing in mind
      icon: supportive
      colour: violet
      description: "The same **level of testability** as the rest of your http4k application, with **in-memory implementations** and plain `HttpHandler` composition."
    - title: Handle inbound<br> and outbound requests
      icon: footprint
      colour: pink
      description: "Use the same mechanism for **outgoing messages** (outbox) or **incoming work** (inbox) - swap your client or handler for a `PostboxHandlers` interceptor."
    - title: Out-of-the-box<br> resilience
      icon: support
      colour: blue
      description: "**Database transactions**, **retries with incremental backoff**, and a **claim-lease** model so concurrent processors never double-handle work."
how_tos:
    - section: Getting started with the Postbox module in http4k
      steps:
          - description: "Step 1 - To implement a Transactional Outbox, replace your HTTP client or handler with a **`PostboxHandlers` interceptor**. Requests are stored **transactionally** and returned as `202 Accepted`."
            alt: Intercept requests
          - description: "Step 2 - Create the storage with **`JdbcPostboxSchema.create(datasource)`** and wrap it in a **`PostboxTransactor`** for transactional processing."
            alt: Set up transactional storage
          - description: "Step 3 - Run **`PostboxProcessing`** in the background to **claim** pending requests, process them with your `HttpHandler`, and store the responses."
            alt: Process in the background
          - description: "Step 4 - Serve the stored responses with the Postbox **status handler** - consume them immediately or later, with **idempotent** retries."
            alt: Serve responses
#email_form_id: 8b025c558c
---
