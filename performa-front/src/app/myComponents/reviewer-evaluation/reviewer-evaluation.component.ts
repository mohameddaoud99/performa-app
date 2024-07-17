import { Component } from '@angular/core';
import { CamundaApiService } from 'src/app/_services/camunda-api.service';
import { Router } from '@angular/router';
import { UserService } from 'src/app/_services/user.service';
import { StorageService } from '../../_services/storage.service';
import { EvaluationService } from '../../_services/evaluation.service';

@Component({
  selector: 'app-reviewer-evaluation',
  templateUrl: './reviewer-evaluation.component.html',
  styleUrls: ['./reviewer-evaluation.component.scss'] // Corrected styleUrls
})
export class ReviewerEvaluationComponent {

  FiltredTasksList: any[] = [];
  emailPresentUser:any
  presentUser: any ;
  presentUserId:number=0
  listEvaluations!:any[];

  constructor(
    private camundaApiService: CamundaApiService,
    private storageService: StorageService, 
    private userService: UserService,
    private evaluationService: EvaluationService,
    private router: Router,
      ) {

}

ngOnInit() { 
  this.getUserByEmail();
  this.getTasksByNameAndAssignee();
  
}


getUserByEmail() {
  this.emailPresentUser = this.storageService.getUser().email;
  this.userService.getUserByEmail(this.emailPresentUser).subscribe(data => {
    this.presentUser = data;
    this.presentUserId=this.presentUser.id
    this.getEmployeeEvaluationByAppraiser( this.presentUserId);
  });
}

getTasksByNameAndAssignee() {
  this.camundaApiService.getTasksByName("Reviewer evaluation").subscribe(data => {
    if (Array.isArray(data)) {
      const tasks = data;

      tasks.forEach(async (task) => {
        const formVariablesResponse = await fetch(`http://localhost:8080/engine-rest/task/${task.id}/form-variables`);
        const formVariables = await formVariablesResponse.json();

        if (formVariables.assignee.value === this.presentUser.email) {
          this.FiltredTasksList.push(formVariables);
        }
      });
    } else {
      console.error("Data is not an array of tasks.");
    }
  });
}

getEmployeeEvaluationByAppraiser(id:number)
{
  this.evaluationService.getEmployeeEvaluationByAppraiser(id).subscribe(data=>{
    this.listEvaluations = data.data
  })
}
 
redirectionValidation(id: any) {
  this.router.navigate(['/reviewer-validation-step/', id]);
}
redirectionEvaluation(id: any) {
  this.router.navigate(['/reviewer-evaluation-step/', id]);
}
redirectionUpdateEvaluation(id: any) {
  this.router.navigate(['/reviewer-step-update-evaluation/', id]);
}
}
