import { Injectable } from '@angular/core';
import {map, Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class SocialService {

  private baseUrl = 'http://localhost:8080/social/';
  constructor(private http: HttpClient) { }

  loginWithGoogle(token: any): Observable<any>{
    return this.http.post(`${this.baseUrl}google`, {token}).pipe(
      map(
        response => {
          return response;
        }
      )
    );
  }

  loginWithFacebook(token: any): Observable<any>{
    return this.http.post(`${this.baseUrl}facebook`, {token}).pipe(
      map(
        response => {
          return response;
        }
      )
    );
  }
}
