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
    console.log(`${this.apiUrl}/api/registration/register`)
    return this.http.post<RegistrationRequest>(`${this.apiUrl}/api/registration/register`, registrationRequest).subscribe(
      (response) => {
        console.log(response);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
  
  constructor(private http: HttpClient) {  }
}
