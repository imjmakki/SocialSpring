import { Injectable } from '@angular/core';
import {map, Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class SocialService {

  private baseUrl = 'http://localhost:8080/social/';
  constructor(private http: HttpClient) { }

  loginWithGoogle(token: string): Observable<any>{
    return this.http.post<any>(`${this.baseUrl}google`, {token}).pipe(
      map(
        response => {
          sessionStorage.setItem('token','Bearer ' + response.token);
          return response;
        }
      )
    );
  }
  loginWithFacebook(token: string): Observable<any>{
    return this.http.post<any>(`${this.baseUrl}facebook`, {token}).pipe(
      map(
        response => {
          sessionStorage.setItem('token',response.token);
          return response;
        }
      )
    );
  }
}
