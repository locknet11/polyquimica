import { Component, ElementRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LayoutService } from 'src/app/shared/services/layout.service';
import { MenuComponent } from '../menu/menu.component';

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [CommonModule, MenuComponent],
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss'],
})
export class SidebarComponent {
  constructor(
    public layoutService: LayoutService,
    public el: ElementRef
  ) {}
}
