import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {Category} from './category';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  url = `${environment.apiUrl}/categories`;

  constructor(private http: HttpClient) {
  }

  createBudget(name: string, budgetId: number): Observable<Category> {
    return this.http.post<Category>(this.url, {name, budgetId});
  }
}

