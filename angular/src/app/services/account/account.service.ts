import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private apiServerUrl = '';

  constructor(private http: HttpClient) { }

  public getAccounts(): Observable<any> {
    return this.http.get<any>(`${this.apiServerUrl}/api/account/all`)
  }



}
