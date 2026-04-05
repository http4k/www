---
category: Reference
type: ecosystem
ecosystem: http4k Core
tier: pro
title: "Tools: Verify"
description: Gradle plugin for automatic verification of http4k artifact signatures, with exported artifacts and attestation reports
---

The **http4k Verify** plugin automatically verifies cosign signatures on every http4k dependency before your code compiles. It covers JARs, CycloneDX SBOMs, SLSA provenance attestations, and license compliance reports — and exports all verification artifacts to your project for independent inspection and attestation.

### Installation (Gradle)

### Gradle setup

```kotlin
plugins {
    id("org.http4k.verify") <version>
}
```

The plugin automatically downloads the http4k public key, resolves sigstore bundles for all http4k dependencies, and verifies every signature. If any artifact has been tampered with, the build fails. Verification results are cached locally — subsequent builds have zero overhead until dependencies change.

### Configuration

{{< kotlin file="verify-plugin-config.kts" >}}

### Running Verification

Run verification explicitly with:

{{< shell file="verify-plugin-run.sh" >}}

Example output:

{{< shell file="verify-plugin-output.txt" >}}

### Exported Artifacts

Every time verification runs, all resolved verification artifacts are exported to `build/http4k-verify/` in your project directory. This gives you full visibility into exactly what was verified, and allows you to run your own independent checks.

{{< shell file="exported-artifacts.txt" >}}

For each http4k module, the following are exported:

- **`.jar.sha256`** — SHA-256 hash of the JAR file
- **`-jar-sigstore.json`** — Cosign signature bundle for the JAR
- **`-cyclonedx.json`** — CycloneDX SBOM listing all dependencies
- **`-cyclonedx-sigstore.json`** — Cosign signature bundle for the SBOM
- **`-provenance.json`** — SLSA Build L2 provenance attestation
- **`-provenance-sigstore.json`** — Cosign signature bundle for the provenance
- **`-license-report.json`** — Curated license compliance report
- **`-license-report-sigstore.json`** — Cosign signature bundle for the license report
- **`cosign.pub`** — The public key used for verification

You can use these exported files to independently verify any artifact with cosign:

{{< shell file="verify-jar.sh" >}}

### Verification Report

A JSON verification report is always generated at `build/http4k-verify/verification-report.json`. This report serves as an attestation record — proof that specific http4k dependencies with specific hashes were verified against specific signatures at a specific time.

{{< json file="verification-report.json" >}}

The report includes:

- **`timestamp`** — When verification was performed
- **`public_key_fingerprint`** — SHA-256 fingerprint of the public key used
- **`modules`** — For each http4k dependency:
    - GAV coordinates (group, module, version)
    - SHA-256 hash of the JAR
    - Verification result for each artifact type (`passed`, `failed`, or `not_available`)
    - Relative paths to all exported artifact and bundle files

This report can be included in your compliance documentation, audit trails, or CI/CD pipeline artifacts as evidence that your http4k dependencies were validated.

### Clearing the Verification Cache

Verification results are cached locally so that subsequent builds have zero overhead. To force re-verification of all artifacts (e.g. after rotating the public key), clear the cache:

```shell
./gradlew clearHttp4kVerificationCache
```

### Manual Verification with cosign

All verification artifacts can also be verified manually using [cosign](https://docs.sigstore.dev/cosign/overview/). Download the http4k public key from [https://http4k.org/cosign.pub](https://http4k.org/cosign.pub), or use the `cosign.pub` file exported by the plugin.

#### Verify a JAR

{{< shell file="verify-jar.sh" >}}

#### Verify an SBOM

{{< shell file="verify-sbom.sh" >}}

#### Verify Provenance

{{< shell file="verify-provenance-example.sh" >}}

The `--private-infrastructure` flag tells cosign to skip public transparency log verification, which is expected for privately distributed artifacts. All signatures include trusted timestamps from the Sigstore Timestamp Authority.

### Gradle Dependency Verification

Gradle has built-in support for verifying dependency checksums without any extra tooling. To pin SHA-256 checksums for all dependencies (including http4k artifacts from **[maven.http4k.org](https://maven.http4k.org)**):

{{< shell file="gradle-verify.sh" >}}

This generates a `gradle/verification-metadata.xml` file containing the expected checksums for every dependency:

{{< xml file="verification-metadata-example.xml" >}}

Gradle will verify these checksums on every build, failing if any artifact has been tampered with. Commit `verification-metadata.xml` to your repository. When upgrading http4k versions, re-run the command to update the checksums.
