import { Injectable } from '@angular/core';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Expense} from './expense';
import {Category} from '../category/category';

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

  createNew(budgetId: number, categoryId: number, amount: string, date: Date): Observable<Expense> {
    return this.http.post<Expense>(this.url, {budgetId, categoryId, amount, date});
  }

}
