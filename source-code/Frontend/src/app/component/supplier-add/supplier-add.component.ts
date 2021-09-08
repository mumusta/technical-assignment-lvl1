import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthMessage } from 'src/app/common/auth-message';
import { Supplier } from 'src/app/common/supplier';
import { MyAuthService } from 'src/app/service/my-auth.service';
import { SupplierService } from 'src/app/service/supplier.service';

@Component({
  selector: 'app-supplier-add',
  templateUrl: './supplier-add.component.html',
  styleUrls: ['./supplier-add.component.css']
})
export class SupplierAddComponent implements OnInit {

  constructor(private supplierService: SupplierService, private router: Router, private myAuthService: MyAuthService) { }

  ngOnInit(): void {
    
    /* if(!this.myAuthService.isTokenExists()){
      this.router.navigate([`login`]);
    } */

    if(!this.myAuthService.isTokenExists()){
      
      this.router.navigate([`login`]);
    }

    else{

      this.myAuthService.reauthenticate(this.myAuthService.getToken()).subscribe(
        (result: AuthMessage) => {
            
          //Do stuff

        }, 
        () => {
            
          this.myAuthService.logout();
          this.router.navigate([`login`]);

        }
      );   
    }
  }

  public onAddSupplier(addForm: NgForm): void{

    this.supplierService.addSupplier(addForm.value).subscribe(

      (response: Supplier) => {
        console.log(response);
        this.router.navigate([`suppliers/${response.supplierId}`]);
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
      }
    );
  }
}
