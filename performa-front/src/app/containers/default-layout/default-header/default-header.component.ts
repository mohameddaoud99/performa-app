import { Component, Input, Inject} from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';

import { ClassToggleService, HeaderComponent } from '@coreui/angular';
import { StorageService } from './../../../_services/storage.service';
import { AuthService } from './../../../_services/auth.service';
import { DOCUMENT } from '@angular/common';
import { Router } from '@angular/router';




@Component({
  selector: 'app-default-header',
  templateUrl: './default-header.component.html',
})
export class DefaultHeaderComponent extends HeaderComponent {

  @Input() sidebarId: string = "sidebar";
  public newMessages = new Array(4)
  public newTasks = new Array(5)
  public newNotifications = new Array(5)

  public username :string=""
  public useremail :string=""


  constructor(private storageService: StorageService,
     private authService: AuthService,
     @Inject(DOCUMENT) private document: Document,
     private classToggler: ClassToggleService,
     private router: Router
     ) {
    super();
  }

  ngOnInit(): void {
    this.username =this.storageService.getUser().username
    this.useremail =this.storageService.getUser().email

  }


  logout(): void {
    this.authService.logout().subscribe({
      next: res => {
        console.log(res);
        this.storageService.clean();
        
        this.router.navigate(['/login']);
      },
      error: err => {
        console.log(err);
      }
    });
  }
}
