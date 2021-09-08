import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthMessage } from 'src/app/common/auth-message';
import { MyAuthService } from 'src/app/service/my-auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public username: string;
  public password: string;
  public invalidLogin: boolean = false;
  public loginSuccess: boolean = false;

  constructor(private router: Router, private myAuthService: MyAuthService) { }

  ngOnInit(): void {

    /* if(this.myAuthService.isTokenExists()){
      this.router.navigate([`suppliers`]);
    }

    else {
      document.getElementById("SxXx123456789xXxS")?.click();
    } */

    if(!this.myAuthService.isTokenExists()){
      
      document.getElementById("SxXx123456789xXxS")?.click();
    }

    else{

      this.myAuthService.reauthenticate(this.myAuthService.getToken()).subscribe(
        (result: AuthMessage) => {
            
          this.router.navigate([`suppliers`]);

        }, 
        () => {
            
          this.myAuthService.logout();
          this.router.navigate([`login`]);

        }
      );   
    }
  }

  onLogin(): void {

    this.myAuthService.authenticate(this.username, this.password).subscribe(
      (result: AuthMessage) => {
          this.invalidLogin = false;
          this.loginSuccess = true;
          this.router.navigate([`suppliers`]);
      }, 
      () => {
          this.invalidLogin = true;
          this.loginSuccess = false;
      }
    );      
  }
}
