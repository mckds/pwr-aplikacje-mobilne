import { Component, OnInit } from '@angular/core';
import { AuthService } from '../service/auth.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  constructor(private router: Router, private authService: AuthService) { }

  ngOnInit() {
    if(this.isAlreadyLoggedIn()) {
      this.router.navigate(['/']);
    }
  }

  async onSignIn(username: string, password: string) {
    const result = await this.authService.login(username, password);
    if(result) {
      this.router.navigate(['/']);
    }
  }

  async onSignUp(username: string, password: string) {
    const result = await this.authService.register(username, password);
    if(result) {
      this.router.navigate(['/']);
    }
  }


  isAlreadyLoggedIn() {
    return localStorage.getItem("currentUser");
  }

}
