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
	 * GET ACTIVE USERS (ABHIJIT ROKADE)
	 */
  getActiveUsers():Observable<User[]>{
    this.activeUsers=this.http.get<User[]>(this.url+"/active");
    return this.activeUsers;
  }

  /**
	 * GET ACTIVE SHOUTS OF SELECTED USER (PRASHANT SHARMA)
	 */
  getSelectedUsersShout(id:number){
    return this.http.get(this.url + "/myShouts/" + id);
  }

  
  getActiveUsersArray():Observable<User[]>{
    return this.activeUsers;
  }

  /**
	 * DELETE SHOUT BY SHOUT ID (ABHIJIT ROKADE)
	 */
  deleteShouts(shoutId:number){
    return this.http.get(this.url+"/deleteshouts/"+shoutId);
  }

  /**
	 * DELETE USER BY USER ID (ABHIJIT ROKADE)
	 */
  deleteUser(userId:number){
    return this.http.get(this.url+"/deleteuser/"+userId);
  }

  getActiveShouts(userId:number):Observable<Shout[]>{
    return this.http.get<Shout[]>(this.url+"/activeshouts/"+userId);
  }

  /**
	 * GET INACTIVE USERS (ABHIJIT ROKADE)
	 */
  getInactiveUsers():Observable<User[]>{
    this.inActiveUsers=this.http.get<User[]>(this.url+"/inactive");
    return this.inActiveUsers;
  }

  /**
	 * GET REPORTED SHOUTS (ABHIJIT ROKADE)
	 */
  getReportedShouts():Observable<Shout[]>{
    return this.http.get<Shout[]>(this.url+"/isreported");
  }

  /**
	 * APPROVE NEWLY REGISTERED USERS (ABHIJIT ROKADE)
	 */
  approveUsers(userId:number){
    return this.http.get<User[]>(this.url+"/approve/"+userId);
  }
}
