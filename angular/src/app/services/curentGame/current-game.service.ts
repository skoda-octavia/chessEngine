import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/app/config/config';

@Injectable({
  providedIn: 'root'
})
export class CurrentGameService {

  public getCurrentGame() {
    const authToken = window.localStorage.getItem('auth_token');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${authToken}`);
    const options = {
      headers: headers
    };
    return this.http.get(`${environment.apiUrl}/api/auth/current-game/get`, options);
  }

  public createGame() {
    const authToken = window.localStorage.getItem('auth_token');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${authToken}`);
    const options = {
      headers: headers
    };
    return this.http.post(`${environment.apiUrl}/api/auth/current-game/create`, null, options);
  }
  


  constructor(private http: HttpClient) { }
}
