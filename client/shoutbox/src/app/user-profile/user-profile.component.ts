import { Component, OnInit } from '@angular/core';
import { UserProfileService } from '../services/user-profile.service';
import { Router } from '@angular/router';
import { User } from '../modals/User';
import { UserDashboardService } from '../services/user-dashboard.service';
import { ShoutText, Shout } from '../modals/Shouts';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})

export class UserProfileComponent implements OnInit {

  currentUser: User = new User();
  currentUserId: number;
  userTextShouts: ShoutText[];
  userBlobShouts: Shout[];

  constructor(private _router: Router, private _profileServ: UserProfileService, private _userServ: UserDashboardService) { }

  ngOnInit() {
    if (("userObject" in localStorage) && ("access_token" in localStorage)) {

      let currObj = JSON.parse(localStorage.getItem("userObject"));
      if (currObj["role"] == "USER") {
        this.currentUserId = currObj["id"];
        this._userServ.getCurrentUserById(this.currentUserId).subscribe(
          data => this.currentUser = data
        )
        this.getCurrentShouts(this.currentUserId);
      }
      else {
        this._router.navigate(['error']);
      }
    }
    else {
      this._router.navigate(['error']);
    }

  }

  getCurrentShouts(id) {
    console.log(id);
    this._profileServ.getCurrentUserShouts(this.currentUserId).subscribe(
      (response) => {
        console.log(response);
        if (response != undefined) {
          if(response["blobShouts"] === undefined){
            this.userBlobShouts = response["textShouts"];
          } 
          else if(response["textShouts"] === undefined){
            this.userBlobShouts = response["blobShouts"];
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

  deleteShout(currentShoutId){
    if(confirm("Are you sure, you want to delete shout?")){
      this._profileServ.deleteCurrentShout(currentShoutId).subscribe(
        (resp) => {
          console.log("Response = " + resp);
          alert("Shout deleted successfully !!!")
          this._router.navigateByUrl('/RefrshComponent', { skipLocationChange: true }).then(() =>
            this._router.navigate(["user"]));
        });
    }
  }
} 
