import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EmployeeComponent } from './employee.component';
import { EmployeeDialogComponent } from './employee-dialog/EmployeeDialogComponent';
import { DemoMaterialModule } from 'src/app/material.module';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    EmployeeComponent,
    EmployeeDialogComponent
  ],
  imports: [
   CommonModule,
   FormsModule,
   DemoMaterialModule
  ]
})
export class EmployeeModule { }
