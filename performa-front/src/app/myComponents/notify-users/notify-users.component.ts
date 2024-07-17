import {Component} from '@angular/core';
import {UserService } from '../../_services/user.service';
import {EmailService } from '../../_services/email.service';
import {ToastrService } from 'ngx-toastr';
import {User} from './../../Interface/IUser'
import {cilCheck} from '@coreui/icons';


@Component({
  selector: 'app-users',
  templateUrl: './notify-users.component.html',
  styleUrl: './notify-users.component.scss'
})


export class NotifyUsersComponent {

UsersList!:User[];



dtoptions:DataTables.Settings={};
selectAll= false;
updatedUsersList:any
parentSelector:boolean=false;
isAnyCheckboxSelected: boolean = false;
filteredUsersList: User[] = [];
filteredUserEmails: string[] = [];
confirm_Send_Emails_Modal_Visibility = false;
errorMessage:any
departmentsList:any
rolesList:any
selectedFilterOption:String="ROLE";

visible = true;

onVisibleChange(eventValue: boolean) {
  this.visible = eventValue;
}

onResetDismiss() {
  this.visible = true;
}


  constructor(private userService: UserService,private emailService: EmailService, private toastrService: ToastrService,) {
 
   }


  ngOnInit():void{
    this.dtoptions ={
      pagingType: 'full_numbers'      
    };
    this.getUsersOnlyAdmins();
    this.getAllDepartments();
    this.getAllRoles();
  }


  getUsersByDepartment(departmentName:string)
  {
    return this.userService.getUsersByDepartment(departmentName).subscribe(data=>{
      this.UsersList = data.data;
      this.updatedUsersList = this.UsersList.map((user) => ({ ...user, isSelected: false }));
    })
  }

  getUsersByRole(roleName:string)
  {
    return this.userService.getUsersByRole(roleName).subscribe(data=>{
      this.UsersList = data.data;
      this.updatedUsersList = this.UsersList.map((user) => ({ ...user, isSelected: false }));
    })
  }

  onDepartmentChange(event: any) {
    const departmentName = event.target.value;
    if (departmentName === 'ALL DEPARTMENTS') {
      this.getUsersOnlyAdmins();
    } else {
      this.getUsersByDepartment(departmentName);
    }
  }

  onRoleChange(event: any) {
    const roleName = event.target.value;
    if (roleName === 'ALL ROLES') {
      this.getUsersOnlyAdmins();
    } else {
      this.getUsersByRole(roleName);
    }
  }


  ///////////////////////////// Select checkbox ////////////////////////////////////////////

  onChangeUser($event:any){
    const id = $event.target.value;
    const isChecked = $event.target.checked;


    this.updatedUsersList.forEach((d: any) => {
      if(d.id == id){
        d.isSelected =isChecked;
        return d;
      }
      if(id ==-1){
        d.isSelected =this.parentSelector;
        return d;
      }
      return d;
    })
    this.isAnyCheckboxSelected = this.updatedUsersList.some((user: any) => user.isSelected);
    }


    sendEmails(emails: string[]) {
      this.emailService.sendFilteredEmails(emails)
        .subscribe(response => {
          this.toastrService.info("success", "Emplyee(s) notified successfully");
        }, error => {
          let errorMessage = error.error.slice(1, -1);
          this.errorMessage = errorMessage.split(',');
        });
    }


    filterCheckedUsers(): void {
      this.filteredUsersList = this.updatedUsersList.filter((user:any) => user.isSelected === true);
      this.filteredUserEmails = this.filteredUsersList.map((user) => user.email);
    }
    
    onFilterButtonClick(): void {
      this.filterCheckedUsers();
      this.sendEmails(this.filteredUserEmails);
      this.toggleCloseConfirmSendEmailsModal();
     
    }
  
  ///////////////////////////// Modal Confirm Send emails functions ///////////////////////////////////

  handleConfirmSendEmailsModal(event: any) {
    this.confirm_Send_Emails_Modal_Visibility  = event;
  }

  toggleConfirmSendEmailsModal(): void {
    this.confirm_Send_Emails_Modal_Visibility  = !this.confirm_Send_Emails_Modal_Visibility ; 
  }
  toggleCloseConfirmSendEmailsModal(): void {
    
    this.confirm_Send_Emails_Modal_Visibility  = !this.confirm_Send_Emails_Modal_Visibility ;
  }



  
  /////////////////////////////  get all departments function ////////////////////////////////////////

  getUsersOnlyAdmins() {
    return this.userService.getUsersOnlyAdmins().subscribe(data => {
      this.UsersList = data.data;
      this.updatedUsersList = this.UsersList.map((user) => ({ ...user, isSelected: false }));
    })
  }

  getAllDepartments() {
    return this.userService.getAllDepartments().subscribe(data => {
      this.departmentsList = data.data;     
    })
  }

  getAllRoles() {
    return this.userService.getAllRoles().subscribe(data => {
      this.rolesList = data;     
    })
  }


  
  
}
