import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Employee } from '../employee/employee.model';
import { Event } from '../event/event.model';

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  apiURL = 'http://localhost:8080';

  constructor(private httpClient: HttpClient) {}

  public getAllEmployees(): Observable<Employee[]> {

    return this.httpClient.get<Employee[]>(this.apiURL + '/employee');
  }

  public deleteEmployee(employeeID: string): Observable<string> {

    return this.httpClient.delete<string>(this.apiURL + '/employee/' + employeeID);
  }

  public updateEmployee(employee: Employee): Observable<string> {

    return this.httpClient.put<string>(this.apiURL + '/employee/' + employee.id, employee);
  }

  public addEmployee(employee: Employee): Observable<string> {

    return this.httpClient.post<string>(this.apiURL + '/employee', employee);
  }

  public enrollEmployeeEvent(employeeID: string, eventID: number): Observable<string> {

    return this.httpClient.post<string>(this.apiURL + '/employee/' + employeeID  + '/event/' + eventID,  '');
  }

  public getEmployeeEvent(employeeID: string): Observable<Event[]> {

    return this.httpClient.get<Event[]>(this.apiURL + '/employee/' + employeeID  + '/event');
  }


  public getAllEvents(): Observable<Event[]> {

    return this.httpClient.get<Event[]>(this.apiURL + '/event');
  }

  public deleteEvent(eventID: number): Observable<string> {

    return this.httpClient.delete<string>(this.apiURL + '/event/' + eventID);
  }

  public updateEvent(event: Event): Observable<string> {

    return this.httpClient.put<string>(this.apiURL + '/event/' + event.id, event);
  }

  public addEvent(event: Event): Observable<string> {

    return this.httpClient.post<string>(this.apiURL + '/event', event);
  }

  public enrollEventEmployee(eventID: number, employeeID: string): Observable<string> {

    return this.httpClient.post<string>(this.apiURL + '/event/' + eventID  + '/employee/' + employeeID,  '');
  }

  public getEventEmployee(eventID: number): Observable<Employee[]> {

    return this.httpClient.get<Employee[]>(this.apiURL + '/event/' + eventID  + '/employee');
  }
}
