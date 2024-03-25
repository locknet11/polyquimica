import { Routes } from '@angular/router';

export const ProductRoutes: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./feature/products.component').then(x => x.ProductsComponent),
  },
];
