---
title: "Publishing our homework: what running http4k responsibly actually takes"
category: "Security"
description: "http4k just hit the OpenSSF Best Practices silver badge at 100%. Here's the unglamorous work behind it - reporting, change control, signed releases, dependency hygiene - and the one gold criterion we're deliberately not chasing."
date: 2026-07-27
image: ossf.webp
---

<img class="imageMid my-4" src="./ossf.webp" alt="http4k and the OpenSSF Best Practices silver badge"/>

At the end of **[Tale of the tape](/news/tale-of-the-tape-claude-vs-http4k/)** we made a claim: that a project born out of the London XP community *publishing its homework should be the bare minimum, not the high-water mark*. Fair enough. So here's the homework.

http4k now carries the **[OpenSSF Best Practices silver badge](https://www.bestpractices.dev/projects/13771)** at **100%** - and the passing tier at 100% before it. None of what that took is glamorous. All of it is the stuff you'd actually want from a library you run in production.

**TL;DR:** http4k meets every OpenSSF passing and silver criterion - reporting, change control, quality, security, analysis. It's the unshowy plumbing of running a project responsibly, and this post is a tour of it. We're a handful of mostly-mechanical items off gold, plus **one criterion we're deliberately not chasing** - and that one's the interesting bit.

---

## Why bother?

A badge is a receipt, not an achievement. Nobody adopts http4k because of a shield in the README. But the work *behind* the shield is real, and it's the difference between a library you can bet a business on and one you're quietly nervous about. Over five million downloads a month and 200+ modules is a lot of other people's production traffic. The responsibility scales with it.

So the practices below aren't box-ticking for a badge - the badge just happens to be a convenient checklist for things we'd do anyway.

## The unglamorous list

**Tell people how to report a problem.** There's a real **[Coordinated Vulnerability Disclosure policy](/cvd-policy/)** and GitHub private reporting, with a committed response window - report privately, get a fix before anything goes public. No "email a maintainer and hope".

**Change control that assumes the worst.** The build and release pipeline is treated as an attack surface, with its own **[documented threat model](https://github.com/http4k/http4k/blob/master/SECURITY-DESIGN.md)**. GitHub Actions are pinned to exact commits, the Gradle wrapper is validated on every push, CI tokens run least-privilege, fork PRs never see secrets, and branch protection plus CODEOWNERS routes anything touching the build or release path to core maintainers. The workflows themselves are generated from code with **[typeflows](https://typeflows.io)**, so the whole pipeline stays consistent and reviewable rather than drifting through hand-edited YAML.

**Prove it works - every time.** Automated tests run on every check-in, we develop test-first, coverage sits north of 80%, and every bug fix ships with a regression test. When we pointed Claude Opus at the codebase for four rounds of adversarial security review, that whole exercise (**[three new CVEs and ~40 hardenings](/news/tale-of-the-tape-claude-vs-http4k/)**) only worked *because* the suite was there to catch what the fixes broke.

**Watch the dependencies.** Dependabot plus a submitted dependency graph on every build, with a documented remediation threshold: a release doesn't get cut while there's an unresolved high/critical dependency vulnerability or a licence violation. CodeQL runs SAST on every push and PR; detekt handles static analysis.

**Sign what we ship.** Artifacts are PGP-signed on Maven Central, and the **[enterprise distribution](/supply-chain-security/)** goes further - cosign signatures, SLSA Build L2 provenance and CycloneDX SBOMs for every module, verifiable offline. (More on making that assurance automatic in your own build very soon.)

None of that is a headline feature. It's just what "responsible" cashes out to when you write it down.

## The road to gold - and the fork we won't take

Gold is stricter, and we're at 78% of it. Some of the gap is quick and on the list - nudging statement coverage from 81% past 90%, and wiring a dynamic-analysis (fuzzing) step into the release pipeline. Some is a bigger lift, like applying per-file licence headers across the whole tree. We'll work through them.

One gold criterion we're deliberately leaving unmet: it wants a second human to review and approve every change before it merges. We practise iterative **trunk-based development** - maintainer commits land on trunk directly, in small continuous increments, under full CI, CodeQL, wrapper validation and branch protection. Every *external* contribution is still reviewed through CODEOWNERS; what we don't do is put a mandatory human review queue in front of the core team's own commits.

That's a values choice, not an oversight - and the OpenSSF entry records it as exactly that. Trunk-based development is how high-throughput teams actually ship, and we'll take fast, well-tested, continuously-integrated increments with automated gates over a review queue that mostly generates ceremony. Silver with our eyes open beats gold by process theatre.

## The point

The badge isn't the point; being the kind of project you can safely build on is. The homework above is what that looks like when nobody's grading it - the badge just confirms we're marking our own work honestly. If you want to check ours, it's all **[public](https://www.bestpractices.dev/projects/13771/silver)**, as it should be.

Peace out.

## // the http4k team
