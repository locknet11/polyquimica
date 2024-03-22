import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { LocalStorageService } from '../services/local-storage.service';
import { Injectable } from '@angular/core';

@Injectable()
export class RequestInterceptor implements HttpInterceptor {
  constructor(private localStorageService: LocalStorageService) {}
  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    // get token and insert in header
    const token = this.localStorageService.getToken();
    if (token) {
      req = req.clone({
        setHeaders: {
          Authorization: token,
        },
      });
    }

    return next.handle(req);
  }
}
