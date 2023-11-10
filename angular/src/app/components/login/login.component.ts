import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationRequest } from 'src/app/interfaces/authenticationRequest';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  
  loginForm = this.fb.group({
    login: ['', [Validators.required]],
    password: ['', Validators.required]
  })

  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router) { }


  submitDetails() {
    console.log("fsdsad")
    const request: AuthenticationRequest = {
      username: this.loginForm.get('login')?.value || '',
      password: this.loginForm.get('password')?.value || ''
    };
    this.authService.authenticate(request).subscribe(
      (response: any) => {
        if (response.status === 0) {
          console.log(response.message)
          window.localStorage.setItem("auth_token", response.token);
          this.router.navigate(['/home']);
        }
        else {
          alert(response.message)
        }
      
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
      }
    );;

  }

}
