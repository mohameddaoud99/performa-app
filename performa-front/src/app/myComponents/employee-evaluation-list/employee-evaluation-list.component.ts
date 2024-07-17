import { Component, OnInit, OnDestroy } from '@angular/core';
import { CamundaApiService } from 'src/app/_services/camunda-api.service';
import { EvaluationService } from 'src/app/_services/evaluation.service';
import { StorageService } from '../../_services/storage.service';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-employee-evaluation-list',
  templateUrl: './employee-evaluation-list.component.html',
  styleUrls: ['./employee-evaluation-list.component.scss']
})
export class EmployeeEvaluationListComponent implements OnInit {
  id_employee: any;
  evalualtion_List: any;
  userProcess: string= "";
  enableEvaluation: boolean= false;
  workflow_status: string= "";
  existSubmittedEvalution:boolean = false;
  private routerSubscription:Subscription;

  constructor(
    private camundaApiService: CamundaApiService,
    private evaluationService: EvaluationService,
    private storageService: StorageService, 

    private router: Router
  ) { }

  ngOnInit(): void {
    this.getEvaluationsByEmployee();
    this.getCurrentTaskByBuisnessKey();
  }


  getEvaluationsByEmployee() {
    this.id_employee = this.storageService.getUser().id
    return this.evaluationService.getEvaluationsByEmployeeSortedByDate(this.id_employee).subscribe(data => {
      this.evalualtion_List = data.data
      this. getProcessByBuisnessKey()
    })
  }


  redirection(id: any) {
    this.router.navigate(['/evaluation/', id]);
  }


  getProcessByBuisnessKey() {    
    return this.camundaApiService.getProcessByBuisnessKey(this.storageService.getUser().email).subscribe(data => {
      this.userProcess = data

      if (this.userProcess && this.userProcess.length > 0) {
        this.enableEvaluation = true
        const hasSubmittedEvaluation = this.evalualtion_List.some((evaluation: any) => evaluation.status != "Completed");
        if (hasSubmittedEvaluation) {
          this.enableEvaluation = false;
        }
      }
    })
  }

  getCurrentTaskByBuisnessKey() {
    return this.camundaApiService.getCurrentTaskByBuisnessKey(this.storageService.getUser().email).subscribe(data => {
      this.workflow_status = data[0].name
    })
  }



}
