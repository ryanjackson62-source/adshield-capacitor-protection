# AdShield Acquisition-Readiness Roadmap

Status: internal strategy document. This is a build and diligence plan, not a valuation, offer, legal opinion, or claim that the product is production-ready.

## Strategic objective

Build AdShield into a credible, independently testable consumer-protection technology asset that could support one or more of these outcomes:

1. Strategic acquisition by a cybersecurity, identity-protection, telecom, financial-services, insurance, or consumer-technology company.
2. Technology licensing or OEM integration into an established security product.
3. A commercial partnership or distribution agreement that validates demand before a sale.
4. A standalone subscription business if that proves more valuable than an early exit.

The company should not optimize for a sale at the expense of user safety, privacy, or technical credibility. Acquisition readiness means being able to demonstrate value and transferability, not rushing to market.

## What a strategic buyer could acquire

AdShield should be developed as a modular platform with clearly separated assets:

- **Consumer experience:** user-initiated message, offer, checkout, recommendation, and link checks.
- **Detection technology:** versioned rules, threat-intelligence ingestion, domain reputation, explainable evidence, calibrated risk labels, and evaluation tooling.
- **Android protection layer:** consent-based local DNS filtering VPN, once implemented and independently tested.
- **Threat intelligence operations:** review workflow, provenance, confidence, expiry, appeals/corrections, and signed feed distribution.
- **Integration surface:** documented SDK/API boundaries and OEM integration options, introduced only after security review.
- **Brand and distribution:** AdShield identity, customer trust, educational material, and measurable adoption.
- **Governance:** privacy architecture, security controls, incident response, release records, and audit evidence.

A buyer should be able to license or replace each module without needing undocumented knowledge of the original prototype.

## Product milestones

### Stage 1 — Credible prototype (current stage)

- Keep Consumer Check local-only and user-initiated.
- Maintain precise explanations and evidence for each warning.
- Add positive, legitimate near-neighbor, mixed-context, and adversarial tests for every detection family.
- Separate text analysis from URL, QR destination, file, image, audio, and video analysis. Never imply one engine performs another engine's job.
- Preserve fail-closed status reporting for native protection: unavailable means unavailable.
- Maintain a public-facing limitations statement before any public launch.

**Exit evidence:** reproducible test results, a versioned detection taxonomy, a documented threat model, and a clear list of implemented versus planned capabilities.

### Stage 2 — Measurable detection quality

- Create a consent-cleared, versioned evaluation corpus containing both scam and legitimate examples.
- Record source provenance, labeling rationale, category, language, attack pattern, and review date.
- Split data into development and held-out evaluation sets to reduce overfitting.
- Measure precision, recall, false-positive rate, false-negative rate, and coverage by category.
- Report confidence intervals and sample sizes; do not publish a single accuracy number without its test conditions.
- Test paraphrases, misspellings, multilingual variants, prompt injection, and mixed legitimate/malicious content.
- Add human review and correction procedures for disputed threat intelligence.
- Define release thresholds by harm category before enabling stronger warnings or automatic network blocking.

**Exit evidence:** reproducible benchmark report, evaluation scripts, documented limitations, and a process for updating the corpus without contaminating held-out tests.

### Stage 3 — Working Android protection

- Implement the Android VpnService lifecycle with explicit user consent, foreground notification, clear stop control, and truthful status states.
- Build and test bounded IPv4/IPv6 DNS packet handling, canonical domain processing, and correct DNS responses.
- Use a signed, versioned, expiring threat feed with rollback protection and a last-known-good offline state.
- Forward permitted DNS over authenticated encrypted transport using a protected socket to prevent VPN loops.
- Never intercept HTTPS, install a root certificate, read private messages, or use Accessibility Service for surveillance.
- Test battery use, latency, reconnects, captive portals, IPv6, private DNS, app-specific DoH/DoT bypass, other VPN conflicts, and offline operation.
- Complete independent Android security testing and required platform-policy review before distribution.

**Exit evidence:** reproducible device test matrix, crash and performance results, security review, signed-feed verification, and documented bypass limitations.

### Stage 4 — Commercial pilot

- Identify a narrow pilot segment with a real distribution partner; possible segments include family safety, small-business invoice fraud, financial institution account protection, or telecom customer protection.
- Define the buyer's problem, integration boundary, service-level expectations, support process, and success metrics before the pilot.
- Use opt-in, minimized telemetry. Do not collect message contents or browsing histories by default.
- Track activation, retention, warning review, confirmed user-reported incidents, false-positive appeals, latency, battery impact, and support burden.
- Establish pricing through customer interviews and pilot evidence rather than guessing.
- Obtain permission for any customer logo, case study, or endorsement.

**Exit evidence:** signed pilot agreement or documented customer validation, measurable outcomes, support playbook, and a repeatable integration package.

### Stage 5 — Strategic transaction readiness

Prepare a diligence room with:

- Product overview, architecture diagrams, data-flow diagrams, and capability/limitation matrix.
- Source repository, commit history, dependency inventory, software bill of materials, and reproducible build instructions.
- Detection taxonomy, benchmark methodology, test corpus provenance, and performance results.
- Threat model, penetration-test reports, vulnerability handling, incident response, and release-signing procedures.
- Privacy policy, data retention schedule, consent records, vendor list, and data-processing agreements where applicable.
- Ownership records for source code, designs, domains, brand assets, contractor work, and third-party licenses.
- Customer/pilot agreements, revenue and cost records, retention metrics, support history, and pipeline evidence.
- Open-source license and export-control review, privacy/security legal review, and a list of known liabilities.
- A transition plan explaining how a buyer can operate, maintain, and extend the technology.

**Exit evidence:** a complete, internally reviewed diligence package and a clear chain of title for material IP.

## Potential strategic buyer categories

These are target categories for future research, not claims of interest or endorsement:

- Endpoint and mobile security vendors.
- Identity theft and identity-protection providers.
- Telecom carriers and mobile virtual network operators.
- Banks, credit unions, payment networks, and fraud-prevention providers.
- Insurance companies offering identity, cyber, or family-protection products.
- Consumer platforms, device manufacturers, and managed security providers.

Do not approach a buyer with unsupported claims or disclose sensitive implementation details before protecting confidential information and obtaining appropriate legal advice.

## Business model options to test

1. **OEM / white-label licensing:** a partner embeds AdShield technology in an existing product.
2. **Per-subscriber licensing:** a carrier, bank, insurer, or security provider pays per protected account or device.
3. **SDK/API licensing:** partners integrate selected detection capabilities into their own user journeys.
4. **Direct subscription:** consumer or family plans, only after the value and support economics are validated.
5. **Enterprise protection:** business email compromise, invoice redirection, payroll diversion, and employee-initiated message review, without employee surveillance.

Do not finalize tier boundaries until a clear policy guarantees that essential safety checks and high-confidence critical warnings remain available without payment.

## Defensibility: what can become hard to copy

The phrase rules alone are not a durable moat. Potential defensibility comes from the combination of:

- High-quality, provenance-backed threat intelligence.
- A carefully labeled, legally usable evaluation corpus.
- Low false-positive rates supported by transparent evidence.
- Privacy-preserving on-device analysis.
- Reliable Android network protection and signed feed operations.
- Human correction and incident-response operations.
- Distribution partnerships and measured user trust.
- Documented integration interfaces and a strong security track record.

Do not claim exclusive ownership of public scam patterns or generic detection concepts. Protect original code, original datasets where legally appropriate, brand assets, and genuinely novel implementation details.

## Non-negotiable integrity rules

- Never say a message, link, device, or person is safe merely because no match was found.
- Never present a phrase match as proof of fraud.
- Never claim detection capabilities that have not been implemented and tested.
- Never send message contents to an AI provider, public scanner, or threat feed without explicit, informed user consent and a documented purpose.
- Never silently monitor private communications or browsing activity.
- Never let model-generated content directly publish blocklists, change entitlements, or take security actions.
- Keep the essential safety baseline independent of paid upsells.
- Publish limitations and material incidents honestly.
- Do not publish, merge to the production branch, or release a native app without explicit approval and completed release gates.

## Immediate execution priorities

1. Continue fixing analyzer correctness and expand the mixed-context test suite.
2. Add the next scam families with legitimate counterexamples and adversarial variants.
3. Build a versioned evaluation-corpus format and scoring report; do not use fabricated performance claims.
4. Verify the Android plugin build and resolve the outstanding CI and dependency-audit issues.
5. Implement the native VPN only in small, testable stages.
6. Map IP ownership, third-party licenses, and contributor rights before external commercial discussions.
7. Interview prospective distribution partners only when a clear prototype and honest capability statement are ready.

## Current maturity statement

AdShield is an early-stage prototype and technology development effort. The Consumer Check is a deterministic, local phrase-pattern analyzer; it is not a comprehensive fraud classifier or AI detector. The Android VPN package is a scaffold, not an active network-protection engine. A commercial pilot, independent security assessment, validated detection benchmark, and production release have not been established by this document.
