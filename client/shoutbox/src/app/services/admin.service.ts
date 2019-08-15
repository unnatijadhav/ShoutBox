import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../modals/User';
import { Shout } from '../modals/Shouts';


@Injectable({
  providedIn: 'root'
})
export class AdminService {
  url:string="http://localhost:9092/user";

  activeUsers:Observable<User[]>;
  inActiveUsers:Observable<User[]>;
  userShouts:Observable<Shout[]>;
  constructor(private http:HttpClient) { }

  ngOnInit(): void {
    this.getActiveUsers();
  }

  /**
	 * GET ACTIVE USERS 
	 */
  getActiveUsers():Observable<User[]>{
    this.activeUsers=this.http.get<User[]>(this.url+"/active");
    return this.activeUsers;
  }

  /**
	 * GET ACTIVE SHOUTS OF SELECTED USER 
	 */
  getSelectedUsersShout(id:number){
    return this.http.get(this.url + "/myShouts/" + id);
  }

  
  getActiveUsersArray():Observable<User[]>{
    return this.activeUsers;
  }

  /**
	 * DELETE SHOUT BY SHOUT ID 
	 */
  deleteShouts(shoutId:number){
    return this.http.get(this.url+"/deleteshouts/"+shoutId);
  }

  /**
	 * DELETE USER BY USER ID 
	 */
  deleteUser(userId:number){
    return this.http.get(this.url+"/deleteuser/"+userId);
  }

  getActiveShouts(userId:number):Observable<Shout[]>{
    return this.http.get<Shout[]>(this.url+"/activeshouts/"+userId);
  }

  /**
	 * GET INACTIVE USERS 
	 */
  getInactiveUsers():Observable<User[]>{
    this.inActiveUsers=this.http.get<User[]>(this.url+"/inactive");
    return this.inActiveUsers;
  }

  /**
	 * GET REPORTED SHOUTS 
	 */
  getReportedShouts():Observable<Shout[]>{
    return this.http.get<Shout[]>(this.url+"/isreported");
  }

  /**
	 * APPROVE NEWLY REGISTERED USERS
	 */
  approveUsers(userId:number){
    return this.http.get<User[]>(this.url+"/approve/"+userId);
  }
}
