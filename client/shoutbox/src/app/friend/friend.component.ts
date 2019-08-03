import { Component, OnInit } from '@angular/core';
import { UserDashboardService } from '../services/user-dashboard.service';
import { Observable } from 'rxjs';
import { FriendService } from '../services/friend.service';
import { User } from '../modals/User';
import { Router } from '@angular/router';

@Component({
  selector: 'app-friend',
  templateUrl: './friend.component.html',
  styleUrls: ['./friend.component.scss']
})
export class FriendComponent implements OnInit {
  userFriends: Observable<any>;

  pendingFriends: User[];
  searchNewFriends: User[];

  currentUser: User;
  currentUserId: number;

  constructor(private _friendservice: FriendService, private _userdashboardservice: UserDashboardService, private _router: Router) { }

  ngOnInit() {
    if (("userObject" in localStorage) && ("access_token" in localStorage)) {

      let currObj = JSON.parse(localStorage.getItem("userObject"));

      if (currObj["role"] == "USER") {
        this.currentUserId = currObj["id"];
        this.getCurrentUserById(this.currentUserId);
        this.getMyFriends();
        this.getPendingFriends();
        this.getNewFriends();
      }
      else {
        this._router.navigate(['error']);
      }
    }
    else {
      this._router.navigate(['error']);
    }
  }

  // getting current session user
  getCurrentUserById(currentUserId: number) {
    this._userdashboardservice.getCurrentUserById(currentUserId).subscribe((data) => {
      this.currentUser = data;
      console.log(this.currentUser);
    });
  }

  // getting current user  -> active friends
  getMyFriends() {
    this._friendservice.getMyFriends(this.currentUserId).subscribe((response) => {
      if (response != undefined) {
        this.userFriends = response;
        console.log(this.userFriends);
      } else
        alert("Error Occurred");
    });
  }

  // getting current user  -> pending friends
  getPendingFriends() {
    this._friendservice.getMyPendingFriends(this.currentUserId).subscribe((response) => {
      if (response != undefined) {
        this.pendingFriends = response;
        console.log(this.pendingFriends);
      } else
        alert("Error Occurred");
    });
  }

  // getting current user  -> new friends to add
  getNewFriends() {
    this._friendservice.getNewFriends(this.currentUserId).subscribe((response) => {
      if (response != undefined) {
        this.searchNewFriends = response;
        console.log(this.searchNewFriends);
      } else
        alert("Error Occurred");
    });
  }

  // ****************** FRIEND REQUESTS **************
  
  // sendFriendRequest
  sendFriendRequest(currentUserId:number, userId: number) {
    if(confirm("Are you sure, you want to SEND REQUEST")){
      this._friendservice.sendFriendRequest(currentUserId, userId).subscribe((response) => {
        if (response == "OK") {
          alert("Request Sent.");
          this._router.navigateByUrl('/RefrshComponent', {skipLocationChange: true}).then(()=>
          this._router.navigate(["my-friend"])); 
        } else
        alert("Error Occurred");
      });
    }
  }

  // rejectFriendRequest
  rejectFriendRequest(currentUserId:number, userId: number) {
    if(confirm("Are you sure, you want to REJECT FRIEND REQUEST")){
      this._friendservice.rejectFriendRequest(currentUserId, userId).subscribe((response) => {
      if (response == "OK") {
        alert("Rejected Friend Request.");
        this._router.navigateByUrl('/RefrshComponent', {skipLocationChange: true}).then(()=>
        this._router.navigate(["my-friend"]));
      } else
      alert("Error Occurred");
    });
  }
  }


  // acceptFriendRequest 
  acceptFriendRequest(currentUserId:number, userId: number) {
    if(confirm("Are you sure, you want to ACCEPT FRIEND REQUEST")){
      this._friendservice.acceptFriendRequest(currentUserId, userId).subscribe((response) => {
      if (response == "OK") {
        alert("Accepted. Yo got a new friend.");
        this._router.navigateByUrl('/RefrshComponent', {skipLocationChange: true}).then(()=>
        this._router.navigate(["my-friend"]));
      } else
      alert("Error Occurred");
    });
  }
  }

  // unFriendRequest approved friend
  unFriendRequest(currentUserId:number, userId: number) {
    if(confirm("Are you sure, you want to UNFRIEND")){
      this._friendservice.unFriendRequest(currentUserId, userId).subscribe((response) => {
      if (response == "OK") {
        alert("Unfriend. You lost a friend.");
        this._router.navigateByUrl('/RefrshComponent', {skipLocationChange: true}).then(()=>
        this._router.navigate(["my-friend"]));
      } else
      alert("Error Occurred");
    });
  }
  }

  // view shouts by Id
  viewShoutsByID(userId: number) {
   
  }




}
