import { Component, OnInit } from '@angular/core';
import { RegisterService } from '../services/register.service';
import { User } from '../modals/User';
import { LoginService } from '../services/login.service';
import { Router } from '@angular/router';
import { Role } from '../modals/Role';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  
  uniqueId:boolean;
  validUser:boolean;
  newUser:User = new User();

  constructor(private _regSer:RegisterService,private _loginSer:LoginService,private route:Router) {
   }

  ngOnInit() {
  }

  verifyMail(){
    this.uniqueId = true ;
  }
  
  //authenticate user 
  authenticateUser(){
    this._loginSer.loginUser(this.newUser).subscribe(
      (res)=>{
        console.log("response"+res)
        if(res){
        this.newUser = res as User;
        //localStorage.setItem('access_token', "successsudo1234567890");
        localStorage.setItem('access_token', "successsudo1234567890");
        console.log(this.newUser);
        //if user is simple user then 
        if(this.newUser.role.valueOf()==="USER"){
          let userObject = {"id":this.newUser.id,"role":this.newUser.role};
          localStorage.setItem('userObject',JSON.stringify(userObject)); //JSON.parse(userObject) to convert localstorage object to json object
          this.route.navigateByUrl("/user");
        }
        //if user is admin then
        else if(this.newUser.role.valueOf()==="ADMIN"){
          let userObject = {"id":this.newUser.id,"role":this.newUser.role};
          localStorage.setItem('userObject',JSON.stringify(userObject));
          this.route.navigateByUrl("/admin");
        }
        }
      }
      ,(err)=>{
        alert(err.error);
        console.log("error"+err)
        location.reload();
      });
  }

}

