import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { RegistrationRequest } from 'src/app/interfaces/registration'


@Injectable({
  providedIn: 'root'
})
export class RegistrationService {
  private apiUrl: String = 'http://localhost:8080'

  public register(registrationRequest: RegistrationRequest) {
    console.log(registrationRequest)
    return this.http.post<RegistrationRequest>(`${this.apiUrl}/api/auth/register`, registrationRequest)
  }
  
  constructor(private http: HttpClient) {  }
}
