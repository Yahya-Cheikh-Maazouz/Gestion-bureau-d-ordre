import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {select, Store} from "@ngrx/store";
import {AuthService} from "../../../../core/services/auth.service";
import {loginEvent} from "../../../../core/store/auth/auth.actions";
import ILoggedInUser from "../../../../core/models/ILoggedInUser";
import ILoginRequest from "../../../../core/models/ILoginRequest";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  private user=<ILoginRequest>{};
  public loginForm = new FormGroup({
    mail: new FormControl(this.user.email, Validators.compose([
      Validators.required,
      Validators.minLength(1),
      Validators.pattern('^[a-z|0-9|A-Z]*([_][a-z|0-9|A-Z]+)*([.][a-z|0-9|A-Z]+)*([.][a-z|0-9|A-Z]+)*' +
        '(([_][a-z|0-9|A-Z]+)*)?@[a-z][a-z|0-9|A-Z]*\\.([a-z][a-z|0-9|A-Z]*(\\.[a-z][a-z|0-9|A-Z]*)?)$')
    ])),
    passWord: new FormControl(this.user.password, Validators.compose([
      Validators.required,
      Validators.minLength(6)
    ]))
  });
  displayAnim: boolean=false;

  constructor(private apiAuth : AuthService, private router : Router, private _store: Store)
  { }

  ngOnInit(): void {
    this.displayAnim=true
    setTimeout(() => {
      this.displayAnim=false
    }, 1500); //
  }


  login(form: HTMLFormElement) {
    this._store.dispatch(loginEvent({
      loginEventRequest: { email: this.loginForm.getRawValue().mail, password:this.loginForm.getRawValue().passWord }
    }))
  }






}
