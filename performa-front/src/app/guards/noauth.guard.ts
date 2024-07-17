import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';

import { AuthService } from './../_services/auth.service';
@Injectable({
  providedIn: 'root'
})
export class NotauthGuard implements CanActivate {
  constructor(private authService: AuthService, private route: Router) {
  }
  canActivate():boolean {
    if (this.authService.isLogged()) {
      this.route.navigate(['/dashboard']);
      return false;
    }
    return true;
  }
  
}
