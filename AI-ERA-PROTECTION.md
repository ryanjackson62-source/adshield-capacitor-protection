# AdShield AI-Era Scam Protection Scope

**Status: product and security requirements. This is a planned capability set, not implemented detection.**

AI changes the scale, speed, personalization, and realism of fraud. AdShield should address AI-enabled abuse without pretending it can reliably identify every AI-generated image, voice, video, or message.

## 1. Threats to address

### AI-assisted social engineering
- Highly personalized phishing messages and emails with polished grammar.
- Impersonation of family members, employers, banks, government agencies, vendors, and customer support.
- Automated romance, investment, fake job, task, delivery, and recovery scams.
- Scripts that adapt to a victim's replies and build urgency or trust over multiple messages.

### Voice and video impersonation
- Voice cloning used for emergency, family, executive, and payment requests.
- Deepfake video calls and fabricated identity evidence.
- Synthetic voicemail and audio instructions that pressure a person to transfer money, disclose credentials, or bypass normal approval.

### AI-powered malicious infrastructure
- Rapidly generated phishing pages, fake shops, cloned support sites, and lookalike domains.
- AI-generated advertisements and landing pages that impersonate trusted brands.
- Automated scam chatbots and fake technical-support agents.
- QR-code and shortened-link lures that conceal the destination.

### Attacks against AI systems used by AdShield
- Prompt injection embedded in user-submitted messages, webpages, screenshots, or threat reports.
- Attempts to make an AI classifier ignore policy, reveal hidden instructions, or trigger tools.
- Poisoned training/evaluation data and adversarial examples.
- Model/API abuse, cost exhaustion, sensitive-data leakage, and unsafe tool execution.

## 2. User-facing AI-era protection

### Message Check
A user may deliberately paste or share a suspicious message for analysis. The feature should:
- Explain concrete warning signs, not merely return a binary verdict.
- Identify pressure, secrecy, unusual payment methods, credential/OTP requests, impersonation, off-platform moves, fake jobs, romance manipulation, investment guarantees, recovery-fee scams, and money-mule requests.
- Recognize that polished language, familiar writing style, caller ID, a known profile photo, or a convincing voice are not proof of identity.
- Recommend independent verification through a known phone number, official app, saved contact, or trusted in-person channel.
- Never instruct the user to call a number or click a link contained in the suspicious message.
- Minimize retention and never read messages in the background.

### Voice and video scam guidance
- Provide a clear warning that voice/video can be cloned or fabricated.
- Recommend a family or workplace safe-word / callback procedure for urgent requests.
- Advise users to independently call a known number before sending money or sharing secrets.
- Explain that AdShield cannot certify a voice, face, or video as genuine based on appearance alone.

### Link and domain intelligence
- Combine domain reputation, impersonation signals, URL structure, certificate/domain context where appropriate, and confirmed threat intelligence.
- Treat newly registered, lookalike, redirecting, or AI-generated content as signals for review, not automatic proof of fraud.
- Do not claim DNS filtering can inspect page content, image/video authenticity, private messages, or AI generation provenance.

## 3. Safe AI implementation requirements

If an AI model is introduced:
- Treat all user-provided content and retrieved webpages as untrusted data, never as system instructions.
- Keep model tools disabled for classification unless there is a separately reviewed, narrowly scoped use case.
- Never let model output directly publish threat-feed entries, block domains, alter entitlements, or perform account/security actions.
- Require deterministic validation and server-side authorization for every consequential action.
- Use structured outputs with strict schemas, size limits, and explicit handling for refusal, timeout, and malformed responses.
- Defend against prompt injection, data exfiltration, cross-user context leakage, and denial-of-wallet attacks.
- Send only the minimum content necessary; disclose when content leaves the device and obtain consent.
- Define retention and deletion behavior before launch; default to no message-content retention.
- Evaluate false positives and false negatives across languages, dialects, legitimate urgent communications, accessibility contexts, and different scam patterns.
- Provide evidence and uncertainty; never present AI-generated analysis as proof.
- Maintain model/version tracking, evaluation datasets with privacy safeguards, abuse monitoring, and rollback capability.

## 4. Detection language and confidence

Use calibrated labels:
- **Confirmed malicious:** supported by verified threat intelligence or strong corroborated evidence.
- **High-risk indicators:** multiple concrete warning signs; user should stop and independently verify.
- **Needs verification:** incomplete or conflicting signals.
- **No known match:** no known threat found; not a safety guarantee.
- **Unable to check:** provider unavailable, unsupported content, or insufficient evidence.

Do not use an “AI-generated” label as a synonym for “scam.” AI-generated content can be legitimate; human-created content can be fraudulent.

## 5. Release gates

Before public release of AI-assisted analysis:
- Threat model and privacy review.
- Prompt-injection and adversarial test suite.
- Red-team tests for leakage, tool misuse, unsafe recommendations, and cross-user isolation.
- Calibration and false-positive/false-negative evaluation.
- Clear user disclosure of model use, data transmission, limitations, and retention.
- Human-reviewed incident and escalation process.
- No claims of deepfake detection unless a specific modality detector has been implemented, independently evaluated, and its limitations disclosed.

## 6. Initial product priority

1. Ship user-controlled Message Check with transparent evidence and privacy-preserving handling.
2. Add explicit AI-impersonation and voice-clone scam education.
3. Improve URL/domain and fake-brand detection using verified intelligence.
4. Evaluate optional image/audio/video analysis only as a separate, consent-based capability with independent testing.

**Core promise:** Help people recognize manipulation and verify identity safely. Do not promise that AdShield can reliably tell whether every message, voice, image, or video was made by AI.
