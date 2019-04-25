import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EmployeeComponent } from './employee/employee.component';
import { EventComponent } from './event/event.component';
import { LoginComponent } from './auth/login/login.component';
import { AuthGuardService } from './services/auth.guard.service';
import { HomeComponent } from './layout/home/home.component';

const routes: Routes = [
  {
    path : '',
    component: HomeComponent
  },
  {
    path : 'employee',
    component: EmployeeComponent,
    canActivate: [AuthGuardService] 
  },
  {
    path : 'event',
    component: EventComponent,
    canActivate: [AuthGuardService] 
  },
  {
    path : 'login',
    component: LoginComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
