import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { User } from 'src/app/Interface/IUser';
import { saveAs } from "file-saver";
import { UserService } from 'src/app/_services/user.service';
import { StorageService } from '../../_services/storage.service';
import { EvaluationService } from '../../_services/evaluation.service';
import { CamundaApiService } from 'src/app/_services/camunda-api.service';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';

import { Evaluation } from 'src/app/Interface/IEvaluation';


@Component({
  selector: 'app-reviewer-validation-step',
  templateUrl: './reviewer-validation-step.component.html',
  styleUrl: './reviewer-validation-step.component.scss'
})
export class ReviewerValidationStepComponent {

  emailPresentUser: any;
  presentUser: any;
  supervisors: any;
  supervisor_id: any;
  team: any;
  points: number = 0;
  employeeProfile: string = '';
  category_competenciesList: any;
  appraiserRating: any;
  employeeRating: any;
  profileCompetenciesList: any[] = [];
  finalMark: number = 0;
  ratingOptions: number[] = [1, 1.25, 1.5, 1.75, 2, 2.25, 2.5, 2.75, 3, 3.25, 3.5, 3.75, 4, 4.25, 4.5, 4.75, 5];
  weightageOptions: number[] = [20, 50, 70];
  evaluationToEvaluate: Evaluation = new Evaluation();
  evaluationCompetenciesToEvaluate: any
  employeeTask: any
  employeeTaskId: any
  employeeTaskAssignee: any
  submitModalVisibility: boolean = false
  rejectEvaluationModalVisibility = false
  popDelegateVisible: boolean = false
  submitted: boolean = false;

  public isDateEmpty: boolean = true;
  public isReasonsRejectionEmpty: boolean = true;
  public isSubmittingRejection: boolean = false; 

  constructor(
    private userService: UserService,
    private camundaApiService: CamundaApiService,
    private evaluationService: EvaluationService,
    private storageService: StorageService,
    private toastrService: ToastrService,
    private router: Router,
    private route: ActivatedRoute,
  ) {}

  ngOnInit(): void {

    const evaluationId = this.route.snapshot.params['id'];
    if (evaluationId) {
      this.getUserByEmail();
      this.getEvaluationById(evaluationId);
    }
    this.setCurrentDateTime();
    //this.getTaskByNameAndBuisnessKey();
  }


  updateDateValidity(date: string): void {
    this.isDateEmpty = !date;
  }

  updateReasonsRejectionValidity(reasons: string): void {
    this.isReasonsRejectionEmpty = !reasons; 
  }

  getUserByEmail() {
    this.emailPresentUser = this.storageService.getUser().email;
    this.userService.getUserByEmail(this.emailPresentUser).subscribe(data => {
      this.presentUser = data;
      this.employeeProfile = this.presentUser.profile.name;
      this.getSupervisors();
    });
  }

  calculateTotalPoints(): number {
    let totalMark: number = 0;
    if (this.evaluationToEvaluate && this.evaluationToEvaluate.evaluationCompetencies) {
      totalMark = this.evaluationToEvaluate.evaluationCompetencies.reduce((total: number, item: any) => {
        if (item.selected) {
          return total + (item.appraiser_mark * item.weightage / 100);
        }
        return total;
      }, 0);
    }
    this.evaluationToEvaluate.final_mark = totalMark;
    return totalMark;
  }

  calculatePoints(id: number | null): number {
    let points: number = 0;
    this.evaluationToEvaluate.evaluationCompetencies.forEach((item) => {
      if (item.id == id && item.appraiser_mark && item.weightage) {
        points = (item.appraiser_mark * item.weightage) / 100;
        item.points = points;
      }
    });
    return points;
  }

  getSupervisors() {
    this.team = this.presentUser.team;
    this.userService.getAppraiserByTeamAndProfile(this.team).subscribe(data => {
      this.supervisors = data.data;
    });
  }

  getTaskByNameAndBuisnessKey() {
    this.camundaApiService.getTaskByNameAndBuisnessKey("Reviewer validation", this.evaluationToEvaluate.user.email)
      .subscribe(data => {
        this.employeeTask = data;
        this.employeeTaskId = this.employeeTask[0].id;
      });
  }


  currentDateTime: string = '';
  setCurrentDateTime() {
    const currentDateTime = new Date();
    this.currentDateTime = this.formatDate(currentDateTime);
  }
  formatDate(date: Date): string {
    const year = date.getFullYear();
    const month = ('0' + (date.getMonth() + 1)).slice(-2);
    const day = ('0' + date.getDate()).slice(-2);
    const hours = ('0' + date.getHours()).slice(-2);
    const minutes = ('0' + date.getMinutes()).slice(-2);
    return `${year}-${month}-${day}T${hours}:${minutes}`;
  }

  validateEvaluation() {
    this.isDateEmpty=false
    this.submitted = true
    const data = {
      "variables": {
        "accepted": {
          "value": true
        },
        "notAccepted": {
          "value": false
        },
        "EvaluationDateReviwer":{"value":this.evaluationToEvaluate.dateReviewerEvaluation}
      }
    }

    const reviewerEvaluationDate = this.evaluationToEvaluate.dateReviewerEvaluation
    this.evaluationService.EvaluationValidationByReviewerStep(this.evaluationToEvaluate).subscribe({
      next: EvaluationData => {
        this.camundaApiService.completeTask(this.employeeTaskId, data).subscribe({
          next: data => {
            this.toggleCLoseSubmitEvalutionModal()
            this.toastrService.success(EvaluationData.message);
            this.redirection();
            const reviewerEvaluationDate = this.evaluationToEvaluate.dateReviewerEvaluation
          },
          error : err =>{   
            this.toastrService.error(err.error.message)
          }
        })
      },
      error: err => {
      }
    });
  }


  rejectEvaluation() {
    this.isSubmittingRejection = true;
    const data = {
      "variables": {
        "accepted": {
          "value": false
        },
        "notAccepted": {
          "value": true
        }
      }
    }
    this.evaluationService.rejectEmployeeEvaluation(this.evaluationToEvaluate).subscribe({
      next: EvaluationData => {
        this.toastrService.success(EvaluationData.message);
        this.toggleCLoseRejectEvalutionModal();
        this.camundaApiService.completeTask(this.employeeTaskId, data)
          .subscribe(data => {
            this.redirection();
          });
      },
      error: err => {
      }
    });

    setTimeout(() => {
     
      this.isSubmittingRejection = false;
    }, 2000);
  }

  redirection() {
    this.router.navigate(['/reviewer-evaluation',]);
  }


  setAssignee(event: any) {
    this.supervisor_id = event.target.value;
  }

  /////////////////////////////  get Evaluation By Id function ////////////////////////////////////////////
  getEvaluationById(id: any) {
    return this.evaluationService.getEvaluationByID(id).subscribe(data => {
      this.evaluationToEvaluate = data;
      this.evaluationCompetenciesToEvaluate = this.evaluationToEvaluate.evaluationCompetencies
      this.getTaskByNameAndBuisnessKey()
    })
  }


  delegateEvaluation() {
    const data = {
      "variables": {
        "accepted": {
          "value": false
        },
        "notAccepted": {
          "value": false
        },
        "delegated": {
          "value": true
        }
      }
    }
    this.evaluationService.delegateEmployeeEvaluation(this.evaluationToEvaluate).subscribe({
      next: EvaluationData => {
        this.closePopDelegate()
        this.toastrService.success(EvaluationData.message)
        this.camundaApiService.completeTask(this.employeeTaskId, data)
        .subscribe(data => {
          this.redirection();
        });
      },
      error: err => {
      }
    });
  }


  ///////////////////////////// Modal Confirm submit evaluation functions ///////////////////////////////////

  handleRejectEvalutionModal(event: any) {
    this.rejectEvaluationModalVisibility = event;
  }

  toggleRejectEvalutionModal(): void {
    this.rejectEvaluationModalVisibility = !this.rejectEvaluationModalVisibility;
  }

  toggleCLoseRejectEvalutionModal(): void {
    this.rejectEvaluationModalVisibility = !this.rejectEvaluationModalVisibility;
  }

  ///////////////////////////// Modal Delegate appraiser functions ///////////////////////////////////

  handleUpdateModal(event: any) {
    this.popDelegateVisible = event;
  }

  togglePopDelegate(): void {
    this.popDelegateVisible = !this.popDelegateVisible;
  }

  closePopDelegate(): void {
    this.popDelegateVisible = !this.popDelegateVisible;
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
