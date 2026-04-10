---
category: Reference
type: ecosystem
ecosystem: http4k Enterprise
tier: pro
title: "Verify"
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

The plugin automatically downloads the http4k [signing key list](https://http4k.org/.well-known/cosign-keys.json), resolves sigstore bundles for all http4k dependencies, and verifies every signature using the correct key for each artifact. If any artifact has been tampered with, the build fails. Verification results are cached locally — subsequent builds have zero overhead until dependencies change.

### (Optional) Configuration

The plugin works out of the box with no configuration required, but if you want to customize the plugin, you can configure it in `build.gradle.kts`:

{{< kotlin file="verify-plugin-config.kts" >}}

### Running Verification

Run verification explicitly with:

{{< shell file="verify-plugin-run.sh" >}}

Example output:

{{< shell file="verify-plugin-output.txt" >}}

### When Verification Fails

If any artifact has been tampered with or a signature does not match, the plugin reports the failure and stops the build:

{{< shell file="verify-plugin-failure.txt" >}}

Each failed check is marked with **✗** and a `FAIL` line showing the reason. The build will fail with a `GradleException` by default. To continue the build despite failures (e.g. for CI reporting), set `failOnError` to `false` in the plugin configuration.

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
- **`cosign-keys.json`** — The key list used for verification (multi-key mode)
- **`cosign.pub`** — The public key used for verification (single-key mode only)

You can use these exported files to independently verify any artifact with cosign:

{{< shell file="verify-jar.sh" >}}

### Verification Report

A JSON verification report is always generated at `build/http4k-verify/verification-report.json`. This report serves as an attestation record — proof that specific http4k dependencies with specific hashes were verified against specific signatures at a specific time.

{{< json file="verification-report.json" >}}

The report includes:

- **`timestamp`** — When verification was performed
- **`plugin`** — Version and SHA-256 hash of the verify plugin JAR that performed the verification
- **`modules`** — For each http4k dependency:
    - GAV coordinates (group, module, version)
    - SHA-256 hash of the JAR
    - **`signing_key_fingerprint`** — Fingerprint of the key that signed this module's artifacts
    - Verification result for each artifact type (`passed`, `failed`, or `not_available`)
    - Relative paths to all exported artifact and bundle files

This report can be included in your compliance documentation, audit trails, or CI/CD pipeline artifacts as evidence that your http4k dependencies were validated.

### Clearing the Verification Cache

Verification results are cached locally so that subsequent builds have zero overhead. To force re-verification of all artifacts (e.g. after rotating the public key), clear the cache:

```shell
./gradlew clearHttp4kVerificationCache
```

### Key Rotation

The plugin supports key rotation via the [signing key list](https://http4k.org/.well-known/cosign-keys.json) — a JSON document listing all currently valid signing keys with their fingerprints and validity windows. Each artifact's provenance includes the fingerprint of the signing key, which the plugin uses to select the correct key for verification.

When http4k rotates signing keys, a new key is added to the key list and the old key is marked as `retired`. Artifacts signed with the old key continue to verify correctly because the old key remains in the list.

### Manual Verification with cosign

All verification artifacts can also be verified manually using [Cosign](https://docs.sigstore.dev/cosign/overview/). Download the http4k public key from the [signing key list](https://http4k.org/.well-known/cosign-keys.json), or use the `cosign.pub` file exported by the plugin.

#### Verify a JAR

{{< shell file="verify-jar.sh" >}}

#### Verify an SBOM

{{< shell file="verify-sbom.sh" >}}

#### Verify Provenance

{{< shell file="verify-provenance-example.sh" >}}

The `--private-infrastructure` flag tells cosign to skip public transparency log verification, which is expected for privately distributed artifacts. All signatures include trusted timestamps from the Sigstore Timestamp Authority.

### Gradle Dependency Verification

Gradle also has built-in support for verifying dependency checksums without any extra tooling. To pin SHA-256 checksums for all dependencies (including http4k artifacts from **[maven.http4k.org](https://maven.http4k.org)**):

{{< shell file="gradle-verify.sh" >}}

This generates a `gradle/verification-metadata.xml` file containing the expected checksums for every dependency:

{{< xml file="verification-metadata-example.xml" >}}

Gradle will verify these checksums on every build, failing if any artifact has been tampered with. Commit `verification-metadata.xml` to your repository. When upgrading http4k versions, re-run the command to update the checksums.

This is also the recommended way to verify the http4k Verify plugin itself. The plugin records its own version and JAR hash in the verification report, but for maximum assurance, pinning the plugin's SHA-256 in `verification-metadata.xml` ensures Gradle verifies the plugin before it even loads.
