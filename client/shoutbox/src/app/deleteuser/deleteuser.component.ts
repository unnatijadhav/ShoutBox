import { Component, OnInit } from '@angular/core';
import { AdminService } from '../services/admin.service';
import { ActivatedRoute, Params, Router } from '@angular/router';


@Component({
  selector: 'app-deleteuser',
  templateUrl: './deleteuser.component.html',
  styleUrls: ['./deleteuser.component.scss', '../app.component.scss']
})
export class DeleteuserComponent implements OnInit {
  id:number;
  constructor(private service:AdminService,private _route:ActivatedRoute,private _router:Router) { }

  ngOnInit() {
    if(("userObject" in localStorage) && ("access_token" in localStorage)){
      let currObj = JSON.parse(localStorage.getItem("userObject"));
      if(currObj["role"] == "ADMIN"){
        this.deleteUser();
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
	 * DELETE USER BY USER ID
	 */
  deleteUser(){
    if(confirm("Do you really want to delete this user.")){
      this._route.params.subscribe((userId:Params)=>{
        this.id=userId.userId;
        console.log("id = "+this.id);
      });

      this.service.deleteUser(this.id).subscribe((resp)=>{
        console.log("Response = "+resp);
        this._router.navigate(["admin"]);
        alert("User with id "+this.id+" deleted successfully !!!");
      });
    }
    else{
      this._router.navigate(['admin']);
    }
  }

  viewUsers(){
    this._router.navigate(['admin']);
  }
}
