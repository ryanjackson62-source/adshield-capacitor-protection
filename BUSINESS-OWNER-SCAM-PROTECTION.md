# AdShield Business-Owner Scam Protection Taxonomy

Status: product and detection design reference. This document describes threat patterns to support future implementation; it does not claim that every pattern is currently detected or that any sender is fraudulent.

## Product objective

Protect small-business owners and their employees from fraud that targets business revenue, reputation, customer relationships, online visibility, payments, and access to essential business accounts.

AdShield should distinguish:
- **Impersonation and account takeover:** someone falsely claims to represent a trusted platform or tries to obtain credentials, one-time codes, or access.
- **Payment deception:** someone invents an obligation, renewal, listing fee, or account problem to obtain money.
- **Misleading lead generation:** unsolicited marketing or directory sales that may be aggressive or deceptive but are not necessarily criminal.
- **Legitimate business administration:** genuine renewals, subscriptions, marketing proposals, and documented supplier changes.

A pattern match is a warning signal, not a finding of fraud. The product must explain the exact text that triggered a warning and recommend independent verification.

## Threat families

### 1. Google Business Profile and local-search impersonation
Examples:
- Caller claims to represent “Google verification systems.”
- Caller says the business is not on “Google Voice verification.”
- Caller threatens listing suspension or removal unless a fee is paid.
- Caller asks for a Google one-time code or account access.

Safety guidance:
- Google Voice and Google Business Profile are separate services.
- Never disclose one-time codes or passwords to an unsolicited caller.
- Check listing status by opening the business profile through the owner's normal Google account.
- Do not claim that every paid listing-management or marketing service is fraudulent; distinguish legitimate third-party services from false Google affiliation and fabricated account threats.

### 2. Domain, website, hosting, and certificate renewal deception
Examples:
- Unexpected invoice says a domain will expire immediately unless paid through a supplied link.
- Sender uses confusingly similar registrar or hosting branding.
- Notice bundles unrelated “search engine submission,” directory, or certificate charges into a renewal.

Safety guidance:
- Verify registrar, domain, expiration date, and actual invoice through the account the owner normally uses.
- A real renewal notice is not inherently suspicious. Look for mismatched registrar, unexpected payment destination, urgency, and services never ordered.

### 3. Business directory and listing-fee solicitation
Examples:
- Caller claims an existing directory listing must be renewed or confirmed by card.
- Caller implies a business is legally required to pay for a listing.
- Invoice resembles a government or chamber notice but is a private directory solicitation.

Safety guidance:
- Ask for the contract, order record, exact service, and cancellation terms.
- Verify against business records before paying.
- Label generic unsolicited sales as possible misleading solicitation, not automatically criminal fraud.

### 4. SEO, GEO, AEO, and online-reputation lead bait
Examples:
- Unsolicited email says “I checked your digital search presence” but provides no specific findings.
- Sender asks recipient to reply “Send Details” or “Sure.”
- Sender implies special access to Google rankings or AI answer placement.

Safety guidance:
- Treat as unsolicited lead generation unless stronger evidence indicates fraud.
- Do not reply merely to discover the sender's offer.
- Independently verify provider identity, references, deliverables, access requirements, and contract terms.
- Never give a marketing vendor owner-level account access when scoped access is sufficient.

### 5. Payment processor and merchant-account impersonation
Examples:
- Fake Square, Stripe, PayPal, Clover, or “merchant services” call says card processing is suspended.
- Caller demands a fee or code to restore processing.
- Fake chargeback, PCI, compliance, or terminal replacement notices route the owner to a caller-controlled number or link.

Safety guidance:
- Open the processor's app or website directly.
- Verify account notices and chargebacks in the authenticated merchant dashboard.
- Never disclose one-time codes or install remote-access software for an unsolicited caller.

### 6. Invoice, vendor, and payment-instruction compromise
Examples:
- Supplier emails new bank details.
- Fake invoice changes remittance instructions.
- Impersonated owner or manager asks employee to wire money or buy gift cards.

Safety guidance:
- Confirm changes through a previously verified phone number and a second approver.
- Do not use contact details in the change request to verify that same request.

### 7. Fake compliance, tax, licensing, and regulatory notices
Examples:
- Unsolicited caller claims an immediate government fee is due to keep a business license, tax account, or permit active.
- Private vendor presents an optional service as a mandatory government requirement.
- Threats of arrest, closure, or penalties are tied to gift cards, cryptocurrency, or an unusual payment channel.

Safety guidance:
- Identify the actual issuing agency and verify directly using a known official government source.
- Distinguish legitimate filing fees and documented regulatory obligations from fabricated urgency or false affiliation.

### 8. Website, advertising, and customer-lead fraud
Examples:
- Fake advertising credit or refund offer requests account credentials.
- Fake review-removal service promises guaranteed removal for payment.
- Lead seller claims exclusive customer leads but provides recycled, fabricated, or unverifiable prospects.

Safety guidance:
- Verify contract, lead ownership/exclusivity, refund terms, and account permissions.
- Do not promise AdShield can determine whether a review or lead is authentic from text alone.

## Detection design

Each family should have:
- A specific trigger pattern and a clear evidence excerpt.
- A confidence level appropriate to the evidence.
- Legitimate near-neighbor examples to test false positives.
- Defensive-advice examples to ensure warnings do not trigger on safety education.
- Explicit limits: text matching cannot authenticate a caller, sender, domain owner, business relationship, or payment obligation.

Avoid broad keyword rules such as flagging every mention of Google, SEO, a domain renewal, or a payment processor. Require meaningful combinations of context and action (for example, claimed suspension plus an unexpected payment demand).

## Recommended user experience

Create a **Business Owner Protection** category with short checklists for:
- Verify the caller or sender independently.
- Protect one-time codes and account recovery.
- Verify payment destination changes.
- Check real renewal dates and contracts.
- Preserve evidence and report suspected impersonation.

When users share a suspicious message, do not ask them to reply, call the sender, open supplied links, pay, or disclose private account data. Encourage redaction of customer, employee, bank, tax, and login information before submitting examples.

## Privacy and data handling

- Prefer local text analysis where feasible.
- Do not collect full URLs, query tokens, passwords, verification codes, bank details, or customer information in threat reports.
- Store only the minimum redacted pattern evidence needed for review.
- Never use customer or employee data as test fixtures.
- Convert real-world examples into synthetic, non-identifying regression cases.

## Release gates

Before marketing any family as supported:
1. Implement the rule or workflow.
2. Add suspicious and legitimate regression cases.
3. Run tests and type checks.
4. Review false-positive behavior and warning language.
5. State whether the capability analyzes pasted/shared text, URLs, or actual live calls. Do not imply call monitoring where none exists.

Current app work includes deterministic text patterns for some Google verification, payment-demand, and unsolicited SEO-audit language. These are early pattern checks, not caller authentication or a validated real-world detection benchmark. Domain-renewal, directory-fee, and payment-processor rules have been drafted in the app code but still require regression tests and validation.
