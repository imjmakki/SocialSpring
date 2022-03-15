import { Injectable } from '@angular/core';
import {map, Observable} from "rxjs";
import {Student} from "../model/student";
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  constructor(private http: HttpClient) { }

  getStudents(): Observable<Student[]>{
    // @ts-ignore
    let head= new HttpHeaders().set("Authorization", sessionStorage.getItem('token'));
    return this.http.get<Student[]>("http://localhost:8080/student/all",{headers: head}).pipe(
      map(
        response => {
          return response;
        }
      )
    );
  }
}
