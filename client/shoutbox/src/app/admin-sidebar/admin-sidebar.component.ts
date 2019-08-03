import { Component, OnInit } from '@angular/core';
import { LoginService } from '../services/login.service';
import { UserDashboardService } from '../services/user-dashboard.service';
import { Router } from '@angular/router';
import { User } from '../modals/User';

@Component({
  selector: 'app-admin-sidebar',
  templateUrl: './admin-sidebar.component.html',
  styleUrls: ['./admin-sidebar.component.scss','../app.component.scss']
})
export class AdminSidebarComponent implements OnInit {

  // routing pending 
  currentUser:User = new User();
  currentUserId:number;

  constructor(private _userServ:UserDashboardService ,private _loginServ: LoginService, private _router:Router) { }

  ngOnInit() {
    if(("userObject" in localStorage) && ("access_token" in localStorage)){
      let currObj = JSON.parse(localStorage.getItem("userObject"));     
      if(currObj["role"] == "ADMIN"){
        this.currentUserId = currObj["id"];
        this._userServ.getCurrentUserById(this.currentUserId).subscribe(
          data => this.currentUser = data
        )
      }
      else{      
        this._router.navigate(['error']);
      }
    }
    else{
      this._router.navigate(['error']);
    }
  }

  logout(){
    if(confirm("Do you really want to Logout?")){
      alert("Sucessfully logout.");
      this._loginServ.logout();
      this._router.navigate([""]);
    }
  }
}
