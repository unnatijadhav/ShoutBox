import { Component, OnInit } from '@angular/core';
import { AdminService } from '../services/admin.service';
import { ActivatedRoute, Params, Router } from '@angular/router';

@Component({
  selector: 'app-deleteshouts',
  templateUrl: './deleteshouts.component.html',
  styleUrls: ['./deleteshouts.component.scss', '../app.component.scss']
})
export class DeleteshoutsComponent implements OnInit {
  count: number;
  shoutId: number;
  userId: number;
  constructor(private service: AdminService, private _route: ActivatedRoute, private _router: Router) { }

  ngOnInit() {
    if(("userObject" in localStorage) && ("access_token" in localStorage)){
      
      let currObj = JSON.parse(localStorage.getItem("userObject"));
      if(currObj["role"] == "ADMIN"){
        this.deleteShouts();
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
	 * DELETE SHOUT BY SHOUT ID (ABHIJIT ROKADE)
	 */
  deleteShouts() {
    if (confirm("Do you really want to delete this shout.")) {
      this._route.params.subscribe((shoutId: Params) => {
        this.shoutId = shoutId.shoutId;
      })
      this._route.params.subscribe((userId: Params) => {
        this.userId = userId.userId;
      })
      this.service.deleteShouts(this.shoutId).subscribe((resp) => {
        console.log("Response = " + resp);
        this._router.navigate(['viewshouts/' + this.userId])
        alert("Shout with id " + this.shoutId + " deleted successfully !!!");
      })
    }
    else{
      this._route.params.subscribe((userId: Params) => {
        this.userId = userId.userId;
      });
      this._router.navigate(['viewshouts/' + this.userId]);
    }
  }
  viewShouts() {
    this._route.params.subscribe((userId: Params) => {
      this.userId = userId.userId;
    })
    this._router.navigate(['viewshouts/' + this.userId]);
  }
}
