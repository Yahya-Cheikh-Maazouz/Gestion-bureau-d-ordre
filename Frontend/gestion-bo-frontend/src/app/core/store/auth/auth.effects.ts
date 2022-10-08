import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import {map, catchError, exhaustMap, mergeMap } from 'rxjs/operators';

import {of } from 'rxjs';
import {
  loginEvent,
  loginSuccessEvent,
  loginFailureEvent, logoutEvent,
} from './auth.actions';
import {AuthService} from "../../services/auth.service";
import {mapToLoggedInUser} from "../../mappers/auth.model.mapper";
import {Router} from "@angular/router";
import {UserSessionService} from "../../services/userSession.service";

@Injectable()
export class AuthenticationEffects {

    constructor(
        private _actions$: Actions,
        private _authenticationService: AuthService,
        private router : Router,
        private userSessionService:UserSessionService
    ) { }

  loginUsingEmailAndPassword$ = createEffect(() => this._actions$.pipe(
    ofType(loginEvent),
    exhaustMap(payload => this._authenticationService.login(payload.loginEventRequest.email, payload.loginEventRequest.password).pipe(
      map((authenticationResponse) =>   {
        //store jwt and tenantId in local storage
        this._authenticationService.saveToken(authenticationResponse.headers.get("Authorization"));
        //auto logout after jwt expiration
        this._authenticationService.autoLogout(this._authenticationService.getExpirationJwt());
        this.userSessionService.setUserSession(this._authenticationService.getEmail());
        let dto={
          "email":this._authenticationService.getEmail()
        }
        this._authenticationService.getTenantfromApi(this._authenticationService.getEmail()).subscribe(
          tenantid=>{
            console.log("entreravec succes")
            //log(tenantid)
            this._authenticationService.saveTenantId(tenantid);
          }
        )

        this._authenticationService.isAdmin()
        this._authenticationService.getIduserfromApi(this._authenticationService.getEmail()).subscribe(
          res=>{
            this.router.navigate(["/user/?id="+res])
            this._authenticationService.saveUserId(res)
          }
        )




        return  loginSuccessEvent({ authenticatedUser: (mapToLoggedInUser(authenticationResponse)) })}),
      catchError((err) => of(loginFailureEvent({ err: err }))))))
  )



  logout$ = createEffect(
    () => {
      return this._actions$.pipe(
        ofType(logoutEvent),
        map(()=> {
          this.router.navigate(["/login"])
        })
      );
    },
    { dispatch: false }
  );





/*  onLoginSuccess$ = createEffect(() => {
    return this._actions$.pipe(
      ofType(loginSuccessEvent),
      map(() => {
        // redirect to return url or home
        this.router.navigateByUrl("/home");
        return loginEvent();
      })
    );
  });*/









  /*  logout$ = createEffect(() => this._actions$.pipe(
        ofType(logoutEvent),
        exhaustMap(() => this._authenticationService.logout().pipe(
            map(() => logoutSuccessEvent()),
            catchError(() => of(logoutFailureEvent())))))
    )*/


}
