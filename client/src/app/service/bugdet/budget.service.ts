import {Budget} from './budget';
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class BudgetService {

  url = `${environment.apiUrl}/budgets`;

  constructor(private http: HttpClient) {
  }

  getBudget(id: number): Observable<Budget> {
    const url = `${this.url}/${id}`;
    return this.http.get<Budget>(url);
  }

  getBudgets(): Observable<Budget[]> {
    return this.http.get<Budget[]>(this.url);
  }

  createBudget(name: string, startDate: Date, endDate: Date, expenditureLimit: string): Observable<Budget> {
    return this.http.post<Budget>(this.url, {name, startDate, endDate, expenditureLimit});
  }

}

