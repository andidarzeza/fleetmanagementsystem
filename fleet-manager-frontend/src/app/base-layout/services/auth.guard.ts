import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import { Observable } from 'rxjs';
import {AuthService} from "./auth.service";

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}



  //
  // canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
  //   const url: string = state.url;
  //
  //   return this.checkLogin(url);
  // }
  //
  // checkLogin(url: string): boolean {
  //   if (this.authService.isLoggedIn) { return true; }
  //
  //   // Store the attempted URL for redirecting
  //   this.authService.redirectUrl = url;
  //
  //   // Navigate to the login page with extras
  //   this.router.navigate(['/login']);
  //   return false;
  // }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    // const currentUser = this.authService.currentUserValue;
    // if(currentUser){
    //   //check if is restricted by role
    //   if(route.data.roles && route.data.roles.indexOf(currentUser.role)===-1){
    //     //role not authorised, redirect to home page
    //     this.router.navigate(['/']);
    //     return false;
    //   }
    //   //authorised so return true
    //   return true;
    // }
    // // not logged in so redirect to login page with the return url
    // this.router.navigate(['/login'], { queryParams: { returnUrl: state.url }});
    // return false;
    if(localStorage.getItem("token") == null){
      this.router.navigate(['/login']);
      return false;
    }else{
      return true;
    }

  }
}
