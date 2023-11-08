import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class TokenService {
  public getTokenFromURL(url: string): string | null {
    const urlSearchParams = new URLSearchParams(url);
    return urlSearchParams.get('token');
  }

  private apiUrl: String = 'http://localhost:8080'

  public confirmToken(token: String) {
    const body = { token: token };
    return this.http.post(`${this.apiUrl}/api/registration/confirm`, body)
  }
  
  constructor(private http: HttpClient) {  }


}