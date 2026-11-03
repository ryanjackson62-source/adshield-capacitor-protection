# AdShield Capacitor Protection

**Status: native bridge scaffold and first DNS parser milestone — not a functioning VPN and not for production use.**

This repository contains the first-party Capacitor 8 plugin boundary for AdShield. The current native bridge can request Android's VPN consent dialog, but it deliberately reports `engineAvailable: false` and rejects `start()`. It does not establish a TUN interface or route device traffic.

## API

- `getStatus()`
- `requestPermission()`
- `start()`
- `stop()`
- `protectionEvent` listener

## Implemented in this branch

- Capacitor 8 TypeScript API and web fallback.
- Android plugin bridge and explicit VPN consent request.
- Fail-closed behavior: start is disabled until the complete traffic engine is implemented.
- A bounds-checked DNS question parser with compression-pointer loop and bounds defenses (in progress; unit tests included).

## Not implemented yet

- IPv4/IPv6 packet parsing and TUN read/write loop.
- UDP DNS response generation and forwarding.
- DoH upstream using a socket protected with `VpnService.protect()`.
- Signed blocklist verification, expiry and rollback protection.
- Foreground VPN service, persistent notification and lifecycle recovery.
- Physical-device tests and Google Play policy review.

## Security gates before enabling the engine

1. Fuzz and malformed-packet tests for all packet parsers.
2. IPv4/IPv6 and UDP handling with strict packet bounds.
3. Signed blocklist verification, expiry and monotonic version / rollback defense.
4. DoH forwarding over TLS using a socket protected from the VPN route.
5. Foreground service lifecycle, persistent notification, user stop, revoke and network-change handling.
6. Physical-device testing for Wi-Fi/cellular, IPv4/IPv6, captive portals, competing VPNs, battery, OEM behavior and recovery.
7. Google Play VpnService disclosure and policy review.

DNS filtering cannot inspect HTTPS paths, page content, ad creative or messages. App-specific encrypted DNS, hard-coded IPs, another VPN, proxies and bypass routes can evade domain filtering.

## Development

Build the TypeScript surface with `npm run build` in an environment with the declared development dependencies installed. Android integration and device validation require the Android SDK / Gradle toolchain.
