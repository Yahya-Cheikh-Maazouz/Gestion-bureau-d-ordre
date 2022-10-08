import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {accountUser} from "../../../../core/models/AccountUser";
import {AuthService} from "../../../../core/services/auth.service";
import Swal from "sweetalert2";
import {Router} from "@angular/router";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {

  private user=<accountUser>{};

  signupForm = new FormGroup({
    firstName: new FormControl(this.user.firstName, Validators.compose([
      Validators.required, Validators.minLength(1)])),
    lastName: new FormControl(this.user.lastName, Validators.compose([
      Validators.required, Validators.minLength(1)])),
    mail: new FormControl(this.user.email, Validators.compose([
      Validators.required,
      Validators.minLength(1),
      Validators.pattern('^[a-z|0-9|A-Z]*([_][a-z|0-9|A-Z]+)*([.][a-z|0-9|A-Z]+)*([.][a-z|0-9|A-Z]+)*(([_][a-z|0-9|A-Z]+)*)?@[a-z][a-z|0-9|A-Z]*\\.([a-z][a-z|0-9|A-Z]*(\\.[a-z][a-z|0-9|A-Z]*)?)$')
    ])),
    passWord: new FormControl(this.user.password, Validators.compose([
      Validators.required,
      Validators.minLength(6)
    ])),
  });


  constructor(private apiAuth : AuthService,private route:Router) { }

  ngOnInit(): void {
  }

  public signup() : void {
    this.user.firstName=this.signupForm.getRawValue().firstName;
    this.user.lastName=this.signupForm.getRawValue().lastName;
    this.user.email=this.signupForm.getRawValue().mail;
    this.user.password=this.signupForm.getRawValue().passWord;
    this.apiAuth.signUp(this.user).subscribe(res=>{
      this.signupForm.reset()
      Swal.fire({
        icon: 'success',
        title: 'Your Account  has been created please activate your account',
        showConfirmButton: false,
        timer: 1500
      })
      this.route.navigate(["/login"]);
    });
  }



}
