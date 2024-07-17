import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { User } from 'src/app/Interface/IUser';
import { UserService } from 'src/app/_services/user.service';
import { StorageService } from '../../_services/storage.service';
import { EvaluationService } from '../../_services/evaluation.service';
import { ProfileCompetenciesService } from '../../_services/profile-competencies.service';
import { CamundaApiService } from 'src/app/_services/camunda-api.service';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { FormArray, FormBuilder, FormGroup } from '@angular/forms';
import { Evaluation } from 'src/app/Interface/IEvaluation';



@Component({
  selector: 'app-update-emloyee-evaluation',
  templateUrl: './update-emloyee-evaluation.component.html',
  styleUrl: './update-emloyee-evaluation.component.scss'
})
export class UpdateEmloyeeEvaluationComponent {


  emailPresentUser: any;
  presentUser: any;
  supervisors: any;
  supervisor_id: any;
  team: any;
  points: number = 0;
  employeeProfile: string = '';

  profileCompetenciesList: any[] = [];
  finalMark: number = 0;
  ratingOptions: number[] = [1, 1.25, 1.5, 1.75, 2, 2.25, 2.5, 2.75, 3, 3.25, 3.5, 3.75, 4, 4.25, 4.5, 4.75, 5];
  evaluationToUpdate: Evaluation = new Evaluation();
  appraiser: User;
  appraiserName: string = "";
  evaluationCompetenciesToUpdate: any
  updateModalVisibility: boolean = false
  updateAndSubmitModalVisibility: boolean = false
  employeeTask: any
  employeeTaskId: any
  employeeTaskAssignee: any
  moreDetailsCollapseVisibility: Boolean = false;


  constructor(
    private userService: UserService,
    private camundaApiService: CamundaApiService,
    private evaluationService: EvaluationService,
    private profileCompetenciesService: ProfileCompetenciesService,
    private storageService: StorageService,
    private toastrService: ToastrService,
    private router: Router,
    private route: ActivatedRoute,
    private fb: FormBuilder
  ) {
  }

  ngOnInit(): void {
    this.getUserByEmail();
    const evaluationId = this.route.snapshot.params['id']; // 'id' should match the parameter name in your route configuration
    if (evaluationId) {
      this.getEvaluationById(evaluationId);
    }
  }

  redirection() {
    this.router.navigate(['/employee-evaluations-list/',]);
  }


  getUserByEmail() {
    this.emailPresentUser = this.storageService.getUser().email;
    this.userService.getUserByEmail(this.emailPresentUser).subscribe(data => {
      console.log(data);

      this.presentUser = data;
      this.employeeProfile = this.presentUser.profile.name;
      this.getSupervisors();
      this.getTaskByNameAndAssignee();

    });
  }

  getAppraiser() {
    this.userService.getUserById(this.evaluationToUpdate.id_appraiser).subscribe((data: User) => {
      this.appraiser = data;
      this.appraiserName = data.firstname + " " + data.lastname;
    });
  }

  calculateTotalPoints(): number {
    let totalMark = 0;
    if (this.evaluationToUpdate && this.evaluationToUpdate.evaluationCompetencies) {
      totalMark = this.evaluationToUpdate.evaluationCompetencies.reduce((total: number, item: any) => {
        if (!item.selected) {
          return total + (item.appraiser_mark * item.weightage / 100);
        }
        return total;
      }, 0);
    }
    this.evaluationToUpdate.final_mark = totalMark;
    return totalMark;
  }

  calculatePoints(a: number | null, b: number | null): number {
    let calc = 0;
    if (a && b) calc = (a * b) / 100;
    return calc;
  }

  updateTotalPoints() {
    this.finalMark = this.profileCompetenciesList.reduce((total, gradeCompetency) => total + gradeCompetency.points / 100, 0);
  }

  getSupervisors() {
    this.team = this.presentUser.team;
    this.userService.getAppraiserByTeamAndProfile(this.team).subscribe(data => {
      console.log(data);
      this.supervisors = data.data;
    });
  }

  UpdateEvaluationData() {
    let isEmptyOrNull = false;
    // Check if any evaluation competence is empty or null
    for (const key in this.evaluationToUpdate.evaluationCompetencies) {
      const item = this.evaluationToUpdate.evaluationCompetencies[key];
      if (item.appraiser_mark == 0) {
        isEmptyOrNull = true;
        break;
      }
    }
    this.evaluationToUpdate.id_appraiser = this.appraiser.id

    this.evaluationToUpdate.user = this.evaluationToUpdate.user
    console.log(this.evaluationToUpdate);

    this.evaluationService.updateEmployeeEvaluation(this.evaluationToUpdate).subscribe({
      next: EvaluationData => {
        console.log(EvaluationData);
        this.toggleCLoseUpdateEvalutionModal()
        this.toastrService.success(EvaluationData.message);
        this.redirection();
      },
      error: err => {
        console.log("Error:", err);
      }
    });

  }

  getTaskByNameAndAssignee() {
    console.log(this.presentUser.email)
    this.camundaApiService.getTaskByNameAndBuisnessKey("Employee self evaluation", this.presentUser.email)
      .subscribe(data => {
        this.employeeTask = data;
        console.log(this.employeeTask);

        console.log(this.employeeTask[0]);
        this.employeeTaskAssignee = this.employeeTask[0].assignee;
        this.employeeTaskId = this.employeeTask[0].id;

        console.log(this.employeeTaskId);
        console.log(this.employeeTaskAssignee);
      });
  }

  updateAndSubmitEvaluationData() {
    let isEmptyOrNull = false;
    console.log(this.evaluationToUpdate.evaluationCompetencies);
    
    for (const key in this.evaluationToUpdate.evaluationCompetencies) {
      const item = this.evaluationToUpdate.evaluationCompetencies[key];
      if ((item.employee_mark == 0 && item.selected == true)) {
        isEmptyOrNull = true;
        break;
      }
    }
    if (isEmptyOrNull) {
      this.toastrService.error('Please fill in all competencies before saving.', 'Error');
      this.toggleCLoseUpdateAndSubmitEvalutionModal()
    } else {
      const variables: any = {};
      this.evaluationToUpdate.evaluationCompetencies.forEach((item: any) => {
        variables[item.competency.name] = {
          "value": item.employee_mark
        };
      });
      const camunda_eval = {
        "variables": variables
      };
      this.evaluationService.updateAndSubmitEmployeeEvaluation(this.evaluationToUpdate).subscribe({
        next: EvaluationData => {
          this.toggleCLoseUpdateAndSubmitEvalutionModal()
          this.toastrService.success(EvaluationData.message);
          this.camundaApiService.completeTask(this.employeeTaskId, camunda_eval)
            .subscribe(data => {
              this.redirection();
            });
        },
        error: err => {
          console.log("Error:", err);
        }
      });
    }
  }

  setAssignee(event: any) {
    this.supervisor_id = event.target.value;
    console.log(this.supervisor_id);
  }


  /////////////////////////////  get Evaluation By Id function ////////////////////////////////////////////

  getEvaluationById(id: any) {
    return this.evaluationService.getEvaluationByID(id).subscribe(data => {
      this.evaluationToUpdate = data;
      console.log(this.evaluationToUpdate)
      this.evaluationCompetenciesToUpdate = this.evaluationToUpdate.evaluationCompetencies
      this.getAppraiser();
      // console.log(this.evaluationCompetenciesToUpdate);

    })
  }


  /////////////////////////////  more Details collapse function ////////////////////////////////////////////
  moreDatailsToggleCollapse(): void {
    this.moreDetailsCollapseVisibility = !this.moreDetailsCollapseVisibility;
  }


  ///////////////////////////// Modal Confirm Save evaluation functions ///////////////////////////////////

  handleUpdateEvalutionModal(event: any) {
    this.updateModalVisibility = event;
  }

  toggleUpdateEvalutioneModal(): void {
    this.updateModalVisibility = !this.updateModalVisibility;
  }
  toggleCLoseUpdateEvalutionModal(): void {

    this.updateModalVisibility = !this.updateModalVisibility;
  }

  ///////////////////////////// Modal Confirm submit evaluation functions ///////////////////////////////////

  handleUpdateAndSubmitEvalutionModal(event: any) {
    this.updateAndSubmitModalVisibility = event;
  }

  toggleUpdateAndSubmitEvalutioneModal(): void {
    this.updateAndSubmitModalVisibility = !this.updateAndSubmitModalVisibility;
  }

  toggleCLoseUpdateAndSubmitEvalutionModal(): void {
    this.updateAndSubmitModalVisibility = !this.updateAndSubmitModalVisibility;
  }


}




