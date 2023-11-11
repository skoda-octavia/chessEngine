import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/app/config/config';
import { Move } from 'src/app/interfaces/move';

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
    return this.http.get(`${environment.apiUrl}/api/current-game/get-current-game`, options);
  }

  public createNewGame() {
    const authToken = window.localStorage.getItem('auth_token');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${authToken}`);
    const options = {
      headers: headers
    };
    return this.http.post(`${environment.apiUrl}/api/current-game/create-new-game`, null, options);
  }

  public move(move: Move) {
    const authToken = window.localStorage.getItem('auth_token');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${authToken}`);
    const options = {
      headers: headers
    };
    return this.http.post(`${environment.apiUrl}/api/current-game/move`, move, options);
  } 
  


  constructor(private http: HttpClient) { }
}
