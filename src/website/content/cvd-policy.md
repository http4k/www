---
title: Coordinated Vulnerability Disclosure Policy
description: How to report security vulnerabilities in http4k products and what to expect from us
aliases: /security-policy
---

http4k Ltd is committed to the security of our software and our users. We welcome responsible security research and will work with anyone who reports vulnerabilities to us in good faith. This policy describes how to report security vulnerabilities in http4k products and what you can expect from us.

This policy applies to all http4k products, including the open-source http4k toolkit and http4k Enterprise Edition. It has been prepared in accordance with the EU Cyber Resilience Act (Regulation 2024/2847), including the coordinated vulnerability disclosure requirements of Article 13(6).

## How to Report a Vulnerability

**Email:** [security@http4k.org](mailto:security@http4k.org)

**GitHub:** Use [GitHub Security Advisories](https://github.com/http4k/http4k/security/advisories) (private vulnerability reporting — messages are encrypted by default)

Please include the following in your report:

- A description of the vulnerability and its potential impact
- Steps to reproduce the issue, or a proof-of-concept
- Affected product(s), version(s), and module(s)
- Any suggestions for remediation (optional but appreciated)
- Your preferred contact details and how you would like to be credited

## What to Expect

| Stage | Timeline |
|---|---|
| **Acknowledgement** | Within 48 hours of your report |
| **Initial assessment** | Within 5 working days |
| **Status update** | At least every 14 days until resolution |
| **Fix development** | Varies by severity (Critical: 48 hours target; High: 7 days; Medium: 30 days) |
| **Public disclosure** | Coordinated with reporter; typically within 90 days of the initial report |

We will keep you informed throughout the process and will not publish details of the vulnerability without coordinating with you first.

## Scope

#### In scope

- All http4k modules (published to both Maven Central and maven.http4k.org)
- https://www.http4k.org and subdomains
- https://maven.http4k.org

#### Out of scope

- Third-party services or infrastructure not operated by http4k Ltd
- Findings from automated scanning tools without demonstrated impact
- Social engineering, phishing, or physical attacks
- Denial-of-service attacks
- Vulnerabilities in software or systems not developed by http4k Ltd

## Safe Harbour

http4k Ltd will not pursue legal action against security researchers who:

- Act in good faith and in accordance with this policy
- Avoid accessing, modifying, or deleting data that does not belong to them
- Do not exploit a vulnerability beyond the minimum necessary to confirm it exists
- Do not publicly disclose the vulnerability before a fix is available and we have agreed on a disclosure timeline
- Report findings promptly and do not use them for personal gain beyond recognition

If you act in good faith under this policy, we will consider your research to be authorised and will not initiate legal proceedings against you in relation to your research.

## Our Commitments

When we receive a valid vulnerability report, we commit to:

1. **Acknowledge** your report within 48 hours.
2. **Assess** the vulnerability and assign a severity rating using CVSS v3.1.
3. **Develop and release** a fix according to our severity-based timelines.
4. **Assign a CVE** for confirmed vulnerabilities and publish a GitHub Security Advisory (GHSA).
5. **Credit you** in our security advisory (unless you prefer to remain anonymous).
6. **Update our SBOMs** — we publish CycloneDX SBOMs for every module, signed with cosign.
7. **Notify our users** via our [security advisories page](/security/), GitHub, and direct communication with Enterprise customers.
8. **Report upstream** — if the vulnerability is in a third-party or open-source component integrated into our products, we will report it to the component maintainer and share relevant fixes, in accordance with CRA Article 13(6).
9. **Report to authorities** — where required by the EU Cyber Resilience Act (Article 14), we will notify the designated CSIRT and ENISA of actively exploited vulnerabilities within the mandated timeframes.

## Upstream and Downstream Coordination (CRA Article 13(6))

When we identify a vulnerability in a component we integrate — including open-source dependencies — we will:

- Report the vulnerability to the person or entity maintaining that component
- Share relevant code or documentation for the fix, in a machine-readable format where appropriate
- Coordinate disclosure with the component maintainer

Similarly, we expect downstream users who discover vulnerabilities in http4k products to report them to us using this policy, so we can coordinate remediation across the ecosystem.

## Vulnerabilities in Dependencies

If you discover vulnerabilities in http4k's dependencies, these should be reported directly to the respective project maintainers. We do not consider the inclusion of a vulnerable dependency in http4k itself to be a vulnerability in http4k, as developers are free to override dependency versions. We strive to keep dependencies updated and will incorporate security patches in subsequent releases.

## Recognition

We maintain a list of security researchers who have responsibly disclosed vulnerabilities to us on our [security advisories page](/security/). If you would like to be recognised, please let us know in your report.

## Contact

- **Email:** [security@http4k.org](mailto:security@http4k.org)
- **security.txt:** [https://www.http4k.org/.well-known/security.txt](https://www.http4k.org/.well-known/security.txt)
- **Preferred languages:** English

*This policy is reviewed annually and updated as needed.*
