import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthMessage } from 'src/app/common/auth-message';
import { Supplier } from 'src/app/common/supplier';
import { MyAuthService } from 'src/app/service/my-auth.service';
import { SupplierService } from 'src/app/service/supplier.service';

@Component({
  selector: 'app-supplier-get-one',
  templateUrl: './supplier-get-one.component.html',
  styleUrls: ['./supplier-get-one.component.css']
})
export class SupplierGetOneComponent implements OnInit {

  public supplier: Supplier;
  public currentSupplierId: number;

  constructor(private supplierService: SupplierService, private route: ActivatedRoute, private myAuthService: MyAuthService, private router: Router){}

  ngOnInit(): void {

    /* if(!this.myAuthService.isTokenExists()){
      this.router.navigate([`login`]);
    }

    else{
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
}
