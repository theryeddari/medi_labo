import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // Récupérer les identifiants depuis le localStorage
    const authCredentials = localStorage.getItem('authCredentials');

    let authReq = req;
    if (authCredentials) {
      authReq = req.clone({
        setHeaders: {
          Authorization: 'Basic ' + authCredentials
        }
      });
    }

    return next.handle(authReq);
  }
}
