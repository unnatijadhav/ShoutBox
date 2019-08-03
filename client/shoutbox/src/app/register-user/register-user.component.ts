import { Component, OnInit } from '@angular/core';
import { User } from '../modals/User';
import { RegisterService } from '../services/register.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.scss']
})
export class RegisterUserComponent implements OnInit {

  newuser:User = new User();
  pass:string;
  samePass:boolean
  uniqueId:boolean;

  constructor(private _regServ: RegisterService,private _router:Router) { }

  ngOnInit() {
  }
  
  // save to the DB
  registerNewUser(){
    console.log(this.newuser);
    
    this._regServ.registerUser(this.newuser).subscribe(
      Response =>{ 
        if(Response != "User registration failed"){
          alert("Registration Successfull. Your request has been sent to Admin");
          this._router.navigate(['/login']);
        }
        else{
          alert("Registration Failure. Try again in some time");
          this._router.navigate(['/register']);
        }
      }
    );
  }

  // check both passwords are same or not
  checkPasswords(){
    if(this.pass === this.newuser.password)
      this.samePass = true;
    else
      this.samePass = false;
  }

  verifyMail(){
    this._regServ.verifyMail(this.newuser.email).subscribe(
      Response => {
        if(Response == "FOUND")
          this.uniqueId = false;
        else 
          this.uniqueId = true;
      }
    );
  }

}
