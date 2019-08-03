import { Injectable } from '@angular/core';
import { User } from '../modals/User';
import { Role } from '../modals/Role';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {
  
  url:string="http://localhost:9092/user/";
  
  constructor(private _http: HttpClient) { }
  
  registerUser(new_user:User){
    new_user.isUserActive = true;
    new_user.role = Role.USER;
    return this._http.post(this.url+"register",new_user);
  }
  verifyMail(email: string) {
    return this._http.post(this.url+"check-email",email);
  }

}
