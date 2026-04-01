---
title: Supply Chain Security
description: Cryptographically signed provenance and SBOMs for every http4k artifact, exclusively for http4k Enterprise Edition subscribers.
type: solutions
email: enterprise@http4k.org
highlights:
    -   title: SLSA Level 2 Provenance
        description: Know exactly where every artifact came from. Each build is cryptographically linked to the source commit and CI pipeline that produced it, giving your security team complete traceability.
        icon: "/images/footprint.svg"
    -   title: CycloneDX SBOMs
        description: Stay ahead of compliance requirements with machine-readable Software Bills of Materials for every module, ready for audit and vulnerability scanning out of the box.
        icon: "/images/connections.svg"
    -   title: Signed & Verified
        description: Every provenance artifact is signed with our cosign key and delivered exclusively through the authenticated http4k Enterprise Repository.
        icon: "/images/faq.svg"
faq:
    -   question: "How do I get access to the http4k Enterprise Repository?"
        answer: Repository credentials are included with your http4k Enterprise Edition subscription. Contact enterprise@http4k.org to get started.
    -   question: "What SLSA level do http4k artifacts achieve?"
        answer: All artifacts achieve SLSA Build Level 2. Provenance is generated during the CI/CD build and signed with a dedicated cosign key.
    -   question: "Where do I get the public key for verification?"
        answer: The cosign public key is available at https://http4k.org/cosign.pub. This key is used to verify all .sigstore.json bundles.
    -   question: "Are community (org.http4k) artifacts also covered?"
        answer: Yes. Both community (org.http4k) and pro (org.http4k.pro) artifacts published to the http4k Enterprise Repository include full provenance, SBOMs, and cosign signatures.
    -   question: "Is provenance data available on Maven Central?"
        answer: No. Provenance artifacts are available exclusively through the http4k Enterprise Repository at [maven.http4k.org](https://maven.http4k.org), ensuring this data remains accessible only to subscribers.
---

As part of the **http4k Enterprise Edition**, every http4k artifact is published with cryptographically signed provenance, complete dependency transparency, and verified build attestations — giving your organisation the supply chain assurance it needs to ship with confidence.

With increasing regulatory pressure from the **EU Cyber Resilience Act (CRA)**, **US Executive Order 14028**, **NIST SSDF**, and **PCI DSS 4.0**, organisations need to demonstrate full visibility into their software supply chain. http4k delivers this out of the box — every build produces **SLSA Level 2 provenance** linking artifacts to the exact source commit and CI pipeline, **CycloneDX SBOMs** detailing all transitive dependencies, and **cosign signatures** with trusted timestamps from the Sigstore Timestamp Authority. All provenance artifacts are published exclusively through the http4k Enterprise Repository at [maven.http4k.org](https://maven.http4k.org), ensuring this data remains accessible only to subscribers.

## What Gets Published

For every module in the http4k Enterprise Repository, the following provenance artifacts are published as classified Maven artifacts alongside the standard JARs and POMs. They can be resolved via standard Maven coordinates, e.g. `org.http4k:http4k-core:6.39.0.0:cyclonedx@json`.

<table class="table" style="max-width: 90%">
<thead><tr><th>Classifier</th><th>File</th><th>Description</th></tr></thead>
<tbody>
<tr><td><code>cyclonedx</code></td><td><code>{artifact}-cyclonedx.json</code></td><td>CycloneDX SBOM listing all dependencies</td></tr>
<tr><td><code>cyclonedx-sigstore</code></td><td><code>{artifact}-cyclonedx-sigstore.json</code></td><td>Cosign signature bundle for the SBOM</td></tr>
<tr><td><code>jar-sigstore</code></td><td><code>{artifact}-jar-sigstore.json</code></td><td>Cosign signature bundle for the JAR</td></tr>
<tr><td><code>provenance</code></td><td><code>{artifact}-provenance.json</code></td><td>SLSA Build L2 provenance attestation (in-toto v1)</td></tr>
<tr><td><code>provenance-sigstore</code></td><td><code>{artifact}-provenance-sigstore.json</code></td><td>Cosign signature bundle for the provenance</td></tr>
</tbody>
</table>

#### Example: Repository Layout

For `org.http4k:http4k-core:0.0.0.0`:

{{< shell file="repo-layout.txt" >}}

## SLSA Provenance Format

Each provenance attestation follows the [in-toto Statement v1](https://in-toto.io/Statement/v1) specification with a [SLSA Provenance v1](https://slsa.dev/provenance/v1) predicate. The attestation links each artifact to:

- The **exact git commit** that produced it
- The **GitHub Actions workflow** that built it
- The **build invocation ID** for full traceability
- **SHA-256 digests** of all subject artifacts

## http4k Enterprise Repository

All http4k Enterprise Edition artifacts are available through **[maven.http4k.org](https://maven.http4k.org)**. Access credentials are included with your http4k Enterprise Edition subscription.

#### Gradle Configuration

{{< kotlinscript file="gradle-repository.kt" >}}

Add your credentials to `~/.gradle/gradle.properties`:

{{< shell file="gradle-credentials.properties" >}}

#### Maven Configuration

Add the repository to your `pom.xml`:

{{< xml file="pom-repository.xml" >}}

And credentials to `~/.m2/settings.xml`:

{{< xml file="settings-credentials.xml" >}}

#### Artifactory Configuration

If your organisation uses Artifactory as a repository manager, add `maven.http4k.org` as a remote repository:

1. Navigate to **Administration > Repositories > Remote**
2. Click **New Remote Repository** and select **Maven**
3. Set the **URL** to `https://maven.http4k.org`
4. Under **Advanced**, configure **Username** and **Password** with your http4k credentials
5. Save and add the remote repository to your virtual repository resolution order

#### Nexus Configuration

For Sonatype Nexus, add a proxy repository:

1. Navigate to **Repository > Repositories > Create repository**
2. Select **maven2 (proxy)**
3. Set the **Remote storage URL** to `https://maven.http4k.org`
4. Under **HTTP > Authentication**, enter your http4k credentials
5. Add the proxy repository to your maven-public group

## Verifying Artifacts

All provenance artifacts can be verified using [cosign](https://docs.sigstore.dev/cosign/overview/). Download the http4k public key from [https://http4k.org/cosign.pub](https://http4k.org/cosign.pub).

#### Verify a JAR

{{< shell file="verify-jar.sh" >}}

#### Verify an SBOM

{{< shell file="verify-sbom.sh" >}}

#### Verify Provenance

{{< shell file="verify-provenance-example.sh" >}}

The `--private-infrastructure` flag tells cosign to skip public transparency log verification, which is expected for privately distributed artifacts. All signatures include trusted timestamps from the Sigstore Timestamp Authority.

#### Gradle Dependency Verification

Gradle has built-in support for verifying dependency checksums without any extra tooling. To pin SHA-256 checksums for all dependencies (including http4k artifacts from [maven.http4k.org](https://maven.http4k.org)):

{{< shell file="gradle-verify.sh" >}}

This generates a `gradle/verification-metadata.xml` file containing the expected checksums for every dependency. Gradle will then verify these checksums on every build, failing if any artifact has been tampered with.

Once generated, commit `verification-metadata.xml` to your repository. Future builds will automatically verify all downloaded artifacts match the pinned checksums. When upgrading http4k versions, re-run the command to update the checksums.

## Verification Script

We provide a ready-to-use script that downloads and verifies all provenance artifacts for any http4k module. Download the [http4k public key](https://http4k.org/cosign.pub), save the script below, and run:

{{< shell file="run-verify-provenance.sh" >}}

{{< shell file="verify-provenance.sh" >}}

