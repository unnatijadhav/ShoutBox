import { Component, OnInit } from '@angular/core';
import { Shout, ShoutText } from '../modals/Shouts';
import { UserDashboardService } from '../services/user-dashboard.service';
import { User } from '../modals/User';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.scss']
})
export class UserDashboardComponent implements OnInit {
  shout: Shout = new Shout();
  userTextShouts: ShoutText[];
  userBlobShouts: Shout[];
  currentUser: User = new User();
  currentUserId: number;
  currentShout: any;
  componentShout: Shout[];
  comment: Comment = new Comment();
  myComment: string;

  constructor(private _userServ: UserDashboardService, private _router: Router) { }

  ngOnInit() {
    if (("userObject" in localStorage) && ("access_token" in localStorage)) {
      let currObj = JSON.parse(localStorage.getItem("userObject"));
      if (currObj["role"] == "USER") {
        this.currentUserId = currObj["id"];
        this._userServ.getCurrentUserById(this.currentUserId).subscribe(
          data => this.currentUser = data
        )
        //this.getCurrentUserById(this.currentUserId);
        this.getFriendsShouts();
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
      this.currentUser.shouts = data.shouts;
      console.log(data);
    });
  }

  getFriendsShouts(): void {
    this._userServ.getFriendsShouts(this.currentUserId).subscribe(
      (response) => {
        console.log(response);
        if (response != undefined) {
          if (response["blobShouts"] === undefined) {
            this.userBlobShouts = response["textShouts"];
            console.log(this.currentUser);
            console.log(this.userBlobShouts);
            console.log(this.userBlobShouts.sort((a, b): number => {
              if (a.id > b.id) return -1;
              if (a.id < b.id) return 1;
            }));
          }
          else if (response["textShouts"] === undefined) {
            this.userBlobShouts = response["blobShouts"];
            console.log(this.userBlobShouts);
            console.log(this.userBlobShouts.sort((a, b): number => {
              if (a.id > b.id) return -1;
              if (a.id < b.id) return 1;
            }));
          }
          else {
            this.userTextShouts = response["textShouts"];
            this.userBlobShouts = response["blobShouts"];
            this.userTextShouts.forEach((element) =>
              this.userBlobShouts.push(element)
            );
            console.log(this.userBlobShouts);
            console.log(this.userBlobShouts.sort((a, b): number => {
              if (a.id > b.id) return -1;
              if (a.id < b.id) return 1;
            }));
          }
        } else
          alert("error occured");
      })
  }

  reportShoutById(shoutId: number) {
    if (confirm("Do you want to report current Shout?")) {
      this._userServ.reportCurrentShout(shoutId).subscribe(
        (resp) => {
          console.log("Response = " + resp);
          alert("Shout reported successfully !!!")
          this._router.navigateByUrl('/RefrshComponent', { skipLocationChange: true }).then(() =>
            this._router.navigate(["user"]));
        });
    }
  }

  // AUTHOR :SADIYA SHAIKh
  onSelecttext(event) {
    //event for selecting the text
    console.log('file selected...');
    console.log(event);
    this.shout.data = "";
    this.shout.data += event.target.value;
    this.shout.shoutContentType = event.target.type;
    console.log("text content type" + this.shout.shoutContentType)
    console.log("inside on select text event-->" + this.shout.data);
  }

  onSelectFile(event) {
    //event for selecting the file
    console.log('file selected...');
    console.log(event);
    this.shout.data = event.target.files[0];
    this.shout.shoutContentType = event.target.files[0].type;
    console.log(this.shout.data.contentType)
  }

  onSubmit() {
    if (this.shout.data === undefined) {
      alert('Please enter message or upload file')
    }

    else if (this.shout.shoutContentType === "text") {
      console.log("inside submit method" + this.shout.shoutContentType)
      let date: any = new Date();
      console.log(date);
      this.shout.isShoutActive = true;
      this.shout.timestamp = date;
      console.log("timestamp : " + this.shout.timestamp);
      console.log("active : " + this.shout.isShoutActive);
      console.log("contentType : " + this.shout.shoutContentType);
      console.log(this.shout);
      console.log(this.shout.data)
      //calling service layer method
      this._userServ.postShout(this.shout, this.currentUserId).subscribe((response) => {
        console.log(response);
        this._router.navigateByUrl('/RefrshComponent', { skipLocationChange: true }).then(() =>
          this._router.navigate(["user"]));
      })
    }
    else {
      console.log("inside submit method" + this.shout.shoutContentType)
      let date: any = new Date();
      console.log(date);
      this.shout.isShoutActive = true;
      this.shout.timestamp = date;
      console.log("timestamp : " + this.shout.timestamp);
      console.log("active : " + this.shout.isShoutActive);
      console.log("contentType : " + this.shout.shoutContentType);
      console.log(this.shout);
      console.log(this.shout.data)
      this._userServ.postShout(this.shout, this.currentUserId).subscribe((response) => {
        console.log(this.shout);
        console.log(response);
        this._router.navigateByUrl('/RefrshComponent', { skipLocationChange: true }).then(() =>
          this._router.navigate(["user"]));
      })
    }
  }

  // checking currentShout is equal to shout which is clicked
  getShoutById(currentShoutId: number) {
    this._userServ.getShoutById(currentShoutId).subscribe((response) => {
      if (response != undefined) {
        console.log(currentShoutId);
        this.currentShout = response;
      } else
        alert("Error Occurred");
    });
  }

  getComments(currentShoutId: number) {
    this.getShoutById(currentShoutId);
    this._userServ.getCommentsByShout(currentShoutId).subscribe((response) => {
      if (response != undefined) {
        console.log(response);
      } else
        alert("Error Occurred");
    });
  }

  postComment(currentShoutId: number) {
    this._userServ.postComment(this.currentUserId, currentShoutId, this.myComment).subscribe((response) => {
      if (response != "Post Comment Failed") {
       // this.getComments(currentShoutId);
        this._router.navigateByUrl('/RefrshComponent', { skipLocationChange: true }).then(() =>
          this._router.navigate(["user"]));
        console.log(response);
      } else {
        alert("Error Occurred");
      }
    });
  }
}
