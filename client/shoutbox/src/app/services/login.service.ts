import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../modals/User';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  
  baseUrl:string = "http://localhost:9092/user/login";

  constructor(private _http:HttpClient) { }

  loginUser(user:User):Observable<any>{
    return this._http.post(this.baseUrl,user);
  }

  public get loggedIn(): boolean{
    return localStorage.getItem('access_token') !==  null;
  }

  logout() {
    localStorage.removeItem('access_token');
    localStorage.removeItem('userObject');
  }

}
