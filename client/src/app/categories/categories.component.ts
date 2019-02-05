import {Component, OnInit} from '@angular/core';
import {Budget} from '../service/bugdet/budget';
import {BudgetService} from '../service/bugdet/budget.service';
import {CategoryService} from '../service/category/category.service';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css']
})
export class CategoriesComponent implements OnInit {

  budgets: Budget[];
  chosenBudget: Budget;
  catName: string;

  constructor(private budgetService: BudgetService, private categoryService: CategoryService) {
  }

  ngOnInit() {
    this.getBudgets();
  }

  getBudgets(): void {
    this.budgetService.getBudgets().subscribe(b => {
      this.budgets = b;
      if (b.length > 0) {
        this.chosenBudget = b[0];
      }
    });
  }

  createNew() {
    this.categoryService.createBudget(this.catName, this.chosenBudget.budgetId).subscribe(b => this.chosenBudget.categories.push(b));
  }
}
