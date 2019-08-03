import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import {HttpClientModule} from '@angular/common/http';
import {NgxPaginationModule} from 'ngx-pagination';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { MenubarComponent } from './menubar/menubar.component';
import { ForgetPassComponent } from './forget-pass/forget-pass.component';
import { RegisterUserComponent } from './register-user/register-user.component';
import { Error404Component } from './error404/error404.component';
import { ErrorOtherComponent } from './error-other/error-other.component';
import { FormsModule } from '@angular/forms';
import { AdminComponent } from './admin/admin.component';

import { ViewshoutsComponent } from './viewshouts/viewshouts.component';
import { DeleteshoutsComponent } from './deleteshouts/deleteshouts.component';
import { DeleteuserComponent } from './deleteuser/deleteuser.component';
import { ApproveuserComponent } from './approveuser/approveuser.component';
import { RefreshComponent } from './refresh/refresh.component';
import { AdminService } from './services/admin.service';
import { AdminSidebarComponent } from './admin-sidebar/admin-sidebar.component';
import { ReportedshoutsComponent } from './reportedshouts/reportedshouts.component';
import { UserDashboardComponent } from './user-dashboard/user-dashboard.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { FriendComponent } from './friend/friend.component';
import { UserDashboardService } from './services/user-dashboard.service';
import { UserSidebarComponent } from './user-sidebar/user-sidebar.component';
import { SearchFriendFilterPipe } from './pipes/search-friend-filter.pipe';
import { FilterNamesPipe } from './pipes/filter-names.pipe';
import { FilterInitialsPipe } from './pipes/filter-initials.pipe';
import { CommentComponent } from './comment/comment.component';
import { WriteCommentComponent } from './write-comment/write-comment.component';




@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    MenubarComponent,
    ForgetPassComponent,
    RegisterUserComponent,
    Error404Component,
    ErrorOtherComponent,
    AdminComponent,
    ViewshoutsComponent,
    DeleteshoutsComponent,
    DeleteuserComponent,
    ApproveuserComponent,
    RefreshComponent,
    AdminSidebarComponent,
    ReportedshoutsComponent,
    UserDashboardComponent,
    UserProfileComponent,
    FriendComponent,
    UserSidebarComponent,
    SearchFriendFilterPipe,
    FilterNamesPipe,
    FilterInitialsPipe,
    CommentComponent,
    WriteCommentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgxPaginationModule
  ],
  providers: [AdminService,UserDashboardService],
  bootstrap: [AppComponent]
})
export class AppModule { }
