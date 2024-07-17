import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const API_URL = 'http://localhost:8080/api/dashboard/';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  constructor(private http: HttpClient) { }

  public getEvaluationsCountByEmployee(id:number)
  {
    return this.http.get<number>(API_URL+'getEvaluationsCountByEmployee/'+id);
  }

  public getProfileCompetenciesCountById(id:number)
  {
    return this.http.get<number>(API_URL+'getProfileCompetenciesCountById/'+id);
  }
  public getEvaluationsDataByEmployee(id:number)
  {
    return this.http.get<any>(API_URL+'getEvaluationsDataByEmployee/'+id);
  }
  public getEmployeeNumberByStatusByAppraiser(id:number)
  {
    return this.http.get<any>(API_URL+'getEmployeeNumberByStatusByAppraiser/'+id);
  }
  public getEmployeeNumberByStatusByDepartment(department:string)
  {
    return this.http.get<any>(API_URL+'getEmployeeNumberByStatusByDepartment/'+department);
  }
  public getEmployeeNumberByStatusByRole(role:string)
  {
    return this.http.get<any>(API_URL+'getEmployeeNumberByStatusByRole/'+role);
  }
  public getAllEmployeeNumberByStatus()
  {
    return this.http.get<any>(API_URL+'getAllEmployeeNumberByStatus');
  }

}
