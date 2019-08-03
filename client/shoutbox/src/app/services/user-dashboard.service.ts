import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Shout } from '../modals/Shouts';
import { User } from '../modals/User';
import { Router } from '@angular/router';


@Injectable({
  providedIn: 'root'
})
export class UserDashboardService {

  private url = "http://localhost:9092/user";
  private baseurl = "http://localhost:9092/shout";

  constructor(private httpclient: HttpClient, private _router: Router) { }

  public getFriendsShouts(currentUserId: number): Observable<any> {
    return this.httpclient.get<any>(this.baseurl + "/get-friends-shouts/" + currentUserId);
  }

  public getCurrentUserById(currentUserId: number) {
    return this.httpclient.get<User>(this.url + "/current/" + currentUserId);
  }

  public postShout(shout, currentUserId: number) {
    console.log(currentUserId);
    var fd = new FormData();
    fd.append("data", shout.data);
    fd.append("timestamp", shout.timestamp);
    fd.append("isShoutActive", shout.isShoutActive);
    fd.append("shoutContentType", shout.shoutContentType);
    console.log(shout.shoutContentType);
    
  
    fd.append("ownerId", JSON.stringify(currentUserId));
  //  fd.append("comments", shout.comments);
    console.log(shout);

    console.log("inside service" + fd)
    this._router.navigate(["/user"]);
    if (shout.shoutContentType === "text")
      return this.httpclient.post(this.baseurl + '/upload/text', fd);
    else
      return this.httpclient.post(this.baseurl + '/upload', fd);
  }

  public reportCurrentShout(shoutId: number) {
    return this.httpclient.get(this.baseurl + "/report/" + shoutId);
  }

  public getCommentsByShout(currentShoutId: number): Observable<any> {
    return this.httpclient.get<Comment[]>(this.baseurl + "/getcomments/" + currentShoutId);
  }

  public getShoutById(currentShoutId: number) {
    return this.httpclient.get(this.baseurl + "/getshoutbyid/" + currentShoutId);
  }

  public postComment(currentUserId: number, currentShoutId: number,shoutsCommments:string) {
    var formData = new FormData();
    formData.append("comments",shoutsCommments);
    return this.httpclient.post(this.baseurl + "/postmycomment/" + currentUserId + "/" + currentShoutId, formData);
  } 
}