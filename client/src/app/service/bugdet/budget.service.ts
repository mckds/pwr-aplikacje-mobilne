import { Budget } from './budget';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class BudgetService {

  url = 'http://localhost:8080/api/budgets';

  constructor(
    private http: HttpClient
  ) { }

  getBudget(id: number): Observable<Budget> {
    const url = `${this.url}/${id}`;
    return this.http.get<Budget>(url).pipe(
      catchError(this.handleError<Budget>(`getBudget id=${id}`))
    );
  }

  getBudgets(): Observable<Budget[]> {
    const url = `${this.url}`;
    return this.http.get<Budget[]>(url);
  }

  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead
      // TODO: better job of transforming error for user consumption
      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
