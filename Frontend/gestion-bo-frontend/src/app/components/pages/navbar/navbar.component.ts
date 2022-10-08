import {Component, HostListener, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {AuthService} from "../../../core/services/auth.service";
import {UserSessionService} from "../../../core/services/userSession.service";
import {userSession} from "../../../core/models/UserSession";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
  public hide!: boolean;
  public user?: string
  constructor(private authService: AuthService,private sessionService:UserSessionService ) { }



  ngOnInit() {
    this.sessionService.getUserInfo(this.sessionService.OnlineUserID()).subscribe((session:userSession)=>{
      this.user=session.firstName+" "+session.lastName
      })
    if(innerWidth<1190)
      this.hide=true
  }

  @HostListener('window:resize', ['$event'])
  onResize(event?:any) {
    // alert(event.target.innerWidth)
    if(event.target.innerWidth<1190)
      this.hide=true
    else
      this.hide=false
  }

  logout() {
    this.authService.logout();
  }

  refresh() {
    this.ngOnInit()
  }
}
