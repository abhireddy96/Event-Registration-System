import { Injectable } from '@angular/core';
import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate{

  constructor( private router: Router, private loginService: LoginService) {
  }

  isLogged:boolean = false;

 
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {

    this.loginService.loginStatus.subscribe(res => this.isLogged=res);

    if (this.isLogged) {
      return true;
    }

    this.router.navigateByUrl(
      this.router.createUrlTree(
        ['/login'], {
          queryParams: {
            return: state.url
          }
        }
      )
    );

    return false;
  }
}
