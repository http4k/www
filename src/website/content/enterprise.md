---
title: http4k Enterprise Edition
description: Long-term stability, supply chain security, priority support, and Pro modules - everything your organisation needs to run http4k in production with confidence.
type: enterprise
email: enterprise@http4k.org
aliases:
    - /lts-support
highlights:
    -   title: Up to 24 months peace of mind
        description: Guaranteed security and bug updates for the LTS stable release channel, allowing you to focus on feature delivery.
        icon: "/images/footprint.svg"
    -   title: Access to priority support
        description: The http4k team are here on Slack and Email to guide you through any issues or questions.
        icon: "/images/faq.svg"
    -   title: Source code access
        description: Full access to the LTS editions of the http4k codebase for transparency and auditing.
        icon: "/images/connections.svg"
    -   title: License reporting
        description: Signed, per-module license reports delivered with every artifact - ready for audit and regulatory review.
        icon: "/images/supportive.svg"
    -   title: Supply chain security
        description: SLSA Level 2 provenance, signed SBOMs, and cosign signatures for every artifact - compliance-ready out of the box.
        icon: "/images/extendable.svg"
    -   title: Pro modules
        description: A growing collection of commercially licensed, battle-tested modules built from real-world enterprise delivery.
        icon: "/images/footprint.svg"
faq:
    -   question: "How long are LTS versions of http4k EE available for?"
        answer: As standard, the open source http4k CE of http4k releases major versions of the ecosystem on a delayed cadence which is aligned with major release dates of the JDK every 2 years. As a new major http4k CE version is released, the previous major version enters the http4k EE LTS programme for 2 years. See the schedule above for the current timeline.
    -   question: "How will my teams access the LTS binaries of http4k EE?"
        answer: The LTS versions of http4k EE are hosted in the http4k Enterprise Repository at **[maven.http4k.org](https://maven.http4k.org)**. To access these assets, you will need to sign up for the http4k Enterprise Subscription and will be supplied with the necessary credentials.
    -   question: "What types of updates are included within the http4k EE LTS offering?"
        answer: LTS version of http4k EE receive security and high priority bug fixes for community identified bugs. This ensures that your applications remain secure and fully functional.
    -   question: "What type of support is available for http4k EE subscribers"
        answer: After signup you will be given access to both email and Slack-based support channels. The http4k team is there
            to help you with any issues or questions you may have regarding the LTS version of http4k libraries.
    -   question: "Can I request specialised features for the http4k EE LTS versions?"
        answer: For stability reasons, the LTS version of http4k is focused on security and bug fixes so new features are
            not considered for these versions. If you have a particular feature request, please get in touch to discuss
            it with the team. We will then prioritise it for inclusion in the mainline release or can work with you to
            provide a custom build.
---

The **http4k Enterprise Edition (EE)** subscription delivers long-term stability and support for organisations running critical production workloads using http4k technologies.

## At a Glance

{{< features >}}

## In detail

#### Supply Chain Security
From **September 2026**, the **EU Cyber Resilience Act** requires verifiable provenance and machine-readable SBOMs for every component shipped in software sold into Europe. **US Executive Order 14028**, **NIST SSDF** and **PCI DSS 4.0** push in the same direction. http4k Enterprise Edition delivers this today: every artifact ships with **SLSA Level 2 provenance**, **CycloneDX SBOMs**, **signed licence compliance reports** and **cosign signatures** with trusted timestamps from the Sigstore Timestamp Authority. **SLSA Level 3** provenance is available on request for organisations with enhanced compliance requirements.

The **[http4k Verify](https://verify.http4k.org)** Gradle plugin automates build-time verification of all of the above. One line of build config validates signatures, SBOMs and provenance for every http4k dependency - automatically, before your code compiles - so the assurance is captured at build time rather than chased down at audit time. [Learn more about Supply Chain Security](/supply-chain-security/).

#### Compliance at a glance

<table class="table" style="max-width: 90%">
<thead><tr><th>Framework</th><th>Status</th><th>What http4k EE provides</th></tr></thead>
<tbody>
<tr><td><strong>EU Cyber Resilience Act</strong></td><td>Mandatory from September 2026</td><td>CycloneDX SBOMs, SLSA provenance, signed artifacts, vulnerability handling</td></tr>
<tr><td><strong>US Executive Order 14028</strong></td><td>Active</td><td>SBOM generation and supply chain attestation for federal procurement</td></tr>
<tr><td><strong>NIST SSDF (PS.3 / PW.4)</strong></td><td>Active</td><td>Secure development practices, provenance, third-party component verification</td></tr>
<tr><td><strong>PCI DSS 4.0</strong></td><td>Active</td><td>Supply chain integrity controls and verifiable artifact evidence for audit</td></tr>
</tbody>
</table>

See [Supply Chain Security](/supply-chain-security/) for the full technical detail.

#### Long Term Support
Enjoy peace-of-mind with **Long-Term Support (LTS)** versions of http4k. Our LTS version provides up to 24 months of guaranteed security patches and high priority updates for a stable release, ensuring your projects remain secure and fully functional. Additionally, our priority support channel also extend up to 24 months, meaning **http4k EE LTS** is your key to maintaining focus on feature delivery while ensuring your http4k applications are future-proof.

#### Pro Modules
A growing collection of commercially licensed, battle-tested modules built from years of successfully delivering complex systems with http4k. These expert-designed extensions reflect real-world requirements and enterprise patterns, available from Maven Central. [See what's available](/pro).

#### Priority Support
Access expert guidance and **priority support** directly from the creators and maintainers of http4k. With dedicated channels, including email and Slack, you’ll get **fast resolution** to any issues or questions, backed by our deep knowledge of the http4k ecosystem. Whether it’s compatibility updates or bug fixes, our team ensures that your http4k deployment remains fully integrated with the broader Kotlin and JVM environments.

Additionally, **http4k EE**  offers transparency with full access to the codebase and rapid integration of community-driven improvements. Built under a permissive Apache2 license, you can rest assured that your applications comply with industry standards while enjoying the reliability and accountability that **http4k EE LTS**  support provides. Get in touch to learn more about how **http4k EE LTS**  can stabilize your development process for the long term.

## http4k EE Timeline

The timeline for http4k EE's LTS has been designed to strike a balance between a generous open source period and the need for us to take advantage for new language features available in the JDK. We are also committed to providing support using a predictable timeline for our LTS customers, which is synchronized with the official JDK release and support cycle of LTS versions (currently every 2 years), but offset by a 3 month window. To put that more visually, this is the timeline for current and future Java LTS releases:

{{< timeline data="Java" >}}

Based on the above, the current plan is for future major http4k versions to have the following Community and Enterprise Edition (LTS) schedule:

{{< timeline data="http4k" >}}

In the case of an intermediate major version upgrade between JDK versions, the previous major version will also immediately start a 24 month support window to ensure that customers have a smooth upgrade path. 

If you have any questions about the http4k EE subscription, or long-term support requirements outside of the above schedule, please get in touch using the below contact link and we will endeavour to assist.
