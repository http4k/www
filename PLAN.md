# http4k company page redesign (running log)

Reskin `/company/` onto the comp (`~/Desktop/http4k Company.html`). **Comp
headlines adopted; no invented body/numbers/claims.** Existing prose, bios, stats
sourced from content/data. No emdashes; no markup comments.

## Done ✅
- `layouts/company/single.html` rebuilt: ink hero, mission, receipts strip,
  founders, services (numbered), partners, ink CTA, contact map.
- Copy/data: hero+section headlines from comp (factual); mission = `company.md`
  `.Content`; founders = `data/company.yaml` (bios + social icons, "Co-creator"
  role); receipts = `data/receipts.yaml` (5M+/200+/9yrs/0, NOT comp's 2M+);
  partners = `data/sponsors.yaml` (Springer Nature + Kotlin Foundation, both real);
  services = `company.md` highlights + new 4th "Hands-on training".
- NEW `assets/scss/shared/_company.scss` + `@import "company"`.
- Removed markup comments; replaced emdashes with hyphens across touched files.
- Verified: build green; desktop + mobile (receipts 2x2, all stacks).

## Open
- CTA "Explore Enterprise" -> `/enterprise/`; "Get in touch" -> mailto info@.
- Old `shared/cta` + `cta-width` no longer used on company page.
- Commit pending.
