import { Routes } from '@angular/router';

export const CategoriesRoutes: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./feature/categories.component').then(x => x.CategoriesComponent),
  },
];
