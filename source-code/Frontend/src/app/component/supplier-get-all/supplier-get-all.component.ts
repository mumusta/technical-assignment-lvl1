import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthMessage } from 'src/app/common/auth-message';
import { Supplier } from 'src/app/common/supplier';
import { MyAuthService } from 'src/app/service/my-auth.service';
import { SupplierService } from 'src/app/service/supplier.service';

@Component({
  selector: 'app-supplier-get-all',
  templateUrl: './supplier-get-all.component.html',
  styleUrls: ['./supplier-get-all.component.css']
})
export class SupplierGetAllComponent implements OnInit {

  public suppliers: Supplier[];

  constructor(private supplierService: SupplierService, private myAuthService: MyAuthService, private router: Router){}

  ngOnInit(): void {

    if(!this.myAuthService.isTokenExists()){
      
      this.router.navigate([`login`]);
    }

    else{

      /* this.getAllSuppliers();
      document.getElementById("SxXx123456789xXxS")?.click(); */

      this.myAuthService.reauthenticate(this.myAuthService.getToken()).subscribe(
        (result: AuthMessage) => {
            
          this.getAllSuppliers();
          document.getElementById("SxXx123456789xXxS")?.click();

        }, 
        () => {
            
          this.myAuthService.logout();
          this.router.navigate([`login`]);

        }
      );   
    }
  }

  public getAllSuppliers(): void {

    this.supplierService.getAllSuppliers().subscribe(
      
      (response: Supplier[]) => {

        console.log(response);
        this.suppliers = response;
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
      }
    );
  }
}
