import { Component, OnInit } from '@angular/core';
import {userSession} from "../../../../core/models/UserSession";
import {UserSessionService} from "../../../../core/services/userSession.service";
import {UserService} from "../../../../core/services/user.service";
import Swal from 'sweetalert2'
@Component({
  selector: 'app-myaccount',
  templateUrl: './myaccount.component.html',
  styleUrls: ['./myaccount.component.scss']
})
export class MyaccountComponent implements OnInit {


  public userInfo !:userSession
  formData:any
  newPassword:any
  confirmnewPassword:any
  nameInputActivated:boolean=false
  lastInputActivated:boolean=false
  emailInputActivated:boolean=false
  passwordInputActivated:boolean=false

  constructor(private sessionService :UserSessionService,private api : UserService) { }

  ngOnInit(): void {
    this.sessionService.getUserInfo(this.sessionService.OnlineUserID()).subscribe((session:userSession)=>{
      this.userInfo=session
    })
  }




  submit(){
    Swal.fire({
      title: 'Entrer votre mot de passe pour continuer',
      input: 'text',
      inputAttributes: {
        autocapitalize: 'off'
      },
      showCancelButton: true,
      confirmButtonText: 'confirm',
      showLoaderOnConfirm: true,
      preConfirm:res => {
        if(res.length>0)
        {
          this.formData={
            password:res,
            "user":{
              "idUser":this.userInfo.idUser,
              "firstName":this.userInfo.firstName,
              "lastName":this.userInfo.lastName,
              "email":this.userInfo.email,
              "newpassword":null
            }
          }

          if(this.newPassword===this.confirmnewPassword){
            this.formData.user.newpassword=this.newPassword
            console.log(this.newPassword)
            console.log(this.confirmnewPassword)

          }

          this.api.updateUer(this.formData).subscribe(
            t=>{
              Swal.clickCancel()
              Swal.fire({
                title: 'Succes',
                text: 'modification bien effectuer',
                icon: 'success',
                confirmButtonText: 'Cool'
              })
            },
            e => {Swal.fire({
              title: 'Error!',
              text: 'Do you want to continue',
              icon: 'error',
              confirmButtonText: 'Cool'
            })}
        )
        }
        return false
      }
    }).then((res) => {
    })


  }




  cancel() {
    this.sessionService.getUserInfo(this.sessionService.OnlineUserID()).subscribe((session:userSession)=>{
      this.userInfo=session
    })
    this.nameInputActivated=false
    this.lastInputActivated=false
    this.emailInputActivated=false
    this.passwordInputActivated=false
  }



  activateInput(t:any){
    if(t)
    {
      switch (t){
        case "n":
          this.nameInputActivated=true
          break;
        case "l":
          this.lastInputActivated=true
          break;
        case "e":
          this.emailInputActivated=true
          break;
        case "p":
          this.passwordInputActivated=true
          break;
      }
    }

  }
}
