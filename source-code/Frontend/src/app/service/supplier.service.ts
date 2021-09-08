import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Supplier } from '../common/supplier';

@Injectable({
  providedIn: 'root'
})
export class SupplierService {

  private baseUrl = environment.baseUrl;

  constructor(private httpClient: HttpClient) { }

  public getSupplier(supplierId: number): Observable<Supplier> {
    return this.httpClient.get<Supplier>(`${this.baseUrl}/suppliers/${supplierId}`);
  }

  /*public getAllSuppliers(): Observable<Supplier[]> {
    return this.httpClient.get<Supplier[]>(`${this.baseUrl}/suppliers`);
  }*/

  public getAllSuppliers(): Observable<Supplier[]> {
    return this.httpClient.get<GetResponse>(`${this.baseUrl}/suppliers`).pipe(
      map(response => response.content)
    );
  }

  public addSupplier(supplier: Supplier): Observable<Supplier> {
    return this.httpClient.post<Supplier>(`${this.baseUrl}/suppliers`, supplier);
  }

  public updateSupplier(supplier: Supplier, supplierId: number): Observable<Supplier> {
    return this.httpClient.put<Supplier>(`${this.baseUrl}/suppliers/${supplierId}`, supplier);
  }

  public deleteSupplier(supplierId: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.baseUrl}/suppliers/${supplierId}`);
  }
}


interface GetResponse {

  content: Supplier[];
}