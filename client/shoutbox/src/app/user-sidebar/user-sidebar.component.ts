import { Component, OnInit } from '@angular/core';
import { User } from '../modals/User';
import { UserDashboardService } from '../services/user-dashboard.service';
import { LoginService } from '../services/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-sidebar',
  templateUrl: './user-sidebar.component.html',
  styleUrls: ['./user-sidebar.component.scss']
})
export class UserSidebarComponent implements OnInit {

  currentUser: User = new User();
  currentUserId: number; // sesssion se value aayegi
  constructor(private _userServ: UserDashboardService, private _loginServ: LoginService, private _router: Router) { }

  ngOnInit() {
    if (("userObject" in localStorage) && ("access_token" in localStorage)) {

      let currObj = JSON.parse(localStorage.getItem("userObject"));

      if (currObj["role"] == "USER") {
        this.currentUserId = currObj["id"];
        this._userServ.getCurrentUserById(this.currentUserId).subscribe(
          data => this.currentUser = data
        )
        this.getCurrentUserById(this.currentUserId);
      }
      else {
        this._router.navigate(['error']);
      }
    } 
    else {
      this._router.navigate(['error']);
    }
  }

  getCurrentUserById(currentUserId: number) {
    this._userServ.getCurrentUserById(currentUserId).subscribe((data) => {
      this.currentUser = data;
      console.log(this.currentUser);
    });
  }


  logout() {
    if (confirm("Do you really want to Logout?")) {
      alert("Sucessfully logout.");
      this._loginServ.logout();
      this._router.navigate([""]);
    }
  }
}
