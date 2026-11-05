# Business-Owner Protection Regression Test Plan

Status: test design only. These cases are proposed fixtures for the AdShield consumer-text analyzer. They are not evidence of detection performance until implemented and run.

## General assertions

- A positive match produces a specific warning with a quoted evidence span.
- A warning never states that a sender is confirmed fraudulent solely because of a phrase match.
- A legitimate comparison and defensive-advice comparison must not trigger the same rule.
- Mentioning a brand or service alone must not trigger a warning.
- If the input is a phone-call summary or transcript, explain that text analysis cannot authenticate the caller, caller ID, voice, or background audio.
- Keep personal, customer, employee, payment, and account details out of fixtures.

## Proposed regression cases

### A. Fake domain renewal

| Case | Input | Expected |
|---|---|---|
| Positive | “Your domain name will expire tomorrow. Pay the renewal invoice now to avoid suspension.” | Flag unexpected domain renewal payment demand |
| Negative | “Sign in to your domain registrar to review your domain renewal date and automatic renewal settings.” | No domain-renewal warning |
| Negative | “Our hosting provider sent the annual renewal invoice for the service we already use.” | No warning absent suspicious pressure or mismatch |
| Defensive | “Do not pay unexpected domain renewal invoices. Check your registrar directly.” | No warning |

### B. Business directory listing fee

| Case | Input | Expected |
|---|---|---|
| Positive | “Pay the business directory listing renewal fee today to prevent removal.” | Flag directory fee pressure |
| Positive | “This is the official business directory. Your listing must be confirmed by credit card to avoid deletion.” | Flag only if the analyzer recognizes the combined directory, payment, and removal claim |
| Negative | “Our business directory lists local contractors and their service areas.” | No warning |
| Negative | “We offer optional paid advertising in our local contractor directory. Here is the written rate card.” | No warning |
| Defensive | “Verify any directory listing invoice against your signed agreement before paying.” | No warning |

### C. Payment processor account threat

| Case | Input | Expected |
|---|---|---|
| Positive | “Your Square merchant account is suspended. Pay a fee now to restore processing.” | Flag processor account threat |
| Positive | “We are calling from merchant services. Read us the verification code to prevent your card terminal from being disabled.” | Flag code disclosure / processor impersonation |
| Negative | “Open the Square app directly to review your merchant account settings.” | No warning |
| Negative | “Stripe sent a normal monthly payout summary to the business account.” | No warning |
| Defensive | “Never share a payment processor one-time code with an unsolicited caller.” | No warning |

## Coverage expansion to consider later

- Fake PCI compliance invoices and “mandatory certification” sales.
- Fake card-terminal replacement and shipping fees.
- Fake advertising credit, refund, or account suspension messages.
- Fake review-removal guarantees and reputation-management extortion.
- Fake exclusive customer-lead offers and lead resale.
- Business licensing, tax, and permit impersonation.
- Supplier invoice substitution and bank-detail changes.
- Fake Google review or Business Profile support messages.

## Manual review checklist

For every new rule, reviewers should ask:
1. Does the rule require a meaningful combination of context and requested action?
2. Could a legitimate provider, existing contract, or normal billing notice match?
3. Does the explanation say exactly what text triggered the warning?
4. Does the recommended action use a trusted, independently located channel?
5. Does the rule avoid treating a generic sales pitch as confirmed criminal fraud?
6. Does it avoid claiming to authenticate a live call or sender?
7. Are all test fixtures synthetic and free of personal or business-sensitive data?

## Release gate

Do not mark a rule complete until its implementation, positive fixtures, negative fixtures, defensive fixtures, full test suite, and type checks all pass. Record the exact build and test result. Keep all claims proportional to the evidence.
