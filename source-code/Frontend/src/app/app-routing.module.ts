import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './component/login/login.component';
import { SupplierAddComponent } from './component/supplier-add/supplier-add.component';
import { SupplierDeleteComponent } from './component/supplier-delete/supplier-delete.component';
import { SupplierGetAllComponent } from './component/supplier-get-all/supplier-get-all.component';
import { SupplierGetOneComponent } from './component/supplier-get-one/supplier-get-one.component';
import { SupplierUpdateComponent } from './component/supplier-update/supplier-update.component';

const routes: Routes = [
  { path: 'suppliers/update/:id', component: SupplierUpdateComponent, pathMatch: 'full' },
  { path: 'suppliers/delete/:id', component: SupplierDeleteComponent, pathMatch: 'full' },
  { path: 'suppliers/add', component: SupplierAddComponent, pathMatch: 'full' },
  { path: 'suppliers/:id', component: SupplierGetOneComponent, pathMatch: 'full' },
  { path: 'suppliers', component: SupplierGetAllComponent, pathMatch: 'full' },
  { path: 'login', component: LoginComponent, pathMatch: 'full' },
  { path: 'logout', redirectTo: '/login', pathMatch: 'full' },
  { path: '', redirectTo: '/suppliers', pathMatch: 'full' },
  { path: '**', redirectTo: '/suppliers', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
