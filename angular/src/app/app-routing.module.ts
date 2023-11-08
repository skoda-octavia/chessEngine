import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { BoardComponent } from './components/board/board.component';
import { ConfirmEmailComponent } from './components/confirmEmail/confirm-email.component';
import { TokenConfirmationComponent } from './components/token-confirmation/token-confirmation.component';


const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'home',
    component: BoardComponent
  },
  {
    path: '',
    component: LoginComponent
  },
  {
    path: 'confirmEmail',
    component: ConfirmEmailComponent
  },
  {
    path: 'confirm',
    component: TokenConfirmationComponent
  }

]


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }
