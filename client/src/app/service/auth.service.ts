import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { environment } from "../../environments/environment";
import { Subject, Observable } from 'rxjs';



/**
 * User credentials saved in localStorage
 */
export class UserCredentials {
    username: string;
    password: string;
    token: string;
}

/**
 * Perfors various authentication related tasks
 */
@Injectable({ providedIn: 'root' })
export class AuthService {
    private user = new Subject<UserCredentials>();

    constructor(private http: HttpClient) { }

    /**
     * Gets JWT token from response header if successfuly authorized
     * And saves user's credentials to localStorage
     * @param username user's username for signing in
     * @param password user's password for signing in
     */
    async login(username: string, password: string) {

        // make request to backend
        const result = await this.http.post<any>(`${environment.apiUrl}/login`, {username, password}, {observe: 'response'})
        .toPromise();

        // check if request was succesful
        if(result && result.status == 200 && result.headers.has("Authorization")) {
            const user: UserCredentials = {
                username,
                password,
                token: result.headers.get("Authorization")
            }

            // save credentials
            localStorage.setItem("currentUser", JSON.stringify(user));

            // notify observers
            this.user.next(user); 

            return user;
        } else {
            return undefined;
        }
    }

    /**
     * Registers user if possible
     * @param username new user's username
     * @param password new user's password
     */
    async register(username: string, password: string) {
        // make request to backend
        const registerResult = await this.http.post<any>(`${environment.apiUrl}/register`, {username, password}).toPromise();

        // if request was succesful
        if(registerResult) {
            // then login user after registration
            const loginResult = await this.login(username, password);
            return loginResult;
        } else {
            return undefined;
        }
    }

    /**
     * Retruns user observable
     * @returns user observable
     */
    getUser(): Observable<UserCredentials> {
        return this.user.asObservable();
    }
    /**
     * Removes user credentials from local storage
     */
    logout() {
        // remove from local storage
        localStorage.removeItem("currentUser");

        // notify observers about logged out user
        this.user.next(undefined);
    }

    /**
     * Tells whether user is logged in
     * @returns is user logged in
     */
    isLoggedIn() {
        return localStorage.getItem("currentUser");
    }
}