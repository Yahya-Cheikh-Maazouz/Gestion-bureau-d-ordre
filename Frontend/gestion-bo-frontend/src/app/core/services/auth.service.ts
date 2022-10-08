import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {accountUser} from "../models/AccountUser";
import {environment} from "../../../environments/environment";
import jwt_decode from "jwt-decode";
import {isBoolean} from "@ngrx/store/src/meta-reducers/utils";
import ILoginRequest from "../models/ILoginRequest";
import {Router} from "@angular/router";
import {userSession} from "../models/UserSession";
@Injectable({
  providedIn: 'root'
})

export class AuthService {

  private host: string = environment.hostURL;
  private token = localStorage.getItem('token') as any;
  private tenantId = localStorage.getItem('XTenantID') as any;
  private roles!: any[] ;
  private username!: any;
  private exp: any;
  clearTimeout: any;
  session !: userSession
  onlineUserId!:number

  constructor(private router : Router, private http: HttpClient ) { }

  public signUp(user : accountUser ): Observable<any>{
    return this.http.post("http://localhost:5050/api/v1/registration/register", user,
      {responseType: 'text'});
  }

  creatUser(user: accountUser) {
    return this.http.post("http://localhost:5050/api/v1/us/register/"+this.getTenantId(), user,
      {responseType: 'text'});
  }


  public login(mail : string,password:string): Observable<any>{
    let userForm=<ILoginRequest>{};
    userForm.email=mail;
    userForm.password=password;
    /*console.log(userForm)*/
    return this.http.post("http://localhost:5050/api/v1/login", userForm,{ observe: 'response'});
  }

  public getIduserfromApi(email : string): Observable<any>{
    return this.http.post("http://localhost:5050/api/v1/registration/user/info", email);
  }

  public getTenantfromApi(email : string): Observable<any>{
    return this.http.post("http://localhost:5050/api/v1/registration/get/tenant",email,
      {responseType: 'text'});
  }


  autoLogout(expirationDate: number) {
    this.clearTimeout = setTimeout(() => {
      alert("auto deconnect")
      this.logout();
    }, (expirationDate*1000)-Date.now());
  }

  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('idUser');
    localStorage.removeItem('XTenantID')
    localStorage.removeItem('role')

    if (this.clearTimeout) {
      clearTimeout(this.clearTimeout);
    }
    this.router.navigate(['/login']);
  }



  public isAuthenticate(){
    if (localStorage.getItem("token"))
      return true;
    else return false;
  }

  public saveToken(jwt:string){
    this.token = jwt;
    localStorage.setItem('token', `Bearer ${jwt}`);
    this.parseJWT();
  }

  public saveTenantId(tenantId:string){
    localStorage.setItem('XTenantID', tenantId);
  }


  public saveUserId(id:any){
    localStorage.setItem('idUser', id);
  }

  public parseJWT(user?: any) : void {
    const decoded = jwt_decode(this.token) as any;
   // localStorage.setItem('expiration', (decoded as any).exp);
    this.roles = decoded.roles ;
    this.username = decoded.sub;
    this.exp = decoded.exp;
  }

  isAdmin(){
    let admin=false
    console.log(this.roles)
    this.roles.map(
      item =>{
      if(item==="ADMIN")
        admin=true
    })
    if(admin)
      localStorage.setItem('role', "ADMIN");
    else
      localStorage.setItem('role', "USER");

  }



  public getToken() {
    return localStorage.getItem('token');
  }

  public getTenantId() {
    return localStorage.getItem('XTenantID');
  }

  public getEmail() {
    return this.username
  }

  public getExpirationJwt():number {
    return this.exp;
  }


  public getIduser():string | null {
     return localStorage.getItem('idUser');;
  }


}
