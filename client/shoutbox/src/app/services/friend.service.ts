import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../modals/User';

@Injectable({
  providedIn: 'root'
})
export class FriendService {

  private url = "http://localhost:9092/user";

  constructor(private httpclient: HttpClient) { }

  // FRIENDS LIST
  public getMyFriends(currentUserId): Observable<any> {
    return this.httpclient.get<User[]>(this.url + "/getFriends/" + currentUserId);
  }
  public getMyPendingFriends(currentUserId: number): Observable<any> {
    return this.httpclient.get(this.url + "/pending-friends/" + currentUserId)
  }

  public getNewFriends(currentUserId: number): Observable<any> {
    return this.httpclient.get(this.url + "/new-users/" + currentUserId)
  }



  // FRIEND REQUESTS
  public sendFriendRequest(currentUserId: number, userId: number) {
    return this.httpclient.get(this.url + "/send-friend-request/" + currentUserId + "/" + userId)
  }

  public rejectFriendRequest(currentUserId: number, userId: number) {
    return this.httpclient.get(this.url + "/reject-friend-request/" + currentUserId + "/" + userId)
  }

  public acceptFriendRequest(currentUserId: number, userId: number) {
    return this.httpclient.get(this.url + "/accept-friend-request/" + currentUserId + "/" + userId)
  }

  public unFriendRequest(currentUserId: number, userId: number) {
    return this.httpclient.get(this.url + "/reject-approved-friend/" + currentUserId + "/" + userId)
  }



}
