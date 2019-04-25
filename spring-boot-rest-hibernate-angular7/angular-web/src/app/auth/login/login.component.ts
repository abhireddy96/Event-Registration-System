import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatDialog, MatDialogConfig, MatDialogRef } from '@angular/material';
import { HttpService } from 'src/app/services/http.service.';
import { LoginDialogComponent } from './login-dialog/login-dialog.component';
import { Router, Route, ActivatedRoute } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(public dialog: MatDialog,
    public httpService: HttpService,
    public changeDetectorRefs: ChangeDetectorRef,
    public router: Router,
    public loginService: LoginService,
    private route: ActivatedRoute) { }

    return :string ='';

  ngOnInit() {

    this.route.queryParams
      .subscribe(params => {this.return = params['return'];});
  }

  login(form: NgForm) {
    if (form.value.userName == 'admin' && form.value.password == 'admin') {
      this.loginService.setLogin(true);
      this.loginSuccess();
      
    }
    else {
      this.loginService.setLogin(false);
      this.loginFailure();
    }
  }

  logout() {
      this.loginService.setLogout(false);
      this.logoutSuccess();
  }

  loginSuccess() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = '500px',
      dialogConfig.hasBackdrop = true;
    const dialogRef: MatDialogRef<LoginDialogComponent> = this.dialog.open(LoginDialogComponent, dialogConfig);
    dialogRef.componentInstance.dialogType = 'loginSuccessDialog';
    dialogRef.afterClosed().subscribe(res=>{this.router.navigateByUrl(this.return);});
    
  }

  logoutSuccess() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = '500px',
      dialogConfig.hasBackdrop = true;
    const dialogRef: MatDialogRef<LoginDialogComponent> = this.dialog.open(LoginDialogComponent, dialogConfig);
    dialogRef.componentInstance.dialogType = 'logoutSuccessDialog';
    dialogRef.afterClosed().subscribe(res=>{this.router.navigateByUrl(this.return);});
    
  }

  loginFailure() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = '500px',
      dialogConfig.hasBackdrop = true;
    const dialogRef: MatDialogRef<LoginDialogComponent> = this.dialog.open(LoginDialogComponent, dialogConfig);
    dialogRef.componentInstance.dialogType = 'loginFailureDialog';
    dialogRef.afterClosed().subscribe(res=>{ this.router.navigate(['/']);});
   
  }

}
