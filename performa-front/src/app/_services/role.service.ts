import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Permission } from '../Interface/IPermission';
import { Role } from '../Interface/IRole';

const API_URL = 'http://localhost:8080/api/role/';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  constructor(private http: HttpClient) { }

  getAllRoles(): Observable<Role[]> {
    return this.http.get<Role[]>(API_URL + 'getAllRoles');
  }

  createRole(name:string,color:string,permissions:[]): Observable<any>
  {
    return this.http.post(API_URL + 'createRole',{name,color,permissions},httpOptions);
  }

  deleteRole(id:number)
  {
    return this.http.delete(API_URL+'deleteRole/'+id);
  }

  getAllPermissions(): Observable<Permission[]> {
    return this.http.get<Permission[]>(API_URL + 'getAllPermissions');
  }

  getRoleById(id:Number): Observable<Role> {
    return this.http.get<Role>(API_URL + 'getRoleById/'+id);
  }

  updateRole(role:any)
  {
    return this.http.post(API_URL+"updateRole",role);
  }

}
