import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ButtonModule } from 'primeng/button';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-page-not-found',
  standalone: true,
  imports: [CommonModule, ButtonModule, RouterModule],
  templateUrl: './page-not-found.component.html',
})
export class PageNotFoundComponent {}
