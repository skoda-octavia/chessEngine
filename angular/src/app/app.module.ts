import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { BoardComponent } from './components/board/board.component';
import { SquareComponent } from './components/square/square.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { AppRoutingModule } from './app-routing.module';
import { CardModule } from "primeng/card";
import { InputTextModule } from 'primeng/inputtext';
import { ReactiveFormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { TopbarComponent } from './components/topbar/topbar.component';
import { RegistrationService } from './services/registration/registration.service';
import { ConfirmEmailComponent } from './components/confirmEmail/confirm-email.component';
import { TokenConfirmationComponent } from './components/token-confirmation/token-confirmation.component';

@NgModule({
  declarations: [
    AppComponent,
    SquareComponent,
    BoardComponent,
    SquareComponent,
    LoginComponent,
    RegisterComponent,
    TopbarComponent,
    ConfirmEmailComponent,
    TokenConfirmationComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    CardModule,
    InputTextModule,
    ReactiveFormsModule,
    ButtonModule
  ],
  providers: [RegistrationService],
  bootstrap: [AppComponent],
  })
export class AppModule { }
