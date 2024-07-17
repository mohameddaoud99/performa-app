import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { User } from 'src/app/Interface/IUser';
import { Evaluation } from 'src/app/Interface/IEvaluation';
import { UserService } from 'src/app/_services/user.service';
import { StorageService } from '../../_services/storage.service';
import { EvaluationService } from '../../_services/evaluation.service';
import { ProfileCompetenciesService } from '../../_services/profile-competencies.service';
import { CamundaApiService } from 'src/app/_services/camunda-api.service';
import { Router } from '@angular/router';
import { FormArray, FormBuilder, FormGroup } from '@angular/forms';
import { isNull } from 'lodash-es';
import { EvaluationCompetency } from 'src/app/Interface/IEvaluationCompetency';


@Component({
  selector: 'app-employee-evaluation',
  templateUrl: './employee-evaluation.component.html',
  styleUrls: ['./employee-evaluation.component.scss']
})
export class EmployeeEvaluationComponent implements OnInit {
  emailPresentUser: any;
  presentUser: User;
  supervisors: any;
  supervisor_id: number = 0;
  team: any;
  points: number = 0;
  employeeProfile: string = '';
  profileCompetenciesList: any[] = [];
  finalMark: number = 0;
  appraiser: User;
  appraiserName: string = "";
  ratingOptions: number[] = [1, 1.25, 1.5, 1.75, 2, 2.25, 2.5, 2.75, 3, 3.25, 3.5, 3.75, 4, 4.25, 4.5, 4.75, 5];
  saveModalVisibility = false;
  submitModalVisibility = false;
  employeeTask: any
  employeeTaskId: any
  employeeTaskAssignee: any

  formm: Evaluation = new Evaluation();
  constructor(
    private userService: UserService,
    private camundaApiService: CamundaApiService,
    private evaluationService: EvaluationService,
    private profileCompetenciesService: ProfileCompetenciesService,
    private storageService: StorageService,
    private toastrService: ToastrService,
    private router: Router,
    private fb: FormBuilder
  ) { }

  ngOnInit(): void {
    this.getUserByEmail();
  }

  redirection() {
    this.router.navigate(['/employee-evaluations-list/',]);
  }


  getProfileCompetenciesByProfileName(profile_name: string): void {
    this.profileCompetenciesService.getProfileCompetenciesByProfileName(profile_name).subscribe(data => {
      this.setItems(data);
    });
  }

  //////////
  getUserByEmail() {
    this.emailPresentUser = this.storageService.getUser().email;
    this.userService.getUserByEmail(this.emailPresentUser).subscribe(data => {
      this.presentUser = data;
      this.employeeProfile = this.presentUser.profile.name;
      this.getProfileCompetenciesByProfileName(this.employeeProfile);
      this.getSupervisors();
      this.getTaskByNameAndAssignee();
      this.getAppraiser();

    });
  }

  getAppraiser() {
    this.userService.getUserById(this.presentUser.id_appraiser).subscribe((data: User) => {
      this.appraiser = data;
      this.appraiserName = data.firstname + " " + data.lastname;
    });
  }

  getSupervisors() {
    this.team = this.presentUser.team;
    this.userService.getAppraiserByTeamAndProfile(this.team).subscribe(data => {
      this.supervisors = data.data;
    });
  }


  SaveEvaluationData() {
    this.formm.user = this.presentUser;
    this.formm.id_appraiser = this.appraiser.id
    // this.toastrService.error('Please fill in all competencies before saving.');
    this.evaluationService.createEmployeeEvaluation(this.formm).subscribe({
      next: EvaluationData => {
        this.toastrService.success(EvaluationData.message)
        this.toggleCLoseSaveEvalutionModal()
        this.redirection();
      },
      error: err => {
        this.toastrService.error("Error: " + err.error.detail)
      }
    });

  }


  getTaskByNameAndAssignee() {
    this.camundaApiService.getTaskByNameAndAssignee("Employee self evaluation", this.presentUser.email)
      .subscribe(data => {
        this.employeeTask = data;
        this.employeeTaskAssignee = this.employeeTask[0].assignee;
        this.employeeTaskId = this.employeeTask[0].id;
      });
  }

  SubmitEvaluationData() {
    let isEmptyOrNull = false;
    for (const key in this.formm.evaluationCompetencies) {
      const item = this.formm.evaluationCompetencies[key];
      if (item.employee_mark === 0 && item.selected === false) {
        isEmptyOrNull = true;
        break;
      }
    }
    if (isEmptyOrNull) {
      this.toastrService.error('Please fill in all competencies before saving.', 'Error');
      this.toggleCLoseSubmitEvalutionModal()

    } else {

      this.formm.id_appraiser = this.appraiser.id
      this.formm.user = this.presentUser;

      const variables: any = {};
      this.formm.evaluationCompetencies.forEach((item: any) => {
        variables[item.competency.name] = {
          "value": item.employee_mark
        };
      });

      const camunda_eval = {
        "variables": variables
      };
      // this.toastrService.error('Please fill in all competencies before saving.');
      this.evaluationService.submitEmployeeEvaluation(this.formm).subscribe({
        next: EvaluationData => {

          this.toastrService.success(EvaluationData.message)
          this.toggleCLoseSubmitEvalutionModal();
          this.redirection();
        },
        error: err => {
        }
      });
      this.camundaApiService.completeTask(this.employeeTaskId, camunda_eval)
        .subscribe(data => {
        });
    }
  }


  // Method to set initial items
  setItems(items: any) {
    this.formm.evaluationCompetencies = items.map((item: any) => {
      let e: EvaluationCompetency = new EvaluationCompetency();
      e.competency = item.competency,
        e.level = item.level
      return e;
    });
  }


  ///////////////////////////// Modal Confirm Save evaluation functions ///////////////////////////////////

  handleSaveEvalutionModal(event: any) {
    this.saveModalVisibility = event;
  }

  toggleSaveEvalutioneModal(): void {
    this.saveModalVisibility = !this.saveModalVisibility;
  }
  toggleCLoseSaveEvalutionModal(): void {

    this.saveModalVisibility = !this.saveModalVisibility;
  }

  ///////////////////////////// Modal Confirm submit evaluation functions ///////////////////////////////////

  handleSubmitEvalutionModal(event: any) {
    this.submitModalVisibility = event;
  }

  toggleSubmitEvalutioneModal(): void {
    this.submitModalVisibility = !this.submitModalVisibility;
  }
  toggleCLoseSubmitEvalutionModal(): void {

    this.submitModalVisibility = !this.submitModalVisibility;
  }

}



