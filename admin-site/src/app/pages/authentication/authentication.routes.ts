import { Routes } from '@angular/router';

export const AuthenticationRoutes: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./ui/authentication-layout.component').then(
        (x) => x.AuthenticationLayoutComponent,
      ),
    children: [
      {
        path: '',
        loadComponent: () =>
          import('./feature/authentication.component').then(
            (x) => x.AuthenticationComponent,
          ),
      },
      // 2fa path
    ],
  },
];
