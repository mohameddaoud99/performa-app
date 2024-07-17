import { Component } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { UserService } from 'src/app/_services/user.service';
import { StorageService } from '../../_services/storage.service';
import { User } from 'src/app/Interface/IUser';
import { RoleService } from 'src/app/_services/role.service';
import { CamundaApiService } from 'src/app/_services/camunda-api.service';


@Component({
  selector: 'app-employee-status',
  templateUrl: './employee-status.component.html',
  styleUrls: ['./employee-status.component.scss']
})
export class EmployeeStatusComponent {

  UsersList: any;
  allRoles:any;
  ListProcessInstances:any;
  ListTasksByAssignee:any;
  updatedUsersList:any;
  email: any;
  role: any;
  assignee:any;
  team: any;
  toastMessageErrror!: string;

  presentUser: User | undefined;

  constructor(private camundaApiService: CamundaApiService,private userService: UserService,private roleService  :RoleService ,private storageService: StorageService, private toastrService: ToastrService) {
  }

  ngOnInit(): void {
    this.getUserByEmail();
    this.getAllRoles();
    //this.getAllProcessInstances();
    //this.getAllTasksByAssignee();
  }

  getAllRoles()
  {
    return this.roleService.getAllRoles().subscribe(data => {
      this.allRoles=data
    })
  }

  getUserByEmail() {
    this.email = this.storageService.getUser().email;
    this.userService.getUserByEmail(this.email).subscribe(data => {
      this.presentUser = data;
      if (this.presentUser) {
        this.getUsersByRoleAndTeam();
      }
    });
  }


  onRoleChange(event: any) {
    const role = event.target.value;
    this.team = this.presentUser?.team;
    this.userService.getLastEvaluationForEachUserByTeamAndRole(role, this.team).subscribe({
      next: data => {
       this.UsersList = data.data;
      // Refresh the list of process instances
      //this.getAllProcessInstances();
      //this.getAllTasksByAssignee();
      },
      error: err => {
        this.toastMessageErrror = err.error.message;
        this.toastrService.error("Not Found", this.toastMessageErrror);
      }
    });
  }


  getUsersByRoleAndTeam() {
    if (!this.presentUser) {
      console.error("presentUser is undefined");
      return;
    }
    this.role = this.storageService.getUser().role;
    this.team = this.presentUser.team;
    this.userService.getLastEvaluationForEachUserByTeamAndRole(this.role, this.team).subscribe({
      next: data => {
       this.UsersList = data.data;
      },
      error: err => {
        this.toastMessageErrror = err.error.message;
        this.toastrService.error("Not Found", this.toastMessageErrror);
      }
    });
  }

 
}
