import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DemoMaterialModule } from 'src/app/material.module';
import { FormsModule } from '@angular/forms';
import { EventDialogComponent } from './event-dialog/EventDialogComponent';
import { EventComponent } from './event.component';

@NgModule({
  declarations: [
    EventComponent,
    EventDialogComponent
  ],
  imports: [
   CommonModule,
   FormsModule,
   DemoMaterialModule
  ]
})
export class EventModule { }
