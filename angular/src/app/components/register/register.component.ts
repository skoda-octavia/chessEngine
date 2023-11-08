import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { RegistrationRequest } from 'src/app/interfaces/registration';
import { RegistrationService } from 'src/app/services/registration/registration.service';
import { passwordMatchValidator } from 'src/app/shared/password-match.directive';



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

  constructor(private fb: FormBuilder, private registrationService: RegistrationService) {}

  submitDetails() {
    const request: RegistrationRequest = {
      username: "John",
      email: "Doe@gmail.com",
      password: "qqqwww"
    };
    this.registrationService.register(request);

  }
}
