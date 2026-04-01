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

## What Gets Published

For every module in the http4k Enterprise Repository, the following provenance artifacts are published alongside the standard JARs and POMs:

<table class="table w-100">
<thead><tr><th>File</th><th>Description</th></tr></thead>
<tbody>
<tr><td><code>{artifact}.jar.sigstore.json</code></td><td>Cosign signature bundle for the JAR</td></tr>
<tr><td><code>{artifact}-sbom.json</code></td><td>CycloneDX SBOM listing all dependencies</td></tr>
<tr><td><code>{artifact}-sbom.json.sigstore.json</code></td><td>Cosign signature bundle for the SBOM</td></tr>
<tr><td><code>{artifact}.provenance.json</code></td><td>SLSA Build L2 provenance attestation (in-toto v1)</td></tr>
<tr><td><code>{artifact}.provenance.json.sigstore.json</code></td><td>Cosign signature bundle for the provenance</td></tr>
</tbody>
</table>

#### Example: Repository Layout

For `org.http4k:http4k-core:0.0.0.0`:

```
maven.http4k.org/releases/org/http4k/http4k-core/0.0.0.0/
    http4k-core-0.0.0.0.jar
    http4k-core-0.0.0.0.pom
    http4k-core-0.0.0.0-sources.jar
    http4k-core-0.0.0.0.jar.sigstore.json
    http4k-core-0.0.0.0-sbom.json
    http4k-core-0.0.0.0-sbom.json.sigstore.json
    http4k-core-0.0.0.0.provenance.json
    http4k-core-0.0.0.0.provenance.json.sigstore.json
```

## http4k Enterprise Repository

All http4k Enterprise Edition artifacts are available through **[maven.http4k.org](https://maven.http4k.org)**. Access credentials are included with your http4k Enterprise Edition subscription.

#### Gradle Configuration

```kotlin
repositories {
    maven {
        url = uri("https://maven.http4k.org")
        credentials {
            username = project.findProperty("http4kMavenUser") as String
            password = project.findProperty("http4kMavenPassword") as String
        }
    }
}
```

Add your credentials to `~/.gradle/gradle.properties`:

```properties
http4kMavenUser=<your-username>
http4kMavenPassword=<your-password>
```

#### Maven Configuration

Add the repository to your `pom.xml`:

```xml
<repositories>
    <repository>
        <id>http4k</id>
        <url>https://maven.http4k.org</url>
    </repository>
</repositories>
```

And credentials to `~/.m2/settings.xml`:

```xml
<servers>
    <server>
        <id>http4k</id>
        <username>your-username</username>
        <password>your-password</password>
    </server>
</servers>
```

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

```bash
cosign verify-blob http4k-core-0.0.0.0.jar \
    --key cosign.pub \
    --bundle http4k-core-0.0.0.0.jar.sigstore.json \
    --insecure-ignore-tlog
```

#### Verify an SBOM

```bash
cosign verify-blob http4k-core-0.0.0.0-sbom.json \
    --key cosign.pub \
    --bundle http4k-core-0.0.0.0-sbom.json.sigstore.json \
    --insecure-ignore-tlog
```

#### Verify Provenance

```bash
cosign verify-blob http4k-core-0.0.0.0.provenance.json \
    --key cosign.pub \
    --bundle http4k-core-0.0.0.0.provenance.json.sigstore.json \
    --insecure-ignore-tlog
```

The `--insecure-ignore-tlog` flag is required because http4k uses private signing without a public transparency log, ensuring provenance data remains accessible only to Enterprise Edition subscribers.

#### Gradle Dependency Verification

Gradle has built-in support for verifying dependency checksums without any extra tooling. To pin SHA-256 checksums for all dependencies (including http4k artifacts from [maven.http4k.org](https://maven.http4k.org)):

```bash
./gradlew --write-verification-metadata sha256
```

This generates a `gradle/verification-metadata.xml` file containing the expected checksums for every dependency. Gradle will then verify these checksums on every build, failing if any artifact has been tampered with.

Once generated, commit `verification-metadata.xml` to your repository. Future builds will automatically verify all downloaded artifacts match the pinned checksums. When upgrading http4k versions, re-run the command to update the checksums.

## SLSA Provenance Format

Each provenance attestation follows the [in-toto Statement v1](https://in-toto.io/Statement/v1) specification with a [SLSA Provenance v1](https://slsa.dev/provenance/v1) predicate. The attestation links each artifact to:

- The **exact git commit** that produced it
- The **GitHub Actions workflow** that built it
- The **build invocation ID** for full traceability
- **SHA-256 digests** of all subject artifacts

