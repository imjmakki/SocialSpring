import { Injectable } from '@angular/core';
import {map, Observable} from "rxjs";
import {Student} from "../model/student";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  constructor(private http: HttpClient) { }

  getStudents(): Observable<Student[]>{
    return this.http.get<Student[]>('http://localhsot:8080/student/all').pipe(
      map(
        response => {
          return response;
        }
      )
    );
  }
}
