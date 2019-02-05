import {Component, OnInit} from '@angular/core';
import {Budget} from '../service/bugdet/budget';
import {BudgetService} from '../service/bugdet/budget.service';
import {CategoryService} from '../service/category/category.service';
import {ExpenseService} from '../service/expense/expense.service';
import {Expense} from '../service/expense/expense';
import {Category} from '../service/category/category';

@Component({
  selector: 'app-expenses',
  templateUrl: './expenses.component.html',
  styleUrls: ['./expenses.component.css']
})
export class ExpensesComponent implements OnInit {

  budgets: Budget[];
  expenses: Expense[];
  chosenBudget: Budget;
  chosenCategory: Category;
  amount: string;
  expenseDate: string;

  constructor(private budgetService: BudgetService, private expenseService: ExpenseService) {
  }

  ngOnInit() {
    this.getBudgets();
  }

  getBudgets(): void {
    this.budgetService.getBudgets().subscribe(b => {
      this.budgets = b;
    });
  }

  getExpenses(): void {
    this.expenseService.getExpenses(this.chosenBudget.budgetId).subscribe(e => this.expenses = e);
  }

  createNew() {
    this.expenseService.createNew(this.chosenBudget.budgetId, this.chosenCategory.categoryId, this.amount, new Date(this.expenseDate))
      .subscribe(e => {
        this.expenses.push(e);
        this.clearInputs();
      });
  }

  private clearInputs() {
    this.chosenCategory = undefined;
    this.amount = '';
    this.expenseDate = '';
  }
}
