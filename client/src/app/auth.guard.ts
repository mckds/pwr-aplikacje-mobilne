import { Injectable } from "@angular/core";
import { Router, ActivatedRouteSnapshot, RouterStateSnapshot, CanActivate } from '@angular/router';


@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {
    constructor(private router: Router) { 

    }


    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        
        // if logged in
        if (localStorage.getItem("currentUser")) {
            return true;
        }

        // else navigate to login screen
        this.router.navigate(['/login']);
        return false;
    }
}