import { Component, OnInit } from '@angular/core';
import { AdminService } from '../services/admin.service';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { User } from '../modals/User';
import { Shout, ShoutText } from '../modals/Shouts';
import { UserDashboardService } from '../services/user-dashboard.service';

@Component({
  selector: 'app-viewshouts',
  templateUrl: './viewshouts.component.html',
  styleUrls: ['./viewshouts.component.scss', '../app.component.scss']
})
export class ViewshoutsComponent implements OnInit {
  id: number;
  user: User = new User();
  users: User[];
  shouts: Shout[];
  userTextShouts: ShoutText[];
  userBlobShouts: Shout[];


  constructor(private service: AdminService, private _userServ:UserDashboardService, private _route: ActivatedRoute, private _router: Router) { }

  ngOnInit() {
    if (("userObject" in localStorage) && ("access_token" in localStorage)) {
      let currObj = JSON.parse(localStorage.getItem("userObject"));
      if (currObj["role"] == "ADMIN") {
        this._route.params.subscribe((id: Params) => {  this.id = id.id; });
        this.getSelectedUser(this.id);
        this.getActiveShouts(this.id);
      }
      else {
        this._router.navigate(['error']);
      }
    }
    else {
      this._router.navigate(['error']);
    }
  }

  /**
     * GET ACTIVE SHOUTS (ABHIJIT ROKADE)
     */
  getActiveShouts(id:number) {
    this.service.getSelectedUsersShout(id).subscribe(
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
     * GET SELECTED USEr DETAILS (PRASHANT SHARMA)
     */
  getSelectedUser(id:number){
    this._userServ.getCurrentUserById(id).subscribe(
      data => this.user = data
    )
  }
}
