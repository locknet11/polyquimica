export interface AccountInfo {
  id: string;
  email: string;
  fullName: string;
  company: string;
  cuit: string;
  role: string;
  moduleAccess: ModuleAccess[];
}

export type ModuleAccess = 'PRODUCTS' | 'USERS' | 'CATEGORIES' | 'SETTINGS';
