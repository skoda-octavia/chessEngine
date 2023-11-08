import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { RegistrationRequest } from 'src/app/interfaces/registration';
import { RegistrationService } from 'src/app/services/registration/registration.service';
import { passwordMatchValidator } from 'src/app/shared/password-match.directive';
import { HttpClient, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Router } from '@angular/router';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {



  registerForm = this.fb.group({
    login: ['', [Validators.required, Validators.pattern(/^[a-zA-Z]+(?: [a-zA-Z]+)*$/)]],
    email: ['', [Validators.required, Validators.email]],
    password: ['', Validators.required],
    confirmPassword: ['', Validators.required]
  }, {
    validators: passwordMatchValidator
  })

  constructor(private fb: FormBuilder, private registrationService: RegistrationService, private router: Router) {}

  submitDetails() {  
    const request: RegistrationRequest = {
      username: this.registerForm.get('login')?.value || '',
      email: this.registerForm.get('email')?.value || '',
      password: this.registerForm.get('password')?.value || ''
    };
    this.registrationService.register(request).subscribe(
      (response: any) => {
        if (response.status === 0) {
          console.log(response.message)
          this.router.navigate(['/confirmEmail']);
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
