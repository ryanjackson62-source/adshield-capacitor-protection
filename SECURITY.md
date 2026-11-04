# AdShield Security-First Engineering Standard

**Status: required design and release policy. This document is not evidence that controls are implemented or independently audited.**

AdShield must assume that attackers know its architecture, inspect its public code and protocols, and actively attempt to bypass, poison, impersonate, or compromise it. Security controls must be verified before a feature is described as protective.

## 1. Threat model and assets

Protect:
- Device traffic handled by the VPN, VPN configuration, and service lifecycle.
- User privacy, local settings, opt-in telemetry, and any account data.
- Threat-feed signing keys, release-signing keys, build credentials, and backend credentials.
- Blocklist integrity, version history, and community-report moderation.
- App updates, plugin packages, APIs, domains, and public brand identity.

Assume attackers may:
- Submit malicious or misleading reports at scale.
- Poison feeds, replay old feeds, or serve modified feeds.
- Exploit malformed DNS/IP/UDP packets and parser edge cases.
- Spoof upstream responses, certificates, API endpoints, or update artifacts.
- Reverse engineer the app, automate API abuse, and search public repositories for secrets.
- Attempt denial of service, resource exhaustion, account takeover, and supply-chain compromise.
- Use rooted/modified devices or network configurations that bypass DNS filtering.

## 2. Secure design principles

- Least privilege: request only permissions and data required for the feature.
- Fail closed for integrity failures: invalid signatures, expired feeds, rollback, malformed packets, or unverifiable updates must never silently become trusted.
- Honest degraded states: show unavailable/degraded when protection cannot be verified; never imply full protection.
- Minimize data: no default browsing history, message collection, or full URL query-token retention.
- Separate trust domains: public reports are untrusted input; backend moderation cannot directly publish trusted block entries.
- Defense in depth: validation at client, API, storage, feed-signing, and release layers.
- No security-through-obscurity: assume protocol and source code are public.

## 3. Threat intelligence and report integrity

- Community reports enter a quarantined pending state and cannot directly block domains.
- Require corroboration and moderation rules before promotion.
- Rate-limit submissions; apply abuse controls and deduplication.
- Store normalized host/domain evidence only when possible; strip URL paths, query strings, fragments, and credentials.
- Distinguish confirmed threat, suspicious, pending report, no known match, and provider unavailable.
- Sign published threat lists with an offline-controlled private signing key.
- Keep signing private keys out of app binaries, source control, client devices, and ordinary application servers.
- Verify signatures against pinned public keys; support planned key rotation and revocation.
- Enforce monotonic list versions, issued-at/expiry windows, schema validation, and rollback rejection.
- Define an emergency revocation process and audit every feed publication.

## 4. Android VPN and packet engine

- Use Android VpnService only after explicit user consent and provide a persistent notification and obvious stop control.
- Parse every IP, IPv6 extension, UDP, and DNS field with strict bounds and length checks before access.
- Reject malformed, ambiguous, unsupported, oversized, fragmented, or resource-exhausting inputs safely.
- Avoid unbounded allocations, recursion, blocking work on critical threads, and attacker-controlled log output.
- Fuzz parsers with generated and mutated packets; add regression tests for every crash or bypass.
- Use protected sockets for DoH to avoid routing upstream requests back into the VPN.
- Validate TLS certificates and hostnames; never disable certificate validation.
- Do not intercept HTTPS, install a root certificate, or read private messages.
- Handle permission revocation, process death, network changes, captive portals, IPv4/IPv6, and competing VPNs explicitly.
- Never report active protection unless the TUN interface and packet-processing loop are healthy.

## 5. Backend and API security

Before production:
- Authenticate privileged operations and enforce authorization server-side on every request.
- Apply per-IP, per-account, and per-device rate limits appropriate to each endpoint.
- Validate request schemas, sizes, encodings, and content types; reject unexpected fields.
- Use parameterized database operations and tenant-scoped access controls.
- Protect against IDOR, injection, SSRF, replay, CSRF where applicable, and mass assignment.
- Keep secrets in managed server-side secret storage; never embed API keys or private keys in app code.
- Restrict CORS to required origins; use secure transport and security headers.
- Use short-lived scoped tokens, secure session handling, and revocation for account features.
- Record security-relevant events without logging passwords, tokens, message contents, or full sensitive URLs.
- Back up critical data securely and test restoration and incident recovery.

## 6. Build and software supply chain

- Protect GitHub account access with MFA/passkeys and least-privilege repository permissions.
- Protect main branch with required checks and review before merge.
- Require reproducible, reviewed dependency changes; pin or constrain versions and monitor advisories.
- Run secret scanning, dependency scanning, static analysis, and license checks in CI.
- Generate an SBOM for release artifacts and retain build provenance.
- Build release artifacts in controlled CI; sign Android releases and protect signing keys outside source control.
- Never publish directly from an unreviewed development branch.
- Treat third-party SDKs, npm packages, actions, and build plugins as part of the attack surface.

## 7. Testing and independent review

Required before enabling device-wide protection:
- Unit, integration, property-based, fuzz, malformed-packet, and regression tests.
- DNS compression, label encoding, transaction correlation, response validation, truncation, and EDNS0 cases.
- IPv4/IPv6, fragments, extension headers, UDP length/checksum handling, MTU, and resource exhaustion tests.
- Feed signature, key rotation, expiry, rollback, corruption, and revocation tests.
- API authorization, abuse, tenant-isolation, and rate-limit tests.
- Physical-device tests across Android versions, Wi-Fi/cellular transitions, battery, captive portals, and VPN conflicts.
- Independent security review / penetration test before public release; remediate critical and high findings before launch.

## 8. Incident response

Maintain a documented process to:
- Disable or revoke a compromised feed key or release.
- Publish corrected signed feeds and force clients to reject revoked versions.
- Notify affected users and relevant platform/security contacts when warranted.
- Preserve minimal forensic evidence without collecting unnecessary private data.
- Rotate credentials, contain compromise, restore from known-good builds, and publish a post-incident report.

## 9. Release gates

No feature may be labeled protected or production-ready until its implementation, tests, failure modes, and user-facing status have been reviewed. Critical/high vulnerabilities block release. A passing CI build is necessary but is not a security audit.

## Current project status

The current branch contains an early Capacitor bridge scaffold and a DNS question parser with a small initial test set. The VPN engine is not implemented, CI has been added but has not yet reported a completed run, and no independent security audit has occurred. The current app must not be represented as providing device-wide protection.
