
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { StorageService } from '../_services/storage.service';
import { Router } from '@angular/router';

const AUTH_API = 'http://localhost:8080/api/auth/';
const LDAP_API = 'http://localhost:8080/ldap/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root',
})
export class AuthService {
permissions:any

  constructor(private http: HttpClient,private storageService: StorageService,private router: Router,
    ) {}

  login(email: string, password: string): Observable<any> {
    return this.http.post(
      AUTH_API + 'signin',
      {
        email,
        password,
      },
      httpOptions
    );
  }

  ldapUserLogin(username:string, password:string): Observable<any> {
    return this.http.post(
      LDAP_API + 'auth-ldap',
      {
        username,
        password,
      },
      httpOptions
    );
  }


  register(username: string, email: string, password: string): Observable<any> {
    return this.http.post(
      AUTH_API + 'signup',
      {
        username,
        email,
        password,
      },
      httpOptions
    );
  }

  logout(): Observable<any> {
    return this.http.post(AUTH_API + 'signout', { }, httpOptions);
  }

  isValidPermissions()
  {
    const user = this.storageService.getUser();
    this.permissions = user.permissins;
    if(this.permissions != undefined)
    {
      return true
    }
    return false;
  }


  isLogged()
  {
    return !!sessionStorage.getItem('auth-user');
  }
}