export type ProtectionState =
  | 'unavailable'
  | 'stopped'
  | 'permission_required'
  | 'starting'
  | 'active'
  | 'degraded'
  | 'error';

export interface ProtectionStatus {
  platform: 'android';
  engineAvailable: boolean;
  state: ProtectionState;
  lastListVersion?: string;
  lastListUpdatedAt?: string;
  blockedCount: number;
  upstream: string;
}

export interface PermissionResult {
  granted: boolean;
}

export interface StartResult {
  state: ProtectionState;
}

export interface StopResult {
  state: 'stopped';
}

export interface ProtectionEvent {
  type: 'statusChanged' | 'threatBlocked' | 'engineError' | 'blocklistUpdated';
  state?: ProtectionState;
  domain?: string;
  category?: string;
  message?: string;
  listVersion?: string;
  occurredAt: string;
}

export interface AdShieldProtectionPlugin {
  getStatus(): Promise<ProtectionStatus>;
  requestPermission(): Promise<PermissionResult>;
  start(): Promise<StartResult>;
  stop(): Promise<StopResult>;
  addListener(
    eventName: 'protectionEvent',
    listenerFunc: (event: ProtectionEvent) => void,
  ): Promise<import('@capacitor/core').PluginListenerHandle>;
}
