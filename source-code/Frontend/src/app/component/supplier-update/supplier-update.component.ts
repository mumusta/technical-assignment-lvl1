import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthMessage } from 'src/app/common/auth-message';
import { Supplier } from 'src/app/common/supplier';
import { MyAuthService } from 'src/app/service/my-auth.service';
import { SupplierService } from 'src/app/service/supplier.service';

@Component({
  selector: 'app-supplier-update',
  templateUrl: './supplier-update.component.html',
  styleUrls: ['./supplier-update.component.css']
})
export class SupplierUpdateComponent implements OnInit {

  public supplier: Supplier;
  public currentSupplierId: number;

  constructor(private supplierService: SupplierService, private route: ActivatedRoute, private router: Router, private myAuthService: MyAuthService) { }

  ngOnInit(): void {

    /* if(!this.myAuthService.isTokenExists()){
      this.router.navigate([`login`]);
    }

    else {
      this.route.paramMap.subscribe(

        () => {
  
          this.getSupplier();
        }
      );
    } */

    if(!this.myAuthService.isTokenExists()){
      
      this.router.navigate([`login`]);
    }

    else{

      this.myAuthService.reauthenticate(this.myAuthService.getToken()).subscribe(
        (result: AuthMessage) => {
            
          this.route.paramMap.subscribe(

            () => {
      
              this.getSupplier();
            }
          );

        }, 
        () => {
            
          this.myAuthService.logout();
          this.router.navigate([`login`]);

        }
      );   
    }
  }

  public getSupplier(): void {

    if(this.route.snapshot.paramMap.has('id')){
      this.currentSupplierId = +this.route.snapshot.paramMap.get('id')!;
    }
    else {
      this.currentSupplierId = 1; //???????
    }

    this.supplierService.getSupplier(this.currentSupplierId).subscribe(
      
      (response: Supplier) => {
        this.supplier = response;
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
      }
    );
  }

  public onUpdateSupplier(updateForm: NgForm): void{

    this.supplierService.updateSupplier(updateForm.value, this.currentSupplierId).subscribe(

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
