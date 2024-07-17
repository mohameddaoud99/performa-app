import { Injectable } from '@angular/core';
import { CanActivate, CanDeactivate, Router } from '@angular/router';
import { AuthService } from './../_services/auth.service';
import { Location } from "@angular/common";

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private authService: AuthService, private route: Router,location: Location
    ) {
  }

  
  canActivate(): boolean {

    if (this.authService.isLogged() /*&& this.authService.isValidPermissions()*/ ) {
      return true;
    }
    this.route.navigate(['/login']);
    return false;
  }

}
