import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MyAuthService } from 'src/app/service/my-auth.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  public isLoggedIn: boolean = false;

  constructor(private router: Router,
    private myAuthService: MyAuthService) { }

  ngOnInit() {
    
    this.isLoggedIn = this.myAuthService.isTokenExists();
    console.log('Menu check if logged in: ' + this.isLoggedIn);
  }

  handleLogout() {
    
    this.myAuthService.logout();
    this.router.navigate([`login`]);
  }

  clickMe(){

    this.ngOnInit();
  }
}
