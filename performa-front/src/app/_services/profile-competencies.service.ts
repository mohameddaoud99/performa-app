import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const API_URL = 'http://localhost:8080/api/profileCompetencies/';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root',
})
export class ProfileCompetenciesService {
  constructor(private http: HttpClient) {}

  getAllGradeCompetencies(): Observable<any> {
    return this.http.get(API_URL + 'getAllGradeCompetencies');
  }

  getProfileCompetenciesByProfileName(profile_name :string) : Observable<any> {
    return this.http.get(API_URL + 'getProfileCompetenciesByName/'+profile_name);
  }

}