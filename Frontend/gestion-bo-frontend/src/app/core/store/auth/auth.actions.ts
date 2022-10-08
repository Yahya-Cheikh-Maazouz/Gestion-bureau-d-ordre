import { createAction, props } from '@ngrx/store';
import {
  LOGIN_EVENT,
  LOGIN_EVENT_SUCCESS,
  LOGIN_EVENT_FAILURE, LOGIN_SESSION_VALIDITY_EVENT_SUCCESS, LOGOUT_EVENT
} from './auth.events';
import ILoginRequest from "../../models/ILoginRequest";


export const loginEvent = createAction(LOGIN_EVENT, props<{ loginEventRequest: ILoginRequest }>());
export const logoutEvent = createAction(LOGOUT_EVENT);
export const loginSuccessEvent = createAction(LOGIN_EVENT_SUCCESS, props<{ authenticatedUser: any }>());
export const loginFailureEvent = createAction(LOGIN_EVENT_FAILURE, props<{ err: any }>());

