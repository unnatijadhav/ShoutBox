import { Component, OnInit } from '@angular/core';
import { AdminService } from '../services/admin.service';
import { User } from '../modals/User';
import { Router } from '@angular/router';
import { UserDashboardService } from '../services/user-dashboard.service';

@Component({
  selector: 'app-approveuser',
  templateUrl: './approveuser.component.html',
  styleUrls: ['./approveuser.component.scss','../app.component.scss']
})
export class ApproveuserComponent implements OnInit {
  
  inActiveUsers:User[];
  currentUser:User = new User();
  currentUserId:number;

  constructor(private service:AdminService, private _userServ:UserDashboardService, private _router:Router) { }

  ngOnInit() {
    if(("userObject" in localStorage) && ("access_token" in localStorage)){
      let currObj = JSON.parse(localStorage.getItem("userObject"));
      if(currObj["role"] == "ADMIN"){
        
        this.currentUserId = currObj["id"];
        this._userServ.getCurrentUserById(this.currentUserId).subscribe(
          data => this.currentUser = data
        )
        this.getInactiveUsers();
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
	 * GET INACTIVE USERS 
	 */
  getInactiveUsers(){
    this.service.getInactiveUsers().subscribe((resp)=>{
        this.inActiveUsers=resp;
    })
  }

  /**
	 * APPROVE NEWLY REGISTERED USERS 
	 */
  approveUsers(id:number){
      if(confirm("Do you really want to approve user.")){
        this.service.approveUsers(id).subscribe((resp)=>{
          console.log("Response = "+resp);
          alert("User with id "+id+" approved successfully !!!")     
          this._router.navigateByUrl('/RefrshComponent', {skipLocationChange: true}).then(()=>
          this._router.navigate(["approveusers"]));
        });
      }
  }
}
