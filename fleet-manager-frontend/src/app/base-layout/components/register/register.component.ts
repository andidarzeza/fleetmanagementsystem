import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {AuthService} from "../../services/auth.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {catchError} from "rxjs/operators";
import {error} from "util";
import {HttpResponse} from "@angular/common/http";

@Component({
  selector: 'register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

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

  register(){
    this.authService.register(this.formGroup.value.username, this.formGroup.value.password)
        .subscribe((response:any)=>{
      console.log(response);
      this.router.navigate(['/login']);
    }, (error)=>{
          console.log(error);
          if(error.status===409){
            alert(error.error);}
            });
  }

}
