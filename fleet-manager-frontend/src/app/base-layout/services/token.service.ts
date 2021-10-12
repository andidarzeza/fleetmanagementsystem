import { Injectable } from '@angular/core';
import {
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
  HttpResponse
} from "@angular/common/http";
import {Router} from "@angular/router";
import {Observable, throwError} from "rxjs";
import {catchError, map} from "rxjs/operators";
import {AuthService} from "./auth.service";


@Injectable({
  providedIn: 'root'
})
export class TokenService implements HttpInterceptor{

  constructor(private router: Router,private authService:AuthService) { }
  intercept(request: HttpRequest<any>, next: HttpHandler) : Observable<HttpEvent<any>> {
    console.log("Before intercept");
    const token = localStorage.getItem("token");
    if(token !== null){
      console.log(token);
      request = request.clone({
        setHeaders: {Authorization: 'Bearer ' + token}
      });
    }else{
      console.log("Token is not avaible");
    }
    return next.handle(request);
  }
}
