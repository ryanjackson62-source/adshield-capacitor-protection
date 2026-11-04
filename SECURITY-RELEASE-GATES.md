# AdShield Security and Release Gates

**Purpose:** Protect end users and AdShield itself. This document is a release gate, not a claim that any listed control is already implemented.

## Release rule

No feature may be described as active protection until its implementation, failure states, limitations, and tests have been reviewed. A green compile is necessary but never sufficient. Keep incomplete controls visibly marked as unavailable or degraded.

## 1. User safety and detection quality

- [ ] Separate verified threat-intelligence matches, heuristic risk indicators, pending community reports, no-known-match results, and unavailable checks.
- [ ] Never label a domain or message "safe" solely because no threat is known.
- [ ] Explain the evidence behind warnings and give a practical next action.
- [ ] Measure false positives and false negatives against a versioned, representative test corpus.
- [ ] Test scams involving impersonation, fake shops, delivery notices, banks, government agencies, jobs, romance, investment, recovery services, sextortion, and payment redirection.
- [ ] Test AI-assisted personalization, voice-clone scenarios, synthetic identities, fake support chatbots, and AI-generated phishing pages.
- [ ] Do not claim reliable AI-media detection without measured, independently reviewed performance.
- [ ] Provide an appeal/report-correction path for incorrectly flagged legitimate sites.

## 2. Android network engine

- [ ] Explicit, understandable Android VPN consent; never imply consent was granted when it was not.
- [ ] Foreground service and persistent notification with a clear stop control.
- [ ] Correct lifecycle handling for revoke, force-stop, reboot, process death, network changes, airplane mode, captive portals, and competing VPNs.
- [ ] Strict IPv4/IPv6, UDP, DNS, fragmentation, length, and checksum validation.
- [ ] DNS parser fuzzing and malformed-packet regression corpus.
- [ ] Correct DNS transaction IDs, flags, response codes, TTLs, and EDNS behavior.
- [ ] Protected DoH upstream socket to prevent routing loops; TLS certificate validation.
- [ ] Signed threat-feed verification, key rotation, expiry, monotonic versions, rollback defense, and atomic updates.
- [ ] Bounded cache and memory; safe behavior under low storage, low memory, and network loss.
- [ ] Device tests across supported Android versions, Wi-Fi/cellular, IPv4/IPv6, OEM variants, and battery conditions.
- [ ] Explicitly communicate bypass cases: app-specific DoH/DoT, hard-coded IPs, proxy, other VPN, and unsupported traffic.

## 3. AI and user-submitted content

- [ ] Message Check is user-initiated paste/share; no background message reading.
- [ ] Minimize content sent to external services; disclose every data flow and obtain consent.
- [ ] Default to no retention of message content; define short, documented retention only where necessary.
- [ ] Treat messages, webpages, files, images, and model outputs as untrusted data, never as instructions.
- [ ] Prompt-injection and adversarial-input test suite.
- [ ] Model output cannot directly publish blocklists, block domains, change entitlements, or execute privileged actions.
- [ ] Validate model output against strict schemas and policy checks.
- [ ] Include uncertainty, evidence, and escalation guidance; avoid binary "scam/safe" certainty.

## 4. Backend, data, and company security

- [ ] Server-side authorization for every sensitive operation; deny by default.
- [ ] Tenant isolation tests for all organization/business data.
- [ ] Secrets stored server-side; never embedded in app bundles, logs, source control, or client configuration.
- [ ] Least-privilege service credentials, rotation, revocation, and access audit.
- [ ] Rate limits, abuse detection, spam controls, and safe handling of community reports.
- [ ] Validate and normalize all inputs; parameterized database access; output encoding.
- [ ] Minimize telemetry; do not collect browsing histories or full URL query tokens by default.
- [ ] Encryption in transit and at rest; documented key management and backup recovery.
- [ ] Security logging without passwords, tokens, message contents, or unnecessary personal data.
- [ ] Incident response plan, vulnerability disclosure contact, patch process, and user notification criteria.
- [ ] Dependency inventory, vulnerability scanning, license review, and reproducible build records.

## 5. Supply chain and release integrity

- [ ] Pin and review build tool versions; use lockfiles where applicable.
- [ ] CI must run TypeScript build, Android compile, unit tests, static analysis, and dependency audit.
- [ ] Review all dependency advisories; do not auto-apply major upgrades without compatibility tests.
- [ ] Protect release branch and require review for security-sensitive changes.
- [ ] Sign release artifacts; document provenance and version.
- [ ] Stage rollout, monitor crash/security signals, and maintain rollback capability.
- [ ] No public release until physical-device acceptance and independent security review.

## 6. Platform expansion

- [ ] Maintain a shared, platform-neutral threat intelligence and detection contract.
- [ ] Implement a separate native protection adapter per operating system.
- [ ] Document platform permission and capability limits; never imply identical coverage across devices.
- [ ] Review each platform's store policies, privacy requirements, and background execution restrictions before release.

## 7. Status language

Use only these meanings:
- **Active:** Feature is running and its health checks pass.
- **Degraded:** Feature is running with a known limitation or dependency failure.
- **Unavailable:** Feature is not running or cannot be verified.
- **Needs verification:** Evidence is incomplete; user should verify independently.
- **No known match:** No threat was found in the sources checked; this is not a safety guarantee.

## 8. Current known status (scaffold stage)

- TypeScript package build: previously passed in CI.
- DNS parser unit tests: previously passed in CI.
- Android Capacitor compilation: not yet passing; latest failure exposed Gradle 9.8.2 incompatibility with the selected Android Gradle Plugin. Gradle 8.13 has been pinned; this correction still needs a fresh CI result.
- Android VPN packet engine: not implemented.
- Signed threat feed: not implemented.
- Message Check: planned, not implemented.
- Production readiness: **not ready**.
