import { Component, OnInit } from '@angular/core';
import { AdminService } from '../services/admin.service';
import { User } from '../modals/User';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { UserDashboardService } from '../services/user-dashboard.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss','../app.component.scss']
})
export class AdminComponent implements OnInit {
  
  activeUsers:User[];
  currentUser:User = new User();
  currentUserId:number;


  constructor(private _userServ:UserDashboardService ,private service:AdminService,private _router:Router) { }

  ngOnInit() {
    if(("userObject" in localStorage) && ("access_token" in localStorage)){
      let currObj = JSON.parse(localStorage.getItem("userObject"));
      
      if(currObj["role"] == "ADMIN"){
        this.currentUserId = currObj["id"];
        this._userServ.getCurrentUserById(this.currentUserId).subscribe(
          data => this.currentUser = data
        )
        this.getActiveUsers();
        console.log(this.activeUsers);
        
      }
      else{
        this._router.navigate(['error']);
      }
    }
    else{
      this._router.navigate(['error']);
    }
    
  }

  /**
	 * GET ACTIVE USERS
	 */
  getActiveUsers(){
    console.log("inside get");
    this.service.getActiveUsers().subscribe((resp)=>{
      console.log(this.activeUsers)
      this.activeUsers=resp;
      console.log("Active users"+resp);
      console.log("active"+this.activeUsers)

    });
  }
}
