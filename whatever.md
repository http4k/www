# Header treatment + nav audit - coherent rules for the http4k site

## Context

The restyle gave most page types a deliberate design, but **page headers are still
inconsistent**: the dark "ink" hero reads as seamless on home/product pages but
bolted-on elsewhere (a white nav strip sits on the dark band), and the lighter
pages now lead **several** different ways. Separately, the **nav buries the
commercial pages** (Enterprise, Supply Chain, Pro) as scattered, duplicated
sub-items when they should be the most prominent things in the bar.

This document is a **reference deliverable only** - the agreed scope is rules +
audit, **no code changes**. It catalogues today's state, defines the target
rules, and lists what violates them so the fixes can be scheduled later.

**Refreshed** after the latest restyling round, which redesigned several pages
off the bare template: Home spotlight, **FAQ**, **Quickstart**, **Community**, and
the **Tutorial / How-to detail** pages (now on a shared `.reading` header). Those
are reflected below.

Decisions locked with the user:
- **Pro hero -> dark** (joins the Enterprise/Company/Overview pitch family).
- **White headers -> two tiers**: section-landing/index pages get the designed
  white band; deep reading pages (docs, legal, articles) keep a plain/quiet header.

---

## Part 1 - Header catalogue (today)

**Two navbar systems**
- Transparent nav *inside* a dark hero - `landing/baseof.html` (home),
  `product/baseof.html` (all `content/_sites/*`). Seamless.
- White sticky nav (`.site-header`, `_site.scss`) - every page on
  `_default/baseof.html` (everything else).

**Header/hero treatments in use**
| Treatment | Class / source | Pages |
|---|---|---|
| **A · Dark ink hero** | `.landing--ink` / `.ink-hero` (`_primitives.scss:46`) | Home, Products *(nav integrated)* · Overview, Enterprise, Company, Security-list, **Community** *(new)*, 404 *(white nav stacked on top)* |
| **B · White list-header band** | `shared/list-header.html` (`_lists.scss`) | Learn, Solutions (single+list), Tutorial-list, Howto-list, News-list, Showcase |
| **C · White bespoke light hero** | `.pro-hero` (`_pro.scss`) | Pro - still a near-duplicate of B |
| **D · Shared reading header** *(new)* | `.reading` = `docs-crumbs` breadcrumb + eyebrow/meta chips + title + lede (`_docs.scss`) | Quickstart, Tutorial-single, Howto-single |
| **E · FAQ header** *(new)* | `.faq-page` = eyebrow + title + lede + accordion (`_primitives.scss`) | FAQ |
| **F · Docs reading header** | `.docs` 3-pane, `page-content` with `docs-crumbs` + eyebrow + `<h1>` | Ecosystem single |
| **G · White article header** | `.article-head` / `.advisory-detail` | News-single, Security-advisory-single |
| **H · Bare white `<h1>`** | plain `<header>` in `page-content` (`_default/single.html`, `ecosystem/list.html`) | Ecosystem-list, all legal/misc markdown (rationale, terms, privacy, disclaimer, cvd-policy, code-of-conduct, commercial-license/terms, consulting, training, lts-support, performance, module) |

Net change since the first audit: **D and E are new** (they replaced bare `<h1>`
for quickstart/tutorial/howto/faq); **Community moved from H to A**; the bare-`<h1>`
bucket (H) shrank to the legal/misc long-tail + the ecosystem list.

---

## Part 2 - Target rules (headers)

**Rule 1 - Dark ink hero = "pitch" pages.** Top-of-funnel / brand / sell-the-story.
Members: Home, all Products (`_sites/*`), Overview, Enterprise, Company, **Pro**,
Community, Security landing, 404.
→ On *all* of these the nav must be **integrated into the dark band** (transparent
nav, white links), exactly like home/products today.

**Rule 2 - White header = "read / browse" pages**, in two tiers:
- **Tier 2a · designed white band** (`list-header`): section-landing & index pages
  you arrive at to choose where to go - Learn, Solutions, Ecosystem-list,
  Tutorial-list, Howto-list, News-list, Showcase.
- **Tier 2b · quiet reading header**: deep reading / utility pages - docs
  (ecosystem single), all legal/policy pages, FAQ, Performance, Quickstart,
  Tutorial-single, Howto-single, News-single, Security-advisory-single.

**Rule 3 - One header component per tier.** `list-header` is the single source for
2a. For 2b, `.reading` (breadcrumb + eyebrow + title + lede) is the emerging shared
component - everything quiet should route through it (or the article variant for
dated content). No bespoke per-page clones.

---

## Part 3 - Violations to address (headers)

1. **Nav bolted onto dark heroes (primary "feels off").** Overview, Enterprise,
   Company, Security-list, **Community** *(new this round)* and 404 use `.ink-hero`
   under a *separate white nav bar* → white strip on a dark band. Home/Products
   integrate it.
   *Fix:* give these page types a dark-hero base (or make the nav transparent when
   the first section is `.ink-hero`) so the nav sits inside the dark band.
   Files: `_default/baseof.html`, `_site.scss` (`.site-header`), the
   `overview/enterprise/company/security/community/404` layouts.
2. **Pro uses a light hero** but is a pitch page. *Fix:* swap `.pro-hero` for the
   dark `.ink-hero` treatment; fold its bespoke `pro-hero__*` styles away.
   Files: `pro/single.html`, `_pro.scss`.
3. **`pro-hero` duplicates `list-header`/`ink-hero`.** Delete once #2 lands.
4. **2b reading headers have diverged into 3 near-identical variants.** This round
   fixed the *bare* `<h1>` on quickstart/tutorial-single/howto-single (now the
   shared `.reading` header D) and faq (`.faq-page` E) - but E and the ecosystem
   docs header (F) each roll their own markup that is ~the same as D.
   *Fix:* fold E and F onto the shared `.reading` header (D) so 2b has one source.
5. **Legal/misc long-tail is still bare `<h1>`** via `_default/single.html`
   (privacy, terms, disclaimer, cvd-policy, code-of-conduct, commercial-*,
   consulting, training, lts-support, performance, module) plus the **ecosystem
   list**. *Fix:* route `_default/single.html` (and `ecosystem/list.html`) through
   the shared 2b header + `.article-body`, lifting the whole long-tail at once.

Resolved since the first audit: tutorial-single & howto-single no longer hand-roll
a header (now share `.reading`); community/quickstart/faq are off the bare template.

---

## Part 4 - Nav audit (`config.yaml` `menus.nav`)

*(Unchanged this round - nav has not been touched, so all of this still stands.)*

Goal the user set: **make Enterprise, Supply Chain and Pro prominent.** Today they
are demoted into sub-items and scattered/duplicated.

**Items that don't earn their keep / are broken:**
- **Pro listed twice** - `ecosystem-pro` and `solutions-pro`, both → `/pro/`. Dupe.
- **Enterprise split & confusingly named** - `ecosystem-enterprise` "http4k
  Enterprise" → `/ecosystem/enterprise/` (docs) vs `solutions-enterprise`
  "Enterprise Edition" → `/enterprise/` (marketing). Two names, two destinations.
- **"Solutions" is a 9-item grab-bag** - mixes commercial offerings (Enterprise,
  Supply Chain, Pro, Consulting, Training) with security housekeeping (Security
  Advisories, **CVD Policy**) and a **Slack** link.
- **Three "Overview" items** across About / Ecosystem / Solutions dropdowns.
- **Tooling has two identical URLs** - "Toolbox (Web)" and "Toolbox CLI" both →
  `toolbox.http4k.org`. **Public Signing Keys** (cosign json) is a deep-cut.
- **Two Slack links** in two different menus (EE Slack, Community Slack).
- Deep-cuts belonging on their own pages, not the top bar: CVD Policy, Signing
  Keys, Changelog.

**Recommended reorg (elevate the money pages):**
- Add a dedicated top-level **Products** (or "Pricing") menu leading with the
  commercial trio, in priority order: **http4k Pro · Enterprise Edition · Supply
  Chain Security** (then Consulting, Training). Consider surfacing **one** of
  these (Enterprise or Pro) as a standalone top-level link/CTA, not inside a
  dropdown.
- **Deduplicate**: Pro once; Enterprise once (pick canonical `/enterprise/` for
  marketing, keep docs under Ecosystem only if genuinely distinct, and rename so
  the two aren't both "…Enterprise").
- **Move security housekeeping off the top bar**: Security Advisories, CVD Policy,
  Signing Keys → onto the Security page (which already links CVD + cosign keys),
  leaving at most one "Security" entry in nav.
- **Collapse Tooling dupes** (Toolbox web/CLI → one entry) and **consolidate the
  two Slack links**.
- Shrink "Solutions" to non-commercial support items (Consulting, Training) once
  the commercial trio moves to Products, or drop the menu entirely.

File: `src/website/config.yaml` (`menus.nav`).

---

## Verification (when the fixes are eventually built)

Rules-doc only for now - nothing to run. When implemented:
- `hugo server` (or the project's build task) and eyeball each page *type* once:
  one dark-hero pitch page, one 2a band page, one 2b reading page - confirm the
  nav integrates on dark and no white strip sits on a dark band.
- Click every nav item; confirm no dead/duplicate destinations and that
  Pro/Enterprise/Supply-Chain are reachable within one hop from the top bar.
- `./testLinks.sh` for link integrity after the nav edit.
