import { Component } from '@angular/core';
import { EvaluationService } from '../../_services/evaluation.service';
import { UserService } from 'src/app/_services/user.service';
import { StorageService } from '../../_services/storage.service';
import { ProfileCompetenciesService } from '../../_services/profile-competencies.service';
import { Evaluation } from 'src/app/Interface/IEvaluation';
import { EvaluationCompetency } from 'src/app/Interface/IEvaluationCompetency';
import { User } from 'src/app/Interface/IUser';
import { FormArray, FormBuilder, FormGroup } from '@angular/forms';


@Component({
  selector: 'app-employee-competencies',
  templateUrl: './employee-competencies.component.html',
  styleUrl: './employee-competencies.component.scss'
})
export class EmployeeCompetenciesComponent {

  formm: Evaluation= new Evaluation();
  presentUser:User;
  emailPresentUser:string=""
  employeeProfile:string=""
  profileCompetencies:any


  constructor(
    private userService: UserService,
    private evaluationService: EvaluationService,
    private profileCompetenciesService: ProfileCompetenciesService,
    private storageService: StorageService,
    private fb: FormBuilder
  ) {  }


  ngOnInit(): void {
    this.getUserByEmail();
  }

  getProfileCompetenciesByProfileName(profile_name: string): void {
    this.profileCompetenciesService.getProfileCompetenciesByProfileName(profile_name).subscribe(data => {
     this.profileCompetencies=data
     
    });
  }
  
  getUserByEmail() {
    this.emailPresentUser = this.storageService.getUser().email;
    this.userService.getUserByEmail(this.emailPresentUser).subscribe(data => {
      this.presentUser = data;
      this.employeeProfile = this.presentUser.profile.name;
      this.getProfileCompetenciesByProfileName(this.employeeProfile);
    });
  }


}
