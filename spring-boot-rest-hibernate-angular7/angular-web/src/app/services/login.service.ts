import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor() { }

  public loginStatus = new Subject<any>();
  Logged: boolean=false;
  

  public setLogin(logInStatus:boolean){
    this.Logged=logInStatus;
    this.loginStatus.next(this.Logged);
  }

  public setLogout(logInStatus:boolean){
    this.Logged=logInStatus;
    this.loginStatus.next(this.Logged);
  }

}
