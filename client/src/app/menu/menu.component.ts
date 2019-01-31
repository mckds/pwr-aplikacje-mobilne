import { Component, OnInit } from '@angular/core';
import { AuthService, UserCredentials } from '../service/auth.service';
import { Observable, Subscription } from 'rxjs';
import { Router } from '@angular/router';


@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  public user: UserCredentials;
  private userSubscription: Subscription;

  constructor(private router: Router, private authService: AuthService) {
    this.authService.getUser().subscribe(user => {
      this.user = user;
    });

    this.user = JSON.parse(localStorage.getItem("currentUser"));
  }

  onLogout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  ngOnInit() {
 
  }

  ngOnDestro() {
    this.userSubscription.unsubscribe();
  }

  onToggleSelected(toggle: string) {
    this.router.navigate([toggle]);
  }

}
