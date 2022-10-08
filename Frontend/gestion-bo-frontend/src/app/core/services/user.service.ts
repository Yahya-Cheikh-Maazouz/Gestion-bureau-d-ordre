import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {UserModel} from "../models/User.model";
import {AuthService} from "./auth.service";

@Injectable({
  providedIn:'root'
})
export class UserService{
  constructor(private http:HttpClient,private auth: AuthService) {
  }


  public users (){
    return this.http.get<UserModel>("http://localhost:5050/api/v1/us/users/"+this.auth.getTenantId())
  }


  public updateUer(user:any){
    return this.http.post("http://localhost:5050/api/v1/registration/account/update",user)
  }
}
