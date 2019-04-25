import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Component, Inject } from '@angular/core';
import { Employee } from '../employee.model';
import { NgForm } from '@angular/forms';
import { Observable } from 'rxjs';
import { HttpService } from '../../services/http.service.';
import { Event } from '../../event/event.model';


@Component({
    selector: 'app-employee-dialog',
    templateUrl: './employee.popup-dialog.html',
    styleUrls: ['./employee.popup-dialog.css']
})
export class EmployeeDialogComponent {
    constructor(public dialogRef: MatDialogRef<EmployeeDialogComponent>,
                @Inject(MAT_DIALOG_DATA) public employee: Employee){}

    dialogType: string;
    events: Observable<Event[]>;

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
