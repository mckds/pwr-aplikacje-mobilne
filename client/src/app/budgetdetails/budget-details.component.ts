import { Component, OnInit } from '@angular/core';
import {Budget} from '../service/bugdet/budget';
import {ActivatedRoute} from '@angular/router';
import {BudgetService} from '../service/bugdet/budget.service';
import { Location } from '@angular/common';
import {Expense} from '../service/expense/expense';
import {ExpenseService} from '../service/expense/expense.service';

@Component({
  selector: 'app-budgetdetails',
  templateUrl: './budget-details.component.html',
  styleUrls: ['./budget-details.component.css']
})
export class BudgetDetailsComponent implements OnInit {

  budget: Budget;
  expenses: Expense[];
  balance: number;

  constructor(
    private budgetService: BudgetService,
    private expenseService: ExpenseService,
    private route: ActivatedRoute,
    private location: Location
  ) { }

  ngOnInit() {
    this.getBudget();
    this.getExpenses();
  }

  getBudget(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.budgetService.getBudget(id).subscribe(budget => this.budget = budget);
  }
  getExpenses(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.expenseService.getExpenses(id).subscribe(expense => this.expenses = expense);
  }


  goBack(): void {
    this.location.back();
  }
}
