import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthenticationRequest } from 'src/app/interfaces/authenticationRequest';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl: String = 'http://localhost:8080'

  public authenticate(authRequest: AuthenticationRequest) {
    console.log(authRequest)
    return this.http.post<AuthenticationRequest>(`${this.apiUrl}/api/auth/authenticate`, authRequest)
  }
  
  constructor(private http: HttpClient) {  }
}
