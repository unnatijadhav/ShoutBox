import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-forget-pass',
  templateUrl: './forget-pass.component.html',
  styleUrls: ['./forget-pass.component.scss']
})
export class ForgetPassComponent implements OnInit {

  constructor(private _router:Router) { }

  ngOnInit() {
    if(("userObject" in localStorage) && ("access_token" in localStorage)){
      
     }
     else{
       this._router.navigate(['error']);
     }
  }

}
