import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/app/config/config';
import { RegistrationRequest } from 'src/app/interfaces/registration'


@Injectable({
  providedIn: 'root'
})
export class RegistrationService {


  public register(registrationRequest: RegistrationRequest) {
    console.log(registrationRequest)
    return this.http.post<RegistrationRequest>(`${environment.apiUrl}/api/auth/register`, registrationRequest)
  }
  
  constructor(private http: HttpClient) {  }
}
