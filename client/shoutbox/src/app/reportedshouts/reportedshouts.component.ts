import { Component, OnInit } from '@angular/core';
import { AdminService } from '../services/admin.service';
import { Shout, ShoutText } from '../modals/Shouts';
import { Router } from '@angular/router';
import { User } from '../modals/User';
import { UserDashboardService } from '../services/user-dashboard.service';

@Component({
  selector: 'app-reportedshouts',
  templateUrl: './reportedshouts.component.html',
  styleUrls: ['./reportedshouts.component.scss', '../app.component.scss']
})
export class ReportedshoutsComponent implements OnInit {
  
 // reportedShouts:Shout[];
  currentUser:User = new User();
  currentUserId:number;
  userTextShouts: ShoutText[];
  userBlobShouts: Shout[];

  constructor(private  service:AdminService, private _userServ: UserDashboardService ,private _router:Router) { }

  ngOnInit() {
    if(("userObject" in localStorage) && ("access_token" in localStorage)){
      
      let currObj = JSON.parse(localStorage.getItem("userObject"));
      if(currObj["role"] == "ADMIN"){
        this.currentUserId = currObj["id"];
        this._userServ.getCurrentUserById(this.currentUserId).subscribe(
          data => this.currentUser = data
        );
        this.getReportedShouts();
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
	 * GET REPORTED SHOUTS (ABHIJIT ROKADE)
	 */
  getReportedShouts(){
    console.log("getReportedShouts()");
    this.service.getReportedShouts().subscribe(
      (response) => {
        console.log(response);
        if (response != undefined) {
          if(response["blobShouts"] === undefined){
            this.userBlobShouts = response["textShouts"];
          } 
          else{
            this.userTextShouts = response["textShouts"];
            this.userBlobShouts = response["blobShouts"];
            this.userTextShouts.forEach((element) =>
              this.userBlobShouts.push(element)
            )
          }

        } else
          alert("error occured");
      })
  }

  /**
	 * GET REPORTED SHOUTS (PRASHANT SHARMA)
	 */
  deleteReportedShout(id:number){
      if(confirm("Do you really want to delete this shout.")){
      this.service.deleteShouts(id).subscribe((resp)=>{
        console.log("Response = "+resp);
        alert("Shout with id "+id+" deleted successfully !!!")
        this._router.navigateByUrl('/RefrshComponent', {skipLocationChange: true}).then(()=>
        this._router.navigate(["reported"]));
      });
    }
  }
}
