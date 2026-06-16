---
title: "Tale of the tape: Claude vs the http4k codebase"
description: "We put http4k in the ring with Claude Opus for four rounds, patching between each. Three new CVEs, an update to our first one, four advisories, ~40 hardenings. Here's what landed - and why a small, minimal-deps codebase is the only kind you can actually audit like this."
date: 2026-06-16
image: http4k-vs-opus.webp
---

<img class="imageMid my-4" src="./http4k-vs-opus.webp" alt="Tale of the tape: Claude vs the http4k codebase"/>

Back in the **[v6 announcement](/news/http4k-v6-still-the-most-testable-web-toolkit-on-the-planet/)** we celebrated http4k's first CVE (**[CVE-2024-55875](/security/cve-2024-55875)** - a juicy 9.8 😈). Rite of passage.

http4k is past a few million downloads a month and 200+ modules in the ecosystem. One CVE down but statistically there were going to be more. So the question was less *if* and more *where* - and how do you find them in a codebase that no single human is auditing end-to-end?

So we put http4k in the ring with Claude Opus - four full rounds, patching between each one and sending the fixed codebase back in to see what survived. Each round found things the previous ones had missed. This post is what turned up, what didn't, and what we learned about pointing an LLM at a nine-year-old codebase that nobody is reading cover-to-cover.

A bet runs through the whole exercise: that a codebase deliberately kept small enough to read end-to-end is the only kind you can actually audit like this. http4k has held that line by design. The findings below are the receipts.

(There's a second half to this story we get to at the bottom - supply-chain attacks, and how you know the bytes you pull off Maven Central are actually the ones we shipped. Worth scrolling for.)

---

**TL;DR:** Across **[v6.48.0.0](https://github.com/http4k/http4k/releases/tag/6.48.0.0)**, **[v6.49.0.0](https://github.com/http4k/http4k/releases/tag/6.49.0.0)** and **[v6.50.0.0](https://github.com/http4k/http4k/releases/tag/6.50.0.0)** we shipped ~40 security hardening changes across a dozen-plus modules. **Three new CVEs** (gzip decompression bomb, Digest auth URI-binding, Digest auth algorithm-ignored), **an update to our first CVE** ([CVE-2024-55875](/security/cve-2024-55875), closing a residual billion-laughs gap the original fix didn't cover), and **four non-CVE advisories** for things like bad Digest nonce-verifier defaults and a cross-origin cookie-storage footgun. None of it landed in http4k's everyday request-handling path - which is exactly where the design effort has always gone. **Upgrade to 6.50.0.0** and you're covered.

**Verdict:** Claude landed some real hits. http4k took them, patched itself up, and walked out stronger.

---

#### Where the hits landed

The genuinely happy news: none of the above touched http4k's everyday request-handling path. The HTTP model, routing, lenses, server config, and the filters most apps actually wire in all came through the pass cleanly. The findings clustered in opt-in modules and older API choices - Digest auth, XML parsing, the gzip filter, client-side cookie storage - which is where you'd hope they'd land, but also exactly the kind of code that doesn't get the day-one scrutiny the core does. The bit of http4k that most people touch every day is the bit we most wanted to come through cleanly. It did.

#### What you should do

If you're on http4k anywhere in the v6 line, simply **upgrade to [v6.50.0.0](https://github.com/http4k/http4k/releases/tag/6.50.0.0)** or later. The fixes are spread across a wide variety of modules so a blanket dependency bump is the cleanest path. Check the round-by-round below to see which findings directly apply to your deployment. We've broken the APIs where we feel we needed to in order to make choices simpler.

Still on **v5** or **v4**? LTS backports of these fixes are available as part of the **[http4k Enterprise Edition](/enterprise)** - get in touch if you need security patches without taking the v6 jump.

#### Round-by-round

**[CVE-2026-53659](/security/cve-2026-53659) - gzip decompression bomb.** Our gzip request-decoding filter had no size cap; a few KB of crafted input could decompress to gigabytes and take the server down. Unpatched for ~9 years. **v6.49.0.0** caps at 10MB by default.

**[CVE-2026-54148](/security/cve-2026-54148) - Digest auth: request URI not bound.** Digest auth verification didn't check the client's claimed URI against the actual request URL - so a captured response could be replayed against any other URL in the same realm, breaking the per-URL binding the scheme is supposed to enforce. Present since we introduced Digest auth in 2021. Fixed in **v6.50.0.0**.

**[CVE-2026-54147](/security/cve-2026-54147) - Digest auth: algorithm ignored.** Digest auth verification silently used MD5 regardless of which algorithm was actually configured - so deployments believing they were running SHA-256 were quietly inheriting MD5's collision weaknesses. Same vintage as the URI bug, fixed in the same release.

**[CVE-2024-55875 follow-up](/security/cve-2024-55875) - Residual XXE class fixed.** The [Dec 2024 fix](/security/cve-2024-55875) closed external-entity attacks but left DOCTYPE acceptance and billion-laughs DoS in place. **v6.50.0.0** closes the residual; the existing advisory is updated.

#### The wider hardening

The same pass produced a long tail of smaller tightenings - DoS body and multipart caps, secret redaction across Secrets and OpenTelemetry, end-to-end OAuth hardening (PKCE, nonce, open-redirect), RFC 6265 cookie scoping, tighter reverse-proxy host matching, and a handful of crypto / API renames for clarity. Details across **v6.48.0.0**, **v6.49.0.0** and **v6.50.0.0** in the **[changelog](/ecosystem/changelog/)**.

#### Why we could even do this

None of the above is normal. The default for a JVM web app is a dependency tree with hundreds of transitive libraries, most of them you've never opened, several of them you couldn't read inside a working week. When something like the gzip bomb sits in *that* tree, the only signal you get is the CVE filing - months or years after the bytes arrived in your build.

http4k is a different shape on purpose. 200+ modules in the ecosystem, but each one is aggressively small, and the dependency surface is deliberately minimal. That's been the http4k bet since day one. Every finding above lived in *our* code, on our trust boundaries - not buried three Jars deep in something we couldn't even see.

That's a big part of why any of this was findable. Less surface area to defend, less foreign code to trust, fewer places for a nine-year-old gzip bug to hide. We've been making the minimal-deps argument for years on ergonomic grounds. This audit is the version where the argument is about whether you can sleep at night.

#### What this looked like in practice

Here's what the workflow actually looked like.

**The setup.** We focused on trust-boundary code: anywhere attacker-controlled bytes (request body, headers, query, cookies) met framework parsing or validation logic. So we fanned Claude Opus out across the codebase in parallel - one agent per module, each running a focused security review on its assigned territory - then funnelled every finding through a single grading loop that asked three questions: *is the exploit pathway real?*, *is the impact concrete?*, and *does the proposed fix preserve legitimate behaviour?* We ran the full sweep **four times end-to-end**, applying fixes between each pass and feeding the patched codebase back in for the next round. Fresh Claude context each time; freshly hardened tree each time. We did it this way partly because a single sweep produces enough noise (false positives *and* false negatives) that you shouldn't trust it on its own, but mostly because we kept being surprised: even after three sweeps and a stack of applied fixes, round four still surfaced material the earlier rounds had missed entirely. One pass is never enough.

**Where Claude landed punches.** The biggest wins were footguns we'd internalised as "by design." The gzip filter never had a decompression cap because nobody asked the question. The XML parser was using JDK defaults, which is exactly what causes XXE everywhere else in the ecosystem - and even our existing CVE fix had missed one class of attack (billion-laughs) until this pass surfaced it. A clutch of older API choices similarly survived years of "well, that's how it works" until the question finally got asked properly. None of these were exotic; they were the kind of thing a careful reviewer should find - and a careful reviewer eventually did.

**Where Claude swung and missed.** The first passes had false positives. Claude's instinct on a quick scan was to flag things that *looked* dangerous but turned out to be guarded elsewhere, or to misread which function was actually called in production paths. Every finding had to be verified against the code: *show me the line, show me the test, show me it fails before the fix and passes after.* On several items the initial framing was wrong: a "vulnerability" turned out to require the end user to misuse an API (footgun, not CVE); an "attacker-controlled" string turned out to be developer-supplied. A maintainer's judgment on what http4k callers actually do in practice corrected the framing more than once. AI is a useful additional lens but it's not a replacement for reading the code, running the test, and making the call yourself. Human. In. The. Loop.

**A few patterns we'll be looking for next time.** Token comparisons (CSRF, nonce, signatures) that don't use a constant-time check are easy for an LLM to spot and easy for a human to miss - we tightened a handful as part of the OAuth work. Insecure defaults dressed up as ergonomic helpers - the always-true nonce verifier, the too-permissive reverse-proxy matcher - read fresh to Claude where the maintainer reads them as "how we've always done it." Parser default-acceptance gaps recur across format modules - once you've seen one (XXE, billion-laughs, gzip bombs), you spot the pattern. And size limits on attacker-controlled input keep being the boring-but-load-bearing finding nobody writes tickets for.

**Scorecard.** Across three releases this pass surfaced enough real issues to justify the time spent. The lesson we're taking forward isn't "AI fixes security bugs" - it's "AI is a credible way to keep up regular security review on a 200-module codebase that no single human is going to audit end-to-end." We'll be doing more of these passes, on a recurring cadence rather than as a one-off.

#### Why we put this in public

We ran this exercise - and published the result - because we aren't afraid of what the models find. Plenty of large, well-resourced companies are running the same kind of LLM-assisted review on their own codebases right now. You aren't reading those findings. The CVEs don't get filed, the advisories never appear, the patches land discreetly (or not at all), and the rationale lives on in internal Confluence pages.

We're proud of our record - but we're under no illusion that http4k is bug-free. Anything shipping at this scale will have gaps; the round-by-round above is proof ours does. The dividing line isn't whether the bugs exist, it's what you do when you find them.

The reasons big shops pick quiet are structural. Legal teams treat public disclosure as risk to be minimised, not as information owed to users. Enterprise procurement uses "disclosed CVE count" as a proxy for product security - producing the obvious perverse incentive that every CVE filed becomes a sales-cycle conversation to defuse. Internal security teams are rewarded for fixing bugs, not for publishing them. The blast radius of a single disclosure scales with the customer base, so *"patch quietly, never speak of it"* becomes the path of least resistance.

None of that makes it the right call. The companies sitting on hundreds of millions of installs have the same classes of bug in their trees that we just fixed in ours. They aren't telling you. They should be - and if you ship security-sensitive code that other people depend on, you should too.

The threat picture only goes one way. Supply-chain incidents in the JVM ecosystem land weekly. Regulatory pressure (EU CRA, NIST SSDF) is firming up around evidence-of-due-care. AI-augmented attackers can run this exact kind of pass on your codebase offensively, looking for things to *exploit* rather than fix. *"We'll do it next quarter"* stops being a defensible answer in that environment. We'd rather find ours ourselves, on our schedule, than read about them in someone else's writeup.

And while we're at it: **this work costs real money.** Tokens are not free (as in beer). Maintainer triage time isn't free. The nine years of attention that put us in a position to run the pass at all isn't free either. If your company runs on open-source libraries you don't pay for, and your security posture quietly depends on those libraries staying audited - someone is subsidising you, and it isn't us volunteering for it. **Fund the OSS you depend on.** The maintainers will spend it on exactly this kind of work.

LLM-assisted review isn't a silver bullet. It produces noise (the false-positive rate on the first pass was real), it doesn't replace maintainer judgment (a lot of our triage was deciding what *wasn't* a CVE), and it doesn't audit anything you don't aim it at. As a force multiplier on the security review you were already going to do - or, more honestly, *should have been doing* - it's earning its keep. A small Kotlin library born out of the London XP community publishing its homework should be the bare minimum here, not the high-water mark.

#### What's next

- Enforcing PKCE on our OAuth server is opt-in today; we'll flip it to the default in a future major release.
- We'll run another security review pass against the next major release window.
- If you're using http4k in security-sensitive contexts and you spot something we missed, **[get in touch](mailto:contact@http4k.org)** - either via the **[Kotlin Slack](https://kotlinlang.slack.com)** or by opening a private security advisory on the **[http4k repo](https://github.com/http4k/http4k/security/advisories/new)**.

#### Coming up: `http4k verify`

Hardening the source (the work this article is about) is one defence. *Proving* that what you pull off Maven Central is the same bytes we signed and shipped is the other - and given everything above about the threat picture, the one we've been getting more serious about too. **`http4k verify`** is a Gradle plugin that validates JAR signatures, CycloneDX SBOMs, SLSA provenance attestations, and license compliance across the entire http4k dependency tree, automatically, before your code compiles. Built for the enterprise / regulated / CRA-exposed audience that's going to need this evidence anyway. Early access is live now at **[verify.http4k.org](https://verify.http4k.org)**; full write-up when it lands formally.

Less surface area, less to hide. That's the http4k way.

Let us know what else we should be looking at.

Peace out.

#### // the http4k team
