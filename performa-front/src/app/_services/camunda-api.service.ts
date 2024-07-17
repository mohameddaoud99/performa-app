import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';


const API_URL = 'http://localhost:8080/engine-rest/';
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': '*'
  })
};

@Injectable({
  providedIn: 'root'
})
export class CamundaApiService {

  
  constructor(private http: HttpClient) { }

  getAllProcessInstances(): Observable<any> {
    return this.http.get(API_URL + 'process-instance',httpOptions);
  }
  getAllTasksByAssgnee(assignee:string): Observable<any> {
    return this.http.get(API_URL + `task?assignee=${assignee}`,httpOptions);
  }

  completeTask(id:number, variables: any): Observable<any> {
    return this.http.post<any>(`${API_URL}` + `task/` + id + `/complete`, variables);
  }

  getTasksByName(taskName: any)
  {
    return this.http.get(`${API_URL}` + `task?name=`+taskName);
  }

  getTaskByNameAndAssignee(taskName: any, assignee : any): Observable<any> {
    return this.http.get(`${API_URL}` + `task?name=` + taskName + `&assignee=` + assignee);
  }

  getCurrentTaskByBuisnessKey(businessKey: any): Observable<any> {
    return this.http.get(`${API_URL}` + `task?processInstanceBusinessKey=` + businessKey);
  }

  getTaskByNameAndBuisnessKey(name:String,businessKey:String): Observable<any> {
    return this.http.get(`${API_URL}` + `task?name=`+name+`&processInstanceBusinessKey=`+businessKey);
  }

  getProcessByBuisnessKey(businessKey:String): Observable<any> {
    return this.http.get<any>(`${API_URL}`+`process-instance?businessKey=` +businessKey);
  }

  getTaskByBuisnessKey(businessKey:String): Observable<any> {
    return this.http.get(`${API_URL}` +`task?processInstanceBusinessKey=`+businessKey);  }

 
  
}
