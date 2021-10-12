import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {BehaviorSubject, Observable, of} from 'rxjs';
import {Router} from "@angular/router";
import {User} from "../models/User";


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private currentUserSubject: BehaviorSubject<User>;
  public currentUser: Observable<User>;


  isLoggedIn = false;
  loggedUser: User = null;

  constructor(private http: HttpClient, private router: Router) {
    if(localStorage.getItem("token") != null) this.isLoggedIn = true;
    this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
  }
  public get currentUserValue(): User {
    return this.currentUserSubject.value;
  }
  login(username, password) {
    return this.http.post("http://localhost:8080/user/login", {username: username, password: password});
  }

  register(username, password){
    return this.http.post("http://localhost:8080/user/register", {username: username, password: password});
  }

  logout() {
    // remove user from local storage to log user out
    if(localStorage.getItem("token") !=null){
      localStorage.removeItem('token');
      this.isLoggedIn = false;
      this.loggedUser = null;
      this.router.navigate(["/login"]);
      this.currentUserSubject.next(null);
    }
  }










  // login(data: any): Observable<any> {
  //   return this.http.post<any>(apiUrl + 'login', data)
  //       .pipe(
  //           tap(_ => this.isLoggedIn = true),
  //           catchError(this.handleError('log', []))
  //       );
  // }
  //
  //
  // register(data: any): Observable<any> {
  //   return this.http.post<any>(apiUrl + 'register', data)
  //       .pipe(
  //           tap(_ => this.log('login')),
  //           catchError(this.handleError('log', []))
  //       );
  // }
  //
  // private handleError<T>(operation = 'operation', result?: T) {
  //   return (error: any): Observable<T> => {
  //
  //     console.error(error); // log to console instead
  //     this.log(`${operation} failed: ${error.message}`);
  //
  //     return of(result as T);
  //   };
  // }
  //
  // private log(message: string) {
  //   console.log(message);
  // }
}
