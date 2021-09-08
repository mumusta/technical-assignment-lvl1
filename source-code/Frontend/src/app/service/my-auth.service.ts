import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { map } from 'rxjs/operators';
import { AuthMessage } from '../common/auth-message';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MyAuthService { 

  public username: string;
  public password: string;
  public baseUrl = environment.baseUrl;



  constructor(private httpClient: HttpClient) { }

  /* public login(username: string, password: string){

    const headers = new HttpHeaders( { Authorization: 'Basic ' + btoa(username + ":" + password) } );
    this.httpClient.get<void>(`${this.baseUrl}/xxx`, { headers, responseType: 'text' as 'json' });
  } */


  authenticate(username: string, password: string): Observable<AuthMessage> {
    
    return this.httpClient.get<AuthMessage>(`${this.baseUrl}/basic_auth`,
                               { headers: { Authorization: 'Basic ' + btoa(username + ":" + password) } } )
      .pipe(
        map(
          (result: AuthMessage) => {
            this.username = username;
            this.password = password;
            this.login(username, password);
            return result;
          }
        )
      );
  }

  reauthenticate(myToken: string): Observable<AuthMessage> {
    
    return this.httpClient.get<AuthMessage>(`${this.baseUrl}/basic_auth`,
                               { headers: { Authorization: 'Basic ' + myToken } } )
      .pipe(
        map(
          (result: AuthMessage) => {
            return result;
          }
        )
      );
  }

  login(username: string, password: string): void {
    
    localStorage.setItem("my_token", btoa(username + ":" + password));
  }

  logout(): void {
    
    localStorage.removeItem("my_token");
    this.username = this.password = "";
  }

  isTokenExists(): boolean {
    
    let user = localStorage.getItem("my_token");
    if (user === null) return false;
    else return true;
  }

  getToken(): string {
    
    let user = localStorage.getItem("my_token");
    if (user === null) return "";
    else return user;
  }
}

