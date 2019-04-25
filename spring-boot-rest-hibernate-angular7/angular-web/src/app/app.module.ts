import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FlexLayoutModule } from '@angular/flex-layout';
import {HttpClientModule} from '@angular/common/http';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DemoMaterialModule } from 'src/app/material.module';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { EmployeeDialogComponent } from './employee/employee-dialog/EmployeeDialogComponent';
import { MAT_DIALOG_DEFAULT_OPTIONS } from '@angular/material';
import { HttpService } from './services/http.service.';
import { FormsModule } from '@angular/forms';
import { EventDialogComponent } from './event/event-dialog/EventDialogComponent';
import { EmployeeModule } from './employee/employee.module';
import { EventModule } from './event/event.module';
import { LayoutModule } from './layout/layout.module';
import { AuthModule } from './auth/auth.module';
import { LoginDialogComponent } from './auth/login/login-dialog/login-dialog.component';
import { AuthGuardService } from './services/auth.guard.service';
import { LoginService } from './services/login.service';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FlexLayoutModule,
    DemoMaterialModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    EmployeeModule,
    EventModule,
    LayoutModule,
    AuthModule
  ],
  providers: [
    {provide: MAT_DIALOG_DEFAULT_OPTIONS, useValue: {hasBackdrop: false}},
    HttpService,
    AuthGuardService,
    LoginService
  ],
  bootstrap: [AppComponent],
  entryComponents: [EmployeeDialogComponent,EventDialogComponent,LoginDialogComponent]
})
export class AppModule { }
