---
title: http4k Enterprise Edition
description: Long-term stability, supply chain security, priority support, and Pro modules - everything your organisation needs to run http4k in production with confidence.
type: enterprise
email: enterprise@http4k.org
aliases:
    - /lts-support
highlights:
    -   title: Up to 24 months peace of mind
        description: Guaranteed security and critical updates for the LTS stable release channel, including source access, allowing you to focus on feature delivery.
        icon: "/images/footprint.svg"
    -   title: Supply chain security
        description: SLSA Level 2 provenance, signed SBOMs, and cosign signatures for every artifact, verified at build time with [http4k Verify](https://verify.http4k.org).
        icon: "/images/extendable.svg"
    -   title: Never wait for a release
        description: Early access to all http4k features and fixes through [maven.http4k.org](https://maven.http4k.org) every 1-2 weeks, rather than the [quarterly](/distribution/) cadence on Maven Central (from 1 October 2026).
        icon: "/images/connections.svg"
    -   title: Access to priority support
        description: The http4k team are here on Slack and Email to guide you through any issues or questions.
        icon: "/images/faq.svg"
    -   title: Pro modules
        description: A growing collection of commercially licensed, battle-tested modules built from real-world enterprise delivery.
        icon: "/images/footprint.svg"
    -   title: License reporting
        description: Signed, per-module license reports delivered with every artifact - ready for audit and regulatory review.
        icon: "/images/supportive.svg"
faq:
    -   question: "What evidence do we receive with each artifact?"
        answer: >-
            Every artifact in the http4k Enterprise Repository ships with an SBOM, build provenance,
            a signed licence report and cosign signatures. None of it is published to Maven Central,
            which carries PGP signatures only. The
            [supply chain security](/supply-chain-security/) page has the detail.
    -   question: "Are we locked in to http4k Verify, or can we check the artifacts ourselves?"
        answer: >-
            You can check them yourself. The signing keys are published openly and every artifact
            verifies with standard cosign tooling. [http4k Verify](https://verify.http4k.org) makes it
            a build step rather than something somebody remembers to do.
    -   question: "Does an Enterprise subscription make us CRA compliant?"
        answer: >-
            No, and be wary of any vendor claiming their product does. The Cyber Resilience Act places
            obligations on the manufacturer putting a product on the EU market, not on a library in
            your dependency tree. What we give you is evidence for the part that concerns us: due
            diligence over an integrated component under Article 13(6). The rest stays yours.
    -   question: "What is happening with Maven Central, and is the Community Edition going away?"
        answer: >-
            No. The Community Edition stays free, stays Apache-2.0 and stays on Maven Central. From
            1 October 2026, Sonatype's publishing limits mean releases reach Central approximately
            quarterly rather than every 1-2 weeks. maven.http4k.org is unaffected. We keep the current
            position on the [distribution and release channels](/distribution/) page.
    -   question: "How do our teams get access, and what has to change in our build?"
        answer: >-
            Credentials for **[maven.http4k.org](https://maven.http4k.org)** are issued when your
            subscription starts. It is a repository declaration in your build or your Artifactory
            equivalent, and coordinates and version numbers are unchanged, so nothing else moves.
            See the [Enterprise Repository reference](/ecosystem/enterprise/reference/repository/).
    -   question: "How long are LTS versions available, and what do they receive?"
        answer: >-
            http4k Community releases major versions on a delayed cadence aligned to the JDK's two-year
            LTS cycle. As each new major ships, the previous one enters the http4k EE LTS programme for
            2 years, receiving security and high priority bug fixes together with source access. The
            schedule above shows the current timeline.
    -   question: "What support is available to Enterprise subscribers?"
        answer: >-
            Email and Slack, answered by the people who wrote the code. That covers the LTS releases
            and the mainline, and questions about using http4k as well as defects in it.
    -   question: "Can I request specialised features for the http4k EE LTS versions?"
        answer: >-
            For stability, LTS versions carry security and bug fixes only. Get in touch with a feature
            request and we will either prioritise it for the mainline release or work with you on a
            custom build.
---
