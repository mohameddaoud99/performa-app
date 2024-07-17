import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';

import { IconSetService } from '@coreui/icons-angular';
import { iconSubset } from './icons/icon-subset';
import { Title } from '@angular/platform-browser';
import { StorageService } from './_services/storage.service';
import { AuthService } from './_services/auth.service';
import {  Inject } from '@angular/core';
import { DOCUMENT } from '@angular/common';



@Component({
  selector: 'app-root',
  template: '<router-outlet></router-outlet>',
})
export class AppComponent implements OnInit {
  title = 'Telnet Holding';

  role ?:string;
  isLoggedIn = false;
  username?: string;

  constructor(
    private router: Router,
    private titleService: Title,
    private iconSetService: IconSetService,
    private storageService: StorageService,
    private authService: AuthService,
    @Inject(DOCUMENT) private document: Document
  ) {
    titleService.setTitle(this.title);
    //iconSet singleton
    iconSetService.icons = { ...iconSubset };
  }

  ngOnInit(): void {
    this.router.events.subscribe((evt) => {
      if (!(evt instanceof NavigationEnd)) {
        return;
      }
    });

    this.isLoggedIn = this.storageService.isLoggedIn();

    if (this.isLoggedIn) {
      const user = this.storageService.getUser();
      this.role = user.role;
      this.username = user.username;
    }
  }
}
