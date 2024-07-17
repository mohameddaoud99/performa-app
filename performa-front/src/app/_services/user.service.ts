import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../Interface/IUser';

const API_URL = 'http://localhost:8080/api/user/';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private http: HttpClient) {}

  getAllRoles(): Observable<any> {
    return this.http.get('http://localhost:8080/api/role/getAllRoles');
  }

  getRoleById(id:number)
  {
    return this.http.get(API_URL + 'getRoleById/'+id);
  }
  
  createUser(data:User): Observable<any>
  {
    return this.http.post(API_URL + 'createUser',data, httpOptions); 
  }

  updateUser(data:User): Observable<any>
  {
    return this.http.post(API_URL + 'updateUser',data,httpOptions); 
  }

  // updateUser(user:any)
  // {
  //   return this.http.post(API_URL+"updateRole",user,httpOptions);
  // }

  getAllUsers(): Observable<any> {
    return this.http.get(API_URL + 'getAllUsers');
  }

  getUsersOnlyAdmins(): Observable<any> {
    return this.http.get(API_URL + 'getUsersOnlyAdmins');
  }
  
  getAllGrades(): Observable<any> {
    return this.http.get('http://localhost:8080/api/grade/getAllGrades');
  }

  getAllDepartments(): Observable<any> {
    return this.http.get(API_URL + 'getAllDepartments');
  }

 
  getAllCompetencies(): Observable<any> {
    return this.http.get('http://localhost:8080/api/comptency/getAllCompetencies');
  }

  affectCompetencies(employee_id:number, competencies:any[] ): Observable<any> {
    return this.http.post('http://localhost:8080/api/comptency/affectCompetencies/'+employee_id,{competencies},httpOptions);
  }
  
  getUsersByDepartment(departmentName:string): Observable<any> {
    return this.http.get(API_URL + 'getUsers/'+departmentName);
  }

  getUsersByRole(roleName:string): Observable<any> {
    return this.http.get(API_URL + 'getUsersByRole/'+roleName);
  }

  getEmployeesByTeam(team:string): Observable<any> {
    return this.http.get(API_URL + 'getUsersByTeam/'+team);
  }

  getEmployeesByProfile(profile:string): Observable<any> {
    return this.http.get(API_URL + 'getUsersByProfile/'+profile);
  }

  deleteUser(id:number)
  {
    return this.http.delete(API_URL + 'deleteUser/'+id);
  }

  getUserById(id:number |null): Observable<User> {
    return this.http.get<User>(API_URL + 'getUserById/'+id);
  }

  getUserByEmail(email:string): Observable<any> {
    return this.http.get(API_URL + 'getUserByEmail/'+email);
  }

  getUsersByRoleAndTeam(role:string , team:string): Observable<any> {
    return this.http.get(API_URL + 'getUsersByRoleAndTeam/'+role+'/'+team);
  }

  getAppraiserByTeamAndProfile(team:string ): Observable<any> {
    return this.http.get(API_URL + 'getAppraisersByTeamAndProfile/'+team);
  }

  getAllAppraisers(): Observable<any> {
    return this.http.get(API_URL + 'getAllAppraisers');
  }

  getLastEvaluationForEachUserByTeamAndRole(role:string , team:string): Observable<any> {
    return this.http.get(API_URL + 'getLastEvaluationForEachUserByTeamAndRole/'+role+'/'+team);
  }

  getAllProfiles(): Observable<any> {
    return this.http.get(API_URL + 'getAllProfiles');
  }

  getAverageMarksForEachCompetencyPerCategoryPerUser(id:number)
  {
    return this.http.get(API_URL + 'getAverageMarksForEachCompetencyPerCategoryPerUser/'+id);
  }

}