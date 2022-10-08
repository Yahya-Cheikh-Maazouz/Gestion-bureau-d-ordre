import { Component, OnInit } from '@angular/core';
import {UserSessionService} from "../../../core/services/userSession.service";

@Component({
  selector: 'app-side-bar',
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.scss']
})
export class SideBarComponent implements OnInit {
  isAdmin:boolean=false
  ngOnInit(): void {
    if(localStorage.getItem('role')==="ADMIN")
      this.isAdmin=true
    else this.isAdmin=false
  }



}
