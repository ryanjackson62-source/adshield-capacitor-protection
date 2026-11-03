import { WebPlugin } from '@capacitor/core';
import type {
  AdShieldProtectionPlugin,
  PermissionResult,
  ProtectionStatus,
  StartResult,
  StopResult,
} from './definitions';

export class AdShieldProtectionWeb extends WebPlugin implements AdShieldProtectionPlugin {
  async getStatus(): Promise<ProtectionStatus> {
    return {
      platform: 'android',
      engineAvailable: false,
      state: 'unavailable',
      blockedCount: 0,
      upstream: 'not_connected',
    };
  }

  async requestPermission(): Promise<PermissionResult> {
    return { granted: false };
  }

  async start(): Promise<StartResult> {
    return { state: 'unavailable' };
  }

  async stop(): Promise<StopResult> {
    return { state: 'stopped' };
  }
}
