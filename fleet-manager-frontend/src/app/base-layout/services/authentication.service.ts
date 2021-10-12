import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private http:HttpClient) { }

  login(user) {
    return this.http.post("http://localhost:8080/user/login", user);
  }

  register(user){
    return this.http.post("http://localhost:8080/user/register", user);
  }

}
