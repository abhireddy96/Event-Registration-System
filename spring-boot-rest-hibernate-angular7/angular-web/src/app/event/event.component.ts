import { Component, OnInit, ViewChild, ChangeDetectorRef } from '@angular/core';
import { MatSort, MatTableDataSource, MatPaginator } from '@angular/material';
import { MatDialogRef, MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { EventDialogComponent } from './event-dialog/EventDialogComponent';
import { HttpService } from '../services/http.service.';
import 'rxjs/add/operator/map';
import { Event } from './event.model';
import { Employee} from '../employee/employee.model';
import { trigger, state, style, transition, animate } from '@angular/animations';


@Component({
  selector: 'app-event',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({ height: '0px', minHeight: '0', visibility: 'hidden' })),
      state('expanded', style({ height: '*', visibility: 'visible' })),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class EventComponent implements OnInit {

  constructor(public dialog: MatDialog, public httpService: HttpService,
              public changeDetectorRefs: ChangeDetectorRef) { }

  events = new Array<Event>();
  employees = new Array<Employee>();
  dataSource: MatTableDataSource<Event>;
  displayedColumns: string[] = ['id', 'title', 'description', 'edit'];
  isLoadingResults = true;
  expandedElement: any;

  @ViewChild(MatSort) sort: MatSort;

  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit() {
    this.loadEvents();
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

  editEvent(row: Event): void {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.data = row;
    dialogConfig.width = '500px',
    dialogConfig.hasBackdrop = false;
    const dialogRef: MatDialogRef<EventDialogComponent> = this.dialog.open(EventDialogComponent, dialogConfig);
    dialogRef.componentInstance.dialogType = 'editDialog';
    dialogRef.afterClosed()
      .subscribe(res => {
        if (res !== false) {
          this.httpService.updateEvent(res)
            .subscribe(response => {
              console.log(response);
              this.loadEvents();
            },
              () => {
                this.loadEvents();
              },
              () => {
                this.loadEvents();
              }
            );
        }
      });
  }

  addEvent(): void {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.data = new Event();
    dialogConfig.width = '500px',
    dialogConfig.hasBackdrop = false;
    const dialogRef: MatDialogRef<EventDialogComponent> = this.dialog.open(EventDialogComponent, dialogConfig);
    dialogRef.componentInstance.dialogType = 'addDialog';
    dialogRef.afterClosed()
      .subscribe(res => {
        if (res !== false) {
          this.httpService.addEvent(res)
            .subscribe(response => {
              console.log(response);
              this.loadEvents();
            },
              () => {
                this.loadEvents();
              },
              () => {
                this.loadEvents();
              }
            );
        }
      });
  }

  deleteEvent(row: Event): void {

    const dialogConfig = new MatDialogConfig();
    dialogConfig.data = row;
    dialogConfig.hasBackdrop = false;

    const dialogRef: MatDialogRef<EventDialogComponent> = this.dialog.open(EventDialogComponent, dialogConfig);
    dialogRef.componentInstance.dialogType = 'deleteDialog';

    dialogRef.afterClosed()
      .subscribe(res => {
        if (res !== false) {
          this.httpService.deleteEvent(row.id)
            .subscribe(response => {
              console.log(response);
              this.loadEvents();
            },
              () => {
                this.loadEvents();
              },
              () => {
                this.loadEvents();
              }
            );
        }
      });
  }

  addEmployee(row: Event): void {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.data = row;
    dialogConfig.width = '500px',
    dialogConfig.hasBackdrop = false;
    const dialogRef: MatDialogRef<EventDialogComponent> = this.dialog.open(EventDialogComponent, dialogConfig);
    dialogRef.componentInstance.dialogType = 'addEmployeeDialog';
    dialogRef.componentInstance.employees = this.httpService.getAllEmployees();
    dialogRef.afterClosed()
      .subscribe(res => {
        if (res) {
          this.httpService.enrollEventEmployee(res.eventID, res.employeeID )
            .subscribe(response => {
              console.log(response);
              this.loadEvents();
            },
              () => {
                this.loadEvents();
              },
              () => {
                this.loadEvents();
              }
            );
        }
      });
  }

  loadEvents() {
    this.isLoadingResults = true;
    this.httpService.getAllEvents()
      .subscribe(response => {
        this.events = response;
        this.dataSource = new MatTableDataSource<Event>(this.events);
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

  getEventEmployee(row: Event) {
    this.httpService.getEventEmployee(row.id)
    .subscribe(response=> {
    this.employees= response;
    },
    () => {
      this.employees = null;
    }
    );
  }

}
