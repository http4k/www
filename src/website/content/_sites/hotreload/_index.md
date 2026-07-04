---
sitemap:
    disable: true
title: "http4k Hot Reload"
type: "product"
subdomain: hotreload
layout: "product"
tier: pro
tagline: No more manual server restarts.
description: http4k Hot Reload restarts your server the moment your code changes - no more manual restarts
docs_link: https://http4k.org/ecosystem/http4k/reference/hot_reload/
navigation:
    -   name: docs
        title: Documentation
        url: https://http4k.org/ecosystem/http4k/reference/hot_reload/
    -   name: pricing
        title: Pricing
    -   name: http4k Pro
        title: http4k Pro
        url: https://http4k.org/pro
    -   name: http4k Enterprise Edition
        title: http4k Enterprise Edition
        url: https://http4k.org/enterprise
features:
    - title: No more<br>stop/starts
      icon: testability
      colour: indigo
      description: This tool restarts the server the instant it detects a code change, cutting the manual steps out of your development workflow.
    - title: Tiny<br> overhead
      icon: supportive
      colour: pink
      description: All that's needed to support hot reloading is a class that provides the main application `HttpHandler`, and a dedicated `main()` function.
    - title: Server and protocol<br> agnostic
      icon: footprint
      colour: violet
      description: Hot reload works with all of the <a href="https://http4k.org/ecosystem/http4k/reference/servers/">servers</a> http4k can run on, no matter if you're serving HTTP, WebSockets, or SSE protocol.
    - title: Leverage your<br> existing build tool
      icon: support
      colour: blue
      description: Use the Gradle integration (including multi-project support) or extend it to leverage your existing building tools.
how_tos:
    - section: Set it up in two easy steps
      steps:
          - description: Step 1 - Create the **factory class**
            image: hotreload-step1.png
            alt: Create your factory class
          - description: Step 2 - **Jus run it** with the HotReloadServer
            image: hotreload-step2.png
            alt: Run it with the HotReloadServer
pricing_table:
    id: prctbl_1TEEK2G47sNzv4yXswGXeFeT
    key: pk_live_51QVe22G47sNzv4yXpAdUo8zZKsS97wLXlkTOBr6WILnYRIm3UYQ1WhMwz3azZMoTRnUzOwebV1m5E4FDicDtGUaG001uo16uL0
---
