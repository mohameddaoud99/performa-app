import { Component, OnInit, NgZone  } from '@angular/core';
import { AuthService } from '../../_services/auth.service';
import { StorageService } from '../../_services/storage.service';
import { Router } from '@angular/router';
import {ToastrService } from 'ngx-toastr';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  form: any = {
    email: null,
    password: null
  };
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  isExternalUser = false;

  visible = true;
  dismissible = true;
  showLoginAlert = true;
  toastMessage:any
  public toastVisibility =false

  constructor(private authService: AuthService,
              private toastrService: ToastrService,
              private storageService: StorageService,
              private router: Router,
              private zone: NgZone) {
              this.dismissLoginAlert();               
               }

  ngOnInit(): void {
    if (this.storageService.isLoggedIn()) {
      this.isLoggedIn = true;
      this.roles = this.storageService.getUser().roles;
    }
  }


  dismissLoginAlert() {
    setTimeout(() => {
      this.zone.run(() => {
        this.showLoginAlert = false;
      });
    }, 2000);
  }

  onVisibleChange(eventValue: boolean) {
    this.visible = eventValue;
  }

 
  checkValue(event: any){
    this.isExternalUser= !  this.isExternalUser
 }

  onSubmit(): void {
    const { email, password } = this.form;

    if (this.isExternalUser) {
      this.authService.login(email, password).subscribe({
        next: data => {
          this.storageService.saveUser(data);
  
          this.isLoginFailed = false;
          this.isLoggedIn = true;
          this.roles = this.storageService.getUser().roles;
          this.router.navigate(['/dashboard']);
        },
        error: err => {          
          this.toastMessage = err.error.message;
          this.ToastAuthentication(this.toastMessage);
          this.errorMessage = err.error.message;
          this.isLoginFailed = true;
        }
      });
    }else{

      this.authService.ldapUserLogin(email, password).subscribe({
        next: data => {
          this.storageService.saveUser(data);
          this.isLoggedIn = true;
          this.router.navigate(['/dashboard']);
        },
        error: err => {
          this.toastMessage = err.error.message;
          this.ToastAuthentication(this.toastMessage);
          this.errorMessage = err.error.message;
          this.isLoginFailed = true;
        }
      });

    }
  }

///////////////////////////// Toast Error authentication functions ///////////////////////////////////
ToastAuthentication(message:any) {

  this.toastrService.error('Bad credentials', message, {
    positionClass: 'toast-bottom-center',
    timeOut: 4000,
  });
}

  reloadPage(): void {
    this.router.navigate(['/dashboard']);
  }

}