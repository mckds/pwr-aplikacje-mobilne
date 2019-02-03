import { Injectable } from '@angular/core';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Expense} from './expense';

@Injectable({
  providedIn: 'root'
})
export class ExpenseService {

  url = `${environment.apiUrl}/expenses`;

  constructor(private http: HttpClient) { }

  getExpenses(id: number): Observable<Expense[]> {
    const url = `${this.url}/budgets/${id}`;
    return this.http.get<Expense[]>(url);
  }

}
