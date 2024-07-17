import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const API_URL = 'http://localhost:8080/api/user/';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};


@Injectable({
  providedIn: 'root'
})
export class EmailService {

 
  constructor(private http: HttpClient) { }

  sendFilteredEmails(emails: string[]): Observable<any> {

    console.log(emails);
    return this.http.post(API_URL+ 'notifyUsers',  emails,httpOptions);
  }
}