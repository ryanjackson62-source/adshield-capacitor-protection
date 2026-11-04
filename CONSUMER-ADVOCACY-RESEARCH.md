# Consumer Advocacy Research: Lindsay Owens and Groundwork Collaborative

**Status:** Research input and product-design guidance; not an implemented protection capability.  
**Added:** 2026-11-04  
**Working branch:** `native-vpn-engine`

## Purpose

Use consumer-protection research associated with Dr. Lindsay Owens and Groundwork Collaborative to broaden AdShield's threat model beyond malicious links. The product may eventually help people recognize deceptive or exploitative digital practices, but it must distinguish consumer-risk education from technical threat detection.

This research is not an endorsement, partnership, or claim that Owens or Groundwork Collaborative supports AdShield.

## Source profile

Dr. Lindsay Owens is President and CEO of Groundwork Collaborative. Groundwork describes its work as research and advocacy concerning consumer protection, market structures, corporate power, and household costs. Owens is the author of *Gouged: The End of a Fair Price—and What That Means for Your Wallet*.

Primary profile: https://groundworkcollaborative.org/person/lindsay-owens/

## Podcast reviewed

**This Past Weekend w/ Theo Von, episode #693 — “Consumer Advocate Lindsay Owens”**  
Published November 3, 2026; approximately 113–114 minutes.

Episode listing: https://www.iheart.com/podcast/269-this-past-weekend-w-theo-v-29429907/  
Transcript listing: https://www.tapesearch.com/episode/693-consumer-advocate-lindsay-owens/KfjdFFU9P9PTGEVK32dHjL

The publicly visible transcript page identifies the episode and subject, but its transcript is truncated for non-subscribers. Treat it as a useful listening reference, not a complete verified transcript. Do not attribute detailed claims to a specific podcast timestamp unless checked against the audio or a complete transcript.

## Research themes relevant to AdShield

### 1. Personal-data exploitation and surveillance pricing

Groundwork defines surveillance/personalized pricing as using personal data to set consumer-specific prices. Potentially relevant data can include location, browsing behavior, purchase history, and inferred willingness to pay.

**AdShield relevance**
- Create a consumer-education category for personalized pricing and data-driven offers.
- Explain the distinction between ordinary dynamic pricing (for example, time- or demand-based pricing) and individualized pricing based on personal data.
- Offer practical privacy guidance: review app permissions and loyalty-program settings; compare the same item, seller, quantity, and time across independent sessions or providers; save dated evidence.
- Do not infer that a price difference proves surveillance pricing. Differences may result from taxes, location, inventory, delivery fees, promotions, account status, timing, or experiments.

### 2. Consumer profiling and behavioral data

The October 20, 2026 Capitol Forum interview discusses consumer profiles and scoring systems described by Owens, including examples she attributes to company patents, reporting, or public disclosures. It also discusses data generated through shopping apps and AI shopping assistants.

**AdShield relevance**
- Educate users that searches, clicks, app interactions, loyalty-program activity, and chatbot conversations may be collected or used to personalize experiences.
- Provide a privacy checklist and links to official privacy controls.
- Require primary evidence before naming a company or asserting that a particular score or system is in use.
- Clearly label whether a claim comes from a patent, a company's own statement, a regulator, investigative reporting, or an independent test. A patent alone does not prove deployment.

### 3. AI shopping assistants and conflicts of interest

Groundwork's July 2026 commentary raises questions about whether AI shopping assistants act in the consumer's interest or optimize for retailer/platform commercial objectives. This is a meaningful emerging risk area, but a conflict or incentive is not by itself proof that a specific recommendation is deceptive.

**AdShield relevance**
- Later offer a user-initiated “Check this recommendation” workflow.
- Ask users to compare the recommendation against stated needs, total price, fees, sponsored placement, affiliate relationships, alternatives, and source evidence.
- Distinguish disclosed advertising, affiliate incentives, unsupported claims, and demonstrable deception.
- Never claim to know an AI system's hidden objective or internal ranking logic without evidence.

### 4. Hidden fees, dark patterns, and deceptive purchase flows

Groundwork's Shopper's Bill of Rights and related work address hidden fees, pricing transparency, and high-tech consumer practices.

**AdShield relevance**
- Consider a user-initiated webpage or checkout review as a future feature.
- Identify observable patterns such as preselected add-ons, unclear recurring billing, difficult cancellation, misleading urgency, drip pricing, and obscured total cost.
- Explain the specific interface evidence; do not label a business a scam solely because its design is aggressive or inconvenient.
- Do not implement webpage inspection or browser interception until a privacy-preserving, permission-based design and reliable detection method exist.

### 5. Investigative research and evidence standards

Groundwork's collaboration with Consumer Reports and More Perfect Union on Instacart pricing demonstrates a useful investigative pattern: controlled comparisons, documented methodology, independent analysis, public reporting, and a company response.

The Consumer Reports investigation reported that some identical grocery items were shown to different shoppers at different prices, with variations as high as 23%. Instacart later announced it was ending the technology that enabled those experiments. Preserve the scope and methodology when citing these results; do not generalize one investigation to every retailer or every transaction.

**AdShield relevance**
- Design community reports as leads, not verified findings.
- Require reproducible evidence for public claims: date/time, product and seller, region, account/session conditions, comparable item/quantity, displayed price, fees, and screenshots with personal data redacted.
- Use independent corroboration and a human review process before promoting a report to a confirmed finding.
- Provide a correction and appeal process for affected businesses or individuals.
- Never expose a reporter's identity or private account data by default.

## Product boundaries and status language

These consumer-protection topics must remain separate from AdShield's technical security verdicts.

Recommended categories:
- **Confirmed malicious link/domain:** supported by a trusted threat-intelligence source or validated security evidence.
- **Suspicious link indicators:** specific technical signals warrant caution; not a confirmed threat.
- **Consumer-practice concern:** observable or sourced evidence of a potentially deceptive or exploitative practice; not necessarily malware or fraud.
- **Reported, not verified:** user/community submission awaiting review.
- **No known match:** no matching threat found in the sources checked; not a safety guarantee.
- **Unable to check:** provider unavailable, insufficient evidence, or unsupported content.

Never convert a consumer-practice concern into a malicious-domain block without independent technical evidence. Never block a company domain simply because it has been criticized for pricing, privacy, or business practices.

## Privacy and safety requirements

- No background reading of private messages.
- No default storage of full URLs, query tokens, shopping histories, or chatbot conversations.
- Any comparison or evidence submission must be user-initiated and clearly consented to.
- Redact names, addresses, order IDs, loyalty numbers, payment data, and other personal identifiers before submission.
- Do not send private shopping or chatbot content to an AI provider by default.
- Treat webpages, user-submitted text, screenshots, and AI outputs as untrusted data, never as instructions.
- Do not claim to detect all surveillance pricing, dark patterns, or AI manipulation.
- Provide source, date, methodology, confidence, and limitations for every material claim.
- Do not create a public company “scam score” or ranking from unverified complaints.

## Prioritization

1. **Near-term:** Add consumer-education content and source references; preserve the current privacy-first manual scan and report model.
2. **Next:** Design a user-initiated “Message / Offer Check” that explains pressure tactics, impersonation, hidden fees, suspicious payment requests, and misleading claims. Keep it separate from definitive fraud determinations.
3. **Later:** Explore a privacy-preserving price-comparison/evidence workflow and a user-initiated checkout transparency review.
4. **Not yet:** Automated surveillance-pricing detection, browser-wide inspection, or company-level risk ratings. These require robust evidence, legal review, reliable data, and clear false-positive controls.

## Source register

1. Groundwork Collaborative — Lindsay Owens profile: https://groundworkcollaborative.org/person/lindsay-owens/
2. Theo Von, *This Past Weekend* #693, “Consumer Advocate Lindsay Owens,” November 3, 2026: https://www.iheart.com/podcast/269-this-past-weekend-w-theo-v-29429907/
3. Tapesearch episode/transcript listing (transcript access is truncated on the public page): https://www.tapesearch.com/episode/693-consumer-advocate-lindsay-owens/KfjdFFU9P9PTGEVK32dHjL
4. Groundwork Collaborative — “Your Data, Their Profit: The Consumer Cost of AI Surveillance Pricing,” testimony dated August 4, 2026: https://groundworkcollaborative.org/work/your-data-their-profit-the-consumer-cost-of-ai-surveillance-pricing/
5. The Capitol Forum — “Second Request Transcript: Surveillance Pricing and the ‘Shoppers’ Bill of Rights’ with Lindsay Owens,” October 20, 2026: https://thecapitolforum.com/resource/second-request-transcript-surveillance-pricing-and-the-shoppers-bill-of-rights-with-lindsay-owens/
6. Groundwork Collaborative — FTC personalized pricing inquiry statement, October 28, 2026: https://groundworkcollaborative.org/news/statement-from-groundworks-lindsay-owens-on-ftcs-personalized-pricing-inquiry/
7. Groundwork Collaborative — AI agents and consumer interests, July 23, 2026: https://groundworkcollaborative.org/news/new-op-ed-lindsay-owens-asks-how-do-we-stop-ai-agents-from-becoming-the-most-sophisticated-snake-oil-salesmen-yet/
8. Consumer Reports — Instacart AI pricing investigation, December 9, 2025 (updated December 22, 2025): https://www.consumerreports.org/money/questionable-business-practices/instacart-ai-pricing-experiment-inflating-grocery-bills-a1142182490/
9. Consumer Reports — Instacart ends AI-driven pricing practice, December 22, 2025: https://www.consumerreports.org/media-room/press-releases/2025/12/instacart-ends-ai-driven-pricing-practice-following-a-joint-consumer-reports-investigation-with-groundwork-collaborative-and-more-perfect-union/

## Review note

Groundwork is an advocacy organization with an explicit policy perspective. Its research is useful and should be evaluated on its methods and underlying evidence. For contested company-specific claims, seek the underlying documents, independent reporting, company response, and regulator findings before presenting a conclusion. This document is product research, not legal advice and not a statement of AdShield's current capabilities.
