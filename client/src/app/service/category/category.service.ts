import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  url = `${environment.apiUrl}/categories`;

  constructor(private http: HttpClient) {
  }

  createBudget(name: string, budgetId: number) {
    return this.http.post<any>(this.url, {name, budgetId}, {observe: 'response'});
  }
}

