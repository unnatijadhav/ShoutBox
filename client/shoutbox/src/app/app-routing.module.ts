import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { ForgetPassComponent } from './forget-pass/forget-pass.component';
import { RegisterUserComponent } from './register-user/register-user.component';
import { Error404Component } from './error404/error404.component';
import { ErrorOtherComponent } from './error-other/error-other.component';
import { AdminComponent } from './admin/admin.component';
import { ViewshoutsComponent } from './viewshouts/viewshouts.component';
import { DeleteshoutsComponent } from './deleteshouts/deleteshouts.component';
import { DeleteuserComponent } from './deleteuser/deleteuser.component';
import { ApproveuserComponent } from './approveuser/approveuser.component';
import { ReportedshoutsComponent } from './reportedshouts/reportedshouts.component';

import { UserDashboardComponent } from './user-dashboard/user-dashboard.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { FriendComponent } from './friend/friend.component';

const routes: Routes = [
  {
    path:"",
    component:LoginComponent
  },
  {
    path:"login",
    component:LoginComponent
  },
  {
    path:"forgetPassword",
    component:ForgetPassComponent
  },
  {
    path:"register",
    component:RegisterUserComponent
  },

  {
    path:"admin",
    component:AdminComponent
  },
  {
    path:"viewshouts/:id",
    component:ViewshoutsComponent
  },
  {
    path:"deleteshout/:shoutId/:userId",
    component:DeleteshoutsComponent
  },
  {
    path:"deleteuser/:userId",
    component:DeleteuserComponent
  },
  {
    path:"approveusers",
    component:ApproveuserComponent
  },
  {
    path:"reported",
    component:ReportedshoutsComponent
  },
  {
    path:"user",
    component:UserDashboardComponent
  },
  {
    path:"profile",
    component:UserProfileComponent
  },
  {
    path:"my-friend",
    component:FriendComponent
  },
  {
    path:"error404",
    component:Error404Component
  },
  {
    path:"error",
    component:ErrorOtherComponent
  },
  {
    path:"**",
    component:ErrorOtherComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
