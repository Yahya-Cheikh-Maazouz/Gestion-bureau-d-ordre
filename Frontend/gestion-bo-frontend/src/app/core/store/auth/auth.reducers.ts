import { createReducer, on } from '@ngrx/store';
import initialLoggedInUserState from './auth.state';
import {
  loginEvent, loginSuccessEvent, loginFailureEvent, logoutEvent,
} from './auth.actions';

import ILoggedInUser from "../../models/ILoggedInUser";
import { TypedAction } from '@ngrx/store/src/models';
import {Router} from "@angular/router";



export const _authReducer = createReducer(initialLoggedInUserState,
  on(loginEvent, state =>   login(state) ),
  on(logoutEvent, state =>   logout(state) ),
  on(loginSuccessEvent, (state, user) => loginSuccess(state, user)),
  on(loginFailureEvent, (state, user) => loginFailure(state, user))
);




function login(state: ILoggedInUser): ILoggedInUser {
  return {...state}
}

function logout(state: ILoggedInUser): ILoggedInUser {
  alert("user logout")
  localStorage.clear()
  return {...state,isLoggedIn:false}
}

function loginSuccess(state: ILoggedInUser, input:any): ILoggedInUser {
  alert("login successful")
  return {
    ...state,
    email: input.authenticatedUser.email,
    displayName: input.authenticatedUser.displayName || input.authenticatedUser.email,
    token: input.authenticatedUser.token,
    isLoggedIn: true
  }
}



function loginFailure(state:ILoggedInUser , error:any): ILoggedInUser {
  return { ...state, error: { code: error.err.code, message: error.err.message }, isLoggedIn: false }
}




