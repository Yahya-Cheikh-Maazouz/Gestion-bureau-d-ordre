import { Component, OnInit } from '@angular/core';
import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {Router} from "@angular/router";

@Component({
  selector: 'app-manage-errors',
  templateUrl: './manage-errors.component.html',
  styleUrls: ['./manage-errors.component.scss']
})
export class ManageErrorsComponent implements OnInit, HttpInterceptor {
  private router: any;

  constructor() { }

  ngOnInit(): void {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return undefined as any;
  }

  /**
   * manage errors
   * @param err
   * @returns {any}
   */
/*  private handleAuthError(err: HttpErrorResponse): Observable<any> {
    //handle your auth error or rethrow
    if (err.status === 403) {
      //navigate /delete cookies or whatever
      console.log('handled error ' + err.status);
      this.router.navigate([`/login`]);
      // if you've caught / handled the error, you don't want to rethrow it unless you also want downstream consumers to have to handle it as well.
      return of(err.message);
    }
    throw err;
  }*/




}
