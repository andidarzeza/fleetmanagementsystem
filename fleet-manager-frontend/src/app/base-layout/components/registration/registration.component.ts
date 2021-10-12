import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { AuthenticationService } from '../../services/authentication.service';

@Component({
  selector: 'registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {

  formGroup: FormGroup;
  private username;
  private password;

  constructor(private formBuilder: FormBuilder, private authentication: AuthenticationService) {
    this.formGroup = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }
  ngOnInit(): void {
  }

  register(){
    this.authentication.register({username: this.formGroup.value.username, password: this.formGroup.value.password})
      .subscribe((response => {
        console.log(response);
      }));
  }


}
