import { Component, OnInit, ViewChild, ChangeDetectorRef } from '@angular/core';
import { MatSort, MatTableDataSource, MatPaginator } from '@angular/material';
import { MatDialogRef, MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { EmployeeDialogComponent } from './employee-dialog/EmployeeDialogComponent';
import { HttpService } from '../services/http.service.';
import 'rxjs/add/operator/map';
import { Employee } from './employee.model';
import { Event } from '../event/event.model';
import { trigger, state, style, transition, animate } from '@angular/animations';


@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({ height: '0px', minHeight: '0', visibility: 'hidden' })),
      state('expanded', style({ height: '*', visibility: 'visible' })),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class EmployeeComponent implements OnInit {

  constructor(public dialog: MatDialog, public httpService: HttpService,
              public changeDetectorRefs: ChangeDetectorRef) { }

  employees = new Array<Employee>();
  events = new Array<Event>();
  dataSource: MatTableDataSource<Employee>;
  displayedColumns: string[] = ['id', 'name', 'joinDate', 'emailID', 'edit'];
  isLoadingResults = true;
  expandedElement: any;

  @ViewChild(MatSort) sort: MatSort;

  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit() {
    this.loadEmployees();
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  isExpansionDetailRow(row: Object) {
    row.hasOwnProperty('detailRow');
  }

  editEmployee(row: Employee): void {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.data = row;
    dialogConfig.width = '500px',
    dialogConfig.hasBackdrop = false;
    const dialogRef: MatDialogRef<EmployeeDialogComponent> = this.dialog.open(EmployeeDialogComponent, dialogConfig);
    dialogRef.componentInstance.dialogType = 'editDialog';
    dialogRef.afterClosed()
      .subscribe(res => {
        if (res !== false) {
          console.log(res);
          this.httpService.updateEmployee(res)
            .subscribe(response => {
              console.log(response);
              this.loadEmployees();
            },
              () => {
                this.loadEmployees();
              },
              () => {
                this.loadEmployees();
              }
            );
        }
      });
  }

  addEmployee(): void {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.data = new Employee();
    dialogConfig.width = '500px',
    dialogConfig.hasBackdrop = false;
    const dialogRef: MatDialogRef<EmployeeDialogComponent> = this.dialog.open(EmployeeDialogComponent, dialogConfig);
    dialogRef.componentInstance.dialogType = 'addDialog';
    dialogRef.afterClosed()
      .subscribe(res => {
        if (res !== false) {
          this.httpService.addEmployee(res)
            .subscribe(response => {
              console.log(response);
              this.loadEmployees();
            },
              () => {
                this.loadEmployees();
              },
              () => {
                this.loadEmployees();
              }
            );
        }
      });
  }

  deleteEmployee(row: Employee): void {

    const dialogConfig = new MatDialogConfig();
    dialogConfig.data = row;
    dialogConfig.hasBackdrop = false;

    const dialogRef: MatDialogRef<EmployeeDialogComponent> = this.dialog.open(EmployeeDialogComponent, dialogConfig);
    dialogRef.componentInstance.dialogType = 'deleteDialog';

    dialogRef.afterClosed()
      .subscribe(res => {
        if (res !== false) {
          this.httpService.deleteEmployee(row.id)
            .subscribe(response => {
              console.log(response);
              this.loadEmployees();
            },
              () => {
                this.loadEmployees();
              },
              () => {
                this.loadEmployees();
              }
            );
        }
      });
  }

  addEvent(row: Employee): void {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.data = row;
    dialogConfig.width = '500px',
    dialogConfig.hasBackdrop = false;
    const dialogRef: MatDialogRef<EmployeeDialogComponent> = this.dialog.open(EmployeeDialogComponent, dialogConfig);
    dialogRef.componentInstance.dialogType = 'addEventDialog';
    dialogRef.componentInstance.events = this.httpService.getAllEvents();
    dialogRef.afterClosed()
      .subscribe(res => {
        if (res) {
          this.httpService.enrollEmployeeEvent(res.employeeID, res.eventID)
            .subscribe(response => {
              console.log(response);
              this.loadEmployees();
            },
              () => {
                this.loadEmployees();
              },
              () => {
                this.loadEmployees();
              }
            );
        }
      });
  }

  loadEmployees() {
    this.isLoadingResults = true;
    this.httpService.getAllEmployees()
      .subscribe(response => {
        this.employees = response;
        this.dataSource = new MatTableDataSource<Employee>(this.employees);
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
        this.changeDetectorRefs.detectChanges();
        this.isLoadingResults = false;
      },
      () => {
        this.isLoadingResults = true;
      }
      );
  }

  getEmployeeEvent(row: Employee) {
    this.httpService.getEmployeeEvent(row.id)
    .subscribe(response=> {
    this.events = response;
    },
    () => {
      this.events = null;
    }
    );
  }

}
