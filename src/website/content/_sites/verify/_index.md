---
sitemap:
    disable: true
title: "http4k Verify"
tagline: "Trust every dependency. Verify every build."
layout: "product"
type: "product"
subdomain: verify
tier: pro
docs_link: /ecosystem/enterprise/reference/verify/
cta_label: "Included with Enterprise Edition"
ee_included: "Get automated supply chain verification for every http4k dependency — giving your security team the assurance they need, with zero developer overhead."
description: "**http4k Verify** ensures the integrity of every http4k dependency in your build — automatically, before your code compiles."
navigation:
    -   name: docs
        title: Documentation
        url: https://http4k.org/ecosystem/enterprise/reference/verify/
    -   name: http4k Pro
        title: http4k Pro
        url: "https://http4k.org/pro"
    -   name: http4k Enterprise Edition
        title: http4k Enterprise Edition
        url: https://http4k.org/enterprise
features:
    -   title: "Zero<br/>Friction"
        icon: footprint
        colour: green
        description: "One Gradle plugin. **No CLI tools to install.** Every http4k dependency is verified automatically before compilation."
    -   title: Build-Time</br>Enforcement
        icon: testability
        colour: blue
        description: "If any artifact has been **tampered with**, the build fails. No silent failures, no runtime surprises. Supply chain integrity verified **before your code compiles**."
    -   title: Full<br/>Coverage
        icon: support
        colour: indigo
        description: "Covers all **200+ http4k modules** — verifies **JARs**, **CycloneDX SBOMs**, **SLSA provenance attestations**, and **license compliance reports** for every dependency."
    -   title: Enterprise</br>Ready
        icon: supportive
        colour: orange
        description: "Works seamlessly through **Artifactory**, **Nexus**, or any repository manager proxying **maven.http4k.org**. Fits into your existing infrastructure with no changes."
    -   title: EU Cyber</br>Resilience Act
        icon: supportive
        colour: pink
        description: "The **CRA** requires machine-readable SBOMs and secure development practices for software sold in the EU. http4k Verify validates SBOMs for every dependency at build time."
    -   title: US Executive</br>Order 14028
        icon: footprint
        colour: green
        description: "Federal agencies require **SBOMs for all purchased software**. http4k Verify validates that SBOM signatures are authentic and untampered."
    -   title: NIST SSDF</br>(PS.3 / PW.4)
        icon: testability
        colour: blue
        description: "The Secure Software Development Framework recommends **consuming provenance** and verifying third-party components. http4k Verify automates this at build time."
    -   title: PCI<br/>DSS 4.0
        icon: support
        colour: orange
        description: "Strengthened **supply chain requirements** for payment-processing software. http4k Verify provides verifiable evidence of artifact integrity for your audit trail."
how_tos:
    -   section: "One line. That's it."
        steps:
            -   description: "**Step 1** — Apply the http4k Verify plugin to your build. Every http4k dependency is now verified before compilation."
                image: verify-install.webp
                alt: Apply the plugin
            -   description: "**Step 2** — On first build, all artifact signatures are verified and cached. Subsequent builds have zero overhead."
                image: verify-run.webp
                alt: Verification output
---
