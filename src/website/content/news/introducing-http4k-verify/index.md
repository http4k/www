---
title: "Trust every dependency: introducing http4k Verify"
category: "Security"
description: "New in this release: nothing. http4k Verify has been proving every http4k artifact in your build is the one we signed and shipped since 6.40.0.0. This is just the post we owed you."
date: 2026-08-19
image: verify.webp
---

<img class="imageMid my-4" src="./verify.webp" alt="http4k Verify - cryptographic chain-of-custody for every dependency"/>

Back in **[Tale of the tape](/news/tale-of-the-tape-claude-vs-http4k/)** we spent four rounds hardening the http4k source, then flagged the other half of the problem: hardening the code proves the code is sound. It says nothing about the bytes that actually land in your  build.

This is the post about the other half.

New in the latest http4k release: nothing. **http4k Verify**, a Gradle plugin that proves every http4k artifact in your build is the exact one we signed and shipped, went out in **6.40.0.0** and has been doing so in builds ever since. We just never got round to writing it up. This is either a damning indictment of our marketing, or the highest compliment you can pay a build plugin... we're going with the second one.

**TL;DR:** it ships with **[http4k Enterprise Edition](/enterprise/)**. One line in your build file, and every http4k dependency has its JAR signature, CycloneDX SBOM, SLSA provenance and licence report checked **before your code compiles**. Tampered artifact? Build fails. Clean build? You get a verification report to hand your auditors. Zero config, results cached, costs you nothing day-to-day.

---

## Why this exists

Nobody ships an app any more. They ship a (sometimes very weighty) dependency tree, and every node in it is someone else's code arriving over the wire. The interesting attacks stopped being "find a bug in the app" a while back. They're "get something into the supply chain and let the build tools carry it the rest of the way".

Regulators have definitely noticed. The **EU Cyber Resilience Act**'s reporting obligations start on **11 September 2026**, and they reach products already on the market, not just new ones.

Mind you, it doesn't mandate any of this - the CRA never names SLSA provenance or signed attestations. What it actually wants is that you know what's in your product and can get a fix out fast when something in it turns out to be exploited. Provenance and signatures are just the cheapest honest way we know to answer that on demand. Anyone telling you otherwise is probably selling you something.

(The wider picture is messier than that. The US spent this year going the other way and rescinded the federal mandates for exactly this kind of evidence. Not really good enough it turns out because the security questionnaires your customers send you won't be any shorter.)

So, you can scramble for that evidence at audit time, or capture it at build time and get on with your life. Verify is firmly in the second camp.

## What we already shipped

Every artifact we publish to **[maven.http4k.org](https://maven.http4k.org)** - all 200+ modules, community (`org.http4k`) and pro (`org.http4k.pro`) alike - carries its evidence with it:

- a **cosign signature** for the JAR, timestamped by the Sigstore Timestamp Authority
- a **CycloneDX SBOM** listing every transitive dependency
- **SLSA Build Level 2 provenance** tying the artifact to the exact commit and pipeline that built it
- a signed **licence compliance report**

http4k release signing runs in a job isolated from the build, so the job that compiles the code holds no keys. That's what's known as a hardened Level 2 posture, and we're deliberately precise about that rather than rounding up because these things matter. (Where the stricter Level 3 fits is on the **[Supply Chain Security](/supply-chain-security/)** page.)

Note *where* all that lives, because it starts to matter in October. None of it goes to Maven Central and never has: Central gets the JARs and their standard PGP signatures, which isn't changing either.

What *is* changing is how often http4k reaches Central at all. New Sonatype publishing limits mean releases there are dropping to roughly quarterly from **1 October 2026**, while `maven.http4k.org` carries on at the usual 1-2 weeks. Nothing is being withdrawn, and the community edition stays free, stays Apache-2.0 and stays on Central. But if you want the evidence *and* you want it current, which repository you pull from has stopped being a detail. Full story on **[Distribution & release channels](https://www.http4k.org/distribution/)**.

The signing, the provenance, the SBOMs: none of it is bolted-on marketing, it's just how http4k is run - we went through the whole unglamorous list in **[Publishing our homework](/news/publishing-our-homework/)**. **[http4k Verify](https://verify.http4k.org)** is the next link in the chain: it takes the same assurance from *how http4k is built* to *what lands in your build*.

## One plugin. One line.

We know that security is boring and hard and not very fun all at the same time. But with Verify, here's the whole integration:

```kotlin
plugins {
    id("org.http4k.verify") version "6.58.0.0"
}
```

That's it. On the next Gradle build the plugin grabs our [signing key list](https://http4k.org/.well-known/cosign-keys.json), resolves the sigstore bundles for every http4k dependency, and checks each signature (with the correct key for that artifact - so key rotation just works). The provenance carries the fingerprint of the key that signed it.

Signature doesn't match? Boom - the build stops dead in its tracks.

Every run also dumps what it touched into a `verification-report.json` - a timestamped record of which artifacts, with which hashes, were verified against which signatures. That report is the point and you can drop it straight into your audit trail.

Verification is key-based rather than log-based, so it works **fully offline**, against a key you already trust. No CLI tools required, nothing exotic in your infrastructure, and it works through **Artifactory**, **Nexus** or anything else proxying **[maven.http4k.org](https://maven.http4k.org)**.

## What you should do

If you're on **[Enterprise Edition](/enterprise/)** you already have this. Add the plugin and you're done - full setup is in the **[Verify reference docs](/ecosystem/enterprise/reference/verify/)**.

Not on EE, and this is the kind of evidence your security team is going to start asking for? **[Get in touch](/enterprise/)** or email enterprise@http4k.org and we can talk. There's more at **[verify.http4k.org](https://verify.http4k.org)**.

Trust every dependency. Verify every build.

Peace out.

## // the http4k team
