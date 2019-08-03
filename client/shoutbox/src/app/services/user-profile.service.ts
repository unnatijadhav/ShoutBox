import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserProfileService {
  

  url:string="http://localhost:9092/user/";
  shouturl:string="http://localhost:9092/shout/";

  constructor(private _http:HttpClient) { }

  getCurrentUserShouts(id:number){
    return this._http.get(this.url + "myShouts/" + id);
  }

  deleteCurrentShout(currentShoutId: number) {
    return this._http.get(this.shouturl + "delete-shout/" + currentShoutId);
  }
}
