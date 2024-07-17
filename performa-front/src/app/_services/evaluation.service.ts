import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';



const API_URL = 'http://localhost:8080/api/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};


@Injectable({
  providedIn: 'root'
})
export class EvaluationService {

  constructor(private http: HttpClient) { }

  

  createEmployeeEvaluation(evaluation:any): Observable<any>{ 
    return this.http.post(API_URL + 'employee_evaluation/create',evaluation,httpOptions); 
  }

  updateEmployeeEvaluation(evaluation:any): Observable<any>{ 
    return this.http.post(API_URL + 'employee_evaluation/update',evaluation,httpOptions);
  }

  submitEmployeeEvaluation(evaluation:any): Observable<any>{ 
    return this.http.post(API_URL + 'employee_evaluation/submit',evaluation,httpOptions); 
  }

  updateAndSubmitEmployeeEvaluation(evaluation:any): Observable<any>{ 
    return this.http.post(API_URL + 'employee_evaluation/updateAndSubmit',evaluation,httpOptions); 
  }
////////////////////////////

  saveEvaluationReviewerStep(evaluation:any): Observable<any>{ 
    return this.http.post(API_URL + 'employee_evaluation/saveEvaluationReviewerStep',evaluation,httpOptions); 
  }

  updateEvaluationReviewerStep(evaluation:any): Observable<any>{ 
    return this.http.post(API_URL + 'employee_evaluation/updateEvaluationReviewerStep',evaluation,httpOptions); 
  }

  submitReviewerStep(evaluation:any): Observable<any>{ 
    return this.http.post(API_URL + 'employee_evaluation/submitReviewerStep',evaluation,httpOptions); 
  }

  EvaluationValidationByReviewerStep(evaluation:any): Observable<any>{ 
    return this.http.post(API_URL + 'employee_evaluation/EvaluationValidationByReviewerStep',evaluation,httpOptions); 
  }


  rejectEmployeeEvaluation(evaluation:any): Observable<any>{ 
    return this.http.post(API_URL + 'employee_evaluation/rejectEmployeeEvaluation',evaluation,httpOptions); 
  }

  delegateEmployeeEvaluation(evaluation:any): Observable<any>{ 
    return this.http.post(API_URL + 'employee_evaluation/delegateEmployeeEvaluation',evaluation,httpOptions); 
  }

  getEvaluationsByEmployeeSortedByDate(id_employee:number ): Observable<any> {
    return this.http.get(API_URL + 'employee_evaluation/getEvaluationsByEmployeeSortedByDate/'+id_employee);
  }

  getEvaluationByID(id:number ): Observable<any> {
    return this.http.get(API_URL + 'employee_evaluation/getEvaluationById/'+id);
  }

  getLastEmployeeEvaluation(id_employee:number ): Observable<any> {
    return this.http.get(API_URL + 'employee_evaluation/lastEvaluation/'+id_employee);
  }

  setEvaluationStatus(evaluationId: number, evaluation:any): Observable<any> {
    return this.http.post<any>(API_URL + 'employee_evaluation/setEvaluationStatus/' + evaluationId, evaluation);
  }

  getCountDraftEvaluations(): Observable<any> {
    return this.http.get(API_URL + 'employee_evaluation/getCountDraftEvaluations');
  }

  getEmployeeEvaluationByAppraiser(id:any): Observable<any> {
    return this.http.get(API_URL + 'employee_evaluation/getEmployeeEvaluationsByAppraiser/'+id);
  }
 
  getEvaluationExcelReport(id:any): Observable<any> {
    return this.http.get(API_URL + 'excel/download/'+id,{ responseType: 'blob'});
  }
  
}
