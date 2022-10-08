import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor, HttpHeaders
} from '@angular/common/http';
import { Observable } from 'rxjs';
import {AuthService} from "../services/auth.service";

@Injectable()
export class JwtInterceptor implements HttpInterceptor {

  constructor(private authService :AuthService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const token = this.authService.getToken();
    const tenantId = this.authService.getTenantId();
    if (token&&tenantId) {
    const requestWithToken = request.clone({
      setHeaders: ({
        Authorization: token
        , XTenantID:tenantId,
      })
    });
      return next.handle(requestWithToken);
    }
   return next.handle(request);
  }
}
