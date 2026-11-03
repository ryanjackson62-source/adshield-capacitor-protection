import { registerPlugin } from '@capacitor/core';
import type { AdShieldProtectionPlugin } from './definitions';

const AdShieldProtection = registerPlugin<AdShieldProtectionPlugin>(
  'AdShieldProtection',
  {
    web: () => import('./web').then((module) => new module.AdShieldProtectionWeb()),
  },
);

export * from './definitions';
export { AdShieldProtection };
