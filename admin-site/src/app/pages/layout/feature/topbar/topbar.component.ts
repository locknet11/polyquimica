import { Component, ElementRef, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MenuItem } from 'primeng/api';
import { LayoutService } from 'src/app/shared/services/layout.service';
import { Router, RouterModule } from '@angular/router';
import { MenuModule } from 'primeng/menu';
import { ButtonModule } from 'primeng/button';
import { AuthenticationService } from 'src/app/pages/authentication/data-access/authentication.service';

@Component({
  selector: 'app-topbar',
  standalone: true,
  imports: [CommonModule, RouterModule, MenuModule, ButtonModule],
  providers: [AuthenticationService],
  templateUrl: './topbar.component.html',
  styleUrls: ['./topbar.component.scss'],
})
export class TopbarComponent {
  items: MenuItem[] = [
    {
      label: $localize`Logout`,
      icon: 'pi pi-sign-out',
      command: () => this.handleLogout(),
    },
  ];

  @ViewChild('menubutton') menuButton!: ElementRef;

  @ViewChild('topbarmenubutton') topbarMenuButton!: ElementRef;

  @ViewChild('topbarmenu') menu!: ElementRef;

  handleLogout() {
    this.authService.logout();
    this.router.navigate(['login']);
  }

  constructor(
    public layoutService: LayoutService,
    private authService: AuthenticationService,
    private router: Router
  ) {}
}
