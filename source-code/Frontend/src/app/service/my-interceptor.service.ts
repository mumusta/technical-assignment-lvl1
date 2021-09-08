import { Injectable } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MyAuthService } from './my-auth.service';

@Injectable({
  providedIn: 'root'
})
export class MyInterceptorService implements HttpInterceptor{

  constructor(private myAuthService: MyAuthService) { }

  intercept(req: HttpRequest<any>, 
            next: HttpHandler): Observable<HttpEvent<any>> {
    
    const myToken = localStorage.getItem("my_token");

    if (myToken && this.myAuthService.isTokenExists() && (req.url.indexOf('basic_auth') === -1)){

      console.log("Interceptor Activated: " + myToken + 
      " " + req.url + " " + req.url.indexOf('basic_auth') + 
      " " + (req.url.indexOf('basic_auth') === -1));

      //This can be changed to variety of auth types.
      const cloned = req.clone({
        headers: req.headers.set("Authorization", "Basic " + myToken)
      });

      return next.handle(cloned);
    }
    else {

      return next.handle(req);
    }
  }
}
