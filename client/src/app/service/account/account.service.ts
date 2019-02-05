import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Account} from './account';
import {environment} from '../../../environments/environment';


@Injectable({
  providedIn: 'root'
})
export class AccountService {

  url = `${environment.apiUrl}/budgets`;

  constructor(private http: HttpClient) {
  }


  getAccountsInBudget(budgetId: number): Observable<Account[]> {
    return this.http.get<Account[]>(`${this.url}/${budgetId}/accounts`);
  }

  getUnassignedAccounts(budgetId: number): Observable<Account[]> {
    return this.http.get<Account[]>(`${this.url}/${budgetId}/unassigned`);
  }

  addAccountToBudget(accountId: number, budgetId: number): Observable<Account> {
    return this.http.post<Account>(`${this.url}/${budgetId}/accounts`, {accountId, budgetId});
  }
}
