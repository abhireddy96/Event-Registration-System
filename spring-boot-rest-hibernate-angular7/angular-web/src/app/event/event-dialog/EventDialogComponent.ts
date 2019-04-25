import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Component, Inject } from '@angular/core';
import { Event } from '../event.model';
import { NgForm } from '@angular/forms';
import { Observable } from 'rxjs';
import { HttpService } from '../../services/http.service.';
import { Employee } from '../../employee/employee.model';

@Component({
    selector: 'app-event-dialog',
    templateUrl: './event.popup-dialog.html',
    styleUrls: ['./event.popup-dialog.css']
})
export class EventDialogComponent {
    constructor(public dialogRef: MatDialogRef<EventDialogComponent>,
        @Inject(MAT_DIALOG_DATA) public event: Event) { }

    dialogType: string;
    employees: Observable<Employee[]>;;

    onNoClick(): void {
        this.dialogRef.close(false);
    }

    onEditConfirm(form: NgForm) {
        this.dialogRef.close(form.value);
    }

    onAddConfirm(form: NgForm) {
        this.dialogRef.close(form.value);
    }

    onDeleteConfirm() {
        this.dialogRef.close(true);
    }

    onAddevent(form: NgForm) {
        this.dialogRef.close(form.value);
    }
}
