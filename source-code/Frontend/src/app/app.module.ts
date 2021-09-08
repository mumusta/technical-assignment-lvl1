import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SupplierService } from './service/supplier.service';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { SupplierGetAllComponent } from './component/supplier-get-all/supplier-get-all.component';
import { SupplierGetOneComponent } from './component/supplier-get-one/supplier-get-one.component';
import { SupplierAddComponent } from './component/supplier-add/supplier-add.component';
import { SupplierDeleteComponent } from './component/supplier-delete/supplier-delete.component';
import { SupplierUpdateComponent } from './component/supplier-update/supplier-update.component';
import { FormsModule } from '@angular/forms';
import { MyInterceptorService } from './service/my-interceptor.service';
import { LoginComponent } from './component/login/login.component';
import { MyAuthService } from './service/my-auth.service';
import { MenuComponent } from './component/menu/menu.component';

@NgModule({
  declarations: [ 
    AppComponent, 
    SupplierGetAllComponent, 
    SupplierGetOneComponent, 
    SupplierAddComponent, 
    SupplierDeleteComponent, 
    SupplierUpdateComponent, 
    LoginComponent, 
    MenuComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    SupplierService,
    MyAuthService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: MyInterceptorService,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
