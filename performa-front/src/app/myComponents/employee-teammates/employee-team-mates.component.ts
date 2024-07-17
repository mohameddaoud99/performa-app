import { Component } from '@angular/core';
import { UserService } from '../../_services/user.service';
import { EmailService } from '../../_services/email.service';
import { ToastrService } from 'ngx-toastr';
import { User } from './../../Interface/IUser'
import { StorageService } from '../../_services/storage.service';

@Component({
  selector: 'app-employee-team-mates',
  templateUrl: './employee-team-mates.component.html',
  styleUrl: './employee-team-mates.component.scss'
})
export class EmployeeTeamMatesComponent {

  EmployeesList: User[];
  dtoptions: DataTables.Settings = {};
  emailPresentUser:string="";
  presentUser:User
  listProfiles:any[];

  constructor(private userService: UserService,
    private emailService: EmailService,
    private toastrService: ToastrService,
    private storageService: StorageService,) {
  }


  ngOnInit(): void {
    this.dtoptions = {
      pagingType: 'full_numbers'
    };
    this.getUserByEmail();
    this.getAllProfiles();
  }

  getUserByEmail() {
    this.emailPresentUser = this.storageService.getUser().email;
    this.userService.getUserByEmail(this.emailPresentUser).subscribe(data => {
      this.presentUser = data;
      this.getEmployeesByTeam(this.presentUser.team)

    });
  }


  getEmployeesByTeam(team: string) {
      this.userService.getEmployeesByTeam(team).subscribe(data => {
      this.EmployeesList = data;
    })
  }

  getAllProfiles() {
      this.userService.getAllProfiles().subscribe(data => {
      this.listProfiles = data.data;     
    })
  }

  getEmployeesByProfile(profile: string) {
      this.userService.getEmployeesByProfile(profile).subscribe( {
        next: data => {
          this.EmployeesList = data;
         },
         error: err => {
           this.toastrService.error( err.error.message,"Not Found");
         }
    })
  }

  onProfileChange(event: any) {
    const team = event.target.value;
    this.getEmployeesByProfile(team);
  }



}
