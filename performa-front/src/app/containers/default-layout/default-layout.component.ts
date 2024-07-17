import { Component } from '@angular/core';

import { navItems } from './_nav';
import { navItemsLdap } from './nav_ldap';
import { StorageService } from './../../_services/storage.service';
import { AuthService } from './../../_services/auth.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './default-layout.component.html',
  styleUrls: ['./default-layout.component.scss'],
})
export class DefaultLayoutComponent {
  role: any

  isLoggedIn = false;
  showAdminBoard = false;
  showModeratorBoard = false;
  username?: string;

  user:any
  public nav: any



  constructor(private storageService: StorageService,
  ) { }

  ngOnInit(): void {
    this.isLoggedIn = this.storageService.isLoggedIn();
    this.user = this.storageService.getUser();
    this.CostumiseNav();
  }

  CostumiseNav()
  {
    if (this.user.data=="userLdapDetails"){
      this.nav=navItemsLdap;
    }
    else
    {
      this.nav = navItems.filter((n) => n.permission?.some(ai => this.user.permissions.includes(ai)));

    }
  }

  
}
