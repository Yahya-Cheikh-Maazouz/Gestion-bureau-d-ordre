import {Injectable} from "@angular/core";
import {AuthService} from "./auth.service";
import {map, Observable, Subject} from "rxjs";
import {Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn:'root'
})
export class UserSessionService{
  public session$=new Subject<{
    id:any,
    firstName: any,
    lastName: any,
    poste: any,
    email: any
  }>();


  constructor(private authService :AuthService,private router : Router, private http: HttpClient ) { }



  ngOnInit(): void {

    }

  public OnlineUserID() {
    let id: number = Number(localStorage.getItem('idUser'));
    return id
  }

  public getUserInfo(id : number):Observable<any>{
    return this.http.get("http://localhost:5050/api/v1/registration/user/"+id);
  }

  setUserSession(email:string){
/*    this.authService.getUserInfo(email).subscribe((user :any)=>{
      this.session$.pipe(
        map((session: any) => {
          alert("ca marche")
          session.id=user.id
          session.firstName=user.firstName
          session.lastName=user.lastName
          session.email=user.email
          session.poste=user.poste
        })
      )
    })*/
  }



}
