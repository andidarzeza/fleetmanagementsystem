import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, NgForm, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {AuthService} from "../../services/auth.service";
import {User} from "../../models/User";
import * as jwt_decode from "jwt-decode";

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  username;
  password;
  formGroup: FormGroup;
  constructor(private router: Router, public authService:AuthService,
              private formBuilder:FormBuilder) {
    this.formGroup = this.formBuilder.group({
      username: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
  }


  ngOnInit() {
  }

  login(){
    this.authService.login(this.formGroup.value.username, this.formGroup.value.password).
    subscribe((response:any) => {
      localStorage.setItem("token", response.token);
      this.authService.isLoggedIn = true;
      this.router.navigate(["/panel"]);
    }, (error: any) => {
          if(error.status == 401){
            alert(error.error);
          }
        });
  }

}
