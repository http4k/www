---
sitemap:
    disable: true
title: "BackOffice"
tagline: "Secure API Gateway for developers and MCP tools"
layout: "product"
type: "product"
tier: pro
description: "Connect developers and AI models to your OpenAPI powered services via a secure, audited gateway with MCP integration"
navigation:
    -   name: docs
        title: Sign up for launch
        url: a'#subscribe'
features:
    -   title: Unified API</br>Gateway
        icon: supportive
        colour: pink
        description: Consolidate all your OpenAPI-powered services into a single, secure access point for developers and AI models.
    -   title: Zero Data</br>Access
        icon: support
        colour: indigo
        description: Enforce strict API-only access patterns, ensuring engineers and AI systems can never directly access your data sources.
    -   title: MCP</br>Integration
        icon: footprint
        colour: violet
        description: Empower your APIs to be automatically exposed as MCP tools, enabling AI assistants to interact with your services through standard protocols.
    -   title: SSO</br>Authentication
        icon: testability
        colour: blue
        description: Secure user login handled via integration with your standard Single Sign-On tooling. MCP tools are authenticated using standard OAuth2 flows.
    -   title: Comprehensive</br>Auditing
        icon: testability
        colour: green
        description: Track and log all API interactions with detailed audit trails for security and compliance requirements, extensible through custom connectors.
    -   title: CLI</br>Support
        icon: testability
        colour: yellow
        description: Enable command-line access with token-based authentication, allowing developers to integrate your services into their toolchains and pipelines.
    -   title: Cloud</br>Native
        icon: supportive
        colour: orange
        description: Deploy the Gateway within your own cloud environment with minimal configuration and maximum security. You are always in control.
    -   title: Self-</br>Contained
        icon: footprint
        colour: red
        description: A standalone solution that works with your existing OpenAPI services, requiring no changes to your backend implementation.
how_tos:
    -   section: Integration with your infrastructure
        steps:
            -   description: Step 1 - Simply import the **http4k-backoffice** library to your Kotlin project and configure manual or **automatic service discovery**.
                alt: Configure your Gateway
            -   description: Step 2 - Define **access controls** with code-based whitelist/blacklist APIs for both developers and AI systems.
                alt: Define access controls
            -   description: Step 3 - Implement **custom extensions** through our simple notification auditing **connector APIs**.
                alt: Implement extensions
            -   description: Step 4 - Deploy to **your cloud environment** using your standard deployment process. **No SaaS, Cloud or Vendor lock-in**.
                alt: Deploy to cloud
large_image: backoffice-big.png
email_form_id: e218235422
---
