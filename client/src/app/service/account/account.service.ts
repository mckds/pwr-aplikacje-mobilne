import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Account} from './account';
import { environment } from '../../../environments/environment';


@Injectable({
  providedIn: 'root'
})
export class AccountService {

  url = `${environment.apiUrl}/accounts`;

  constructor(private http: HttpClient) { }

  getAccount(name: string): Observable<Account> {
    const url = `${this.url}/${name}`;
    return this.http.get<Account>(url);
  }

  getAccounts(): Observable<Account[]> {
    return this.http.get<Account[]>(this.url);
  }
}
