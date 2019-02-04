import { BudgetService } from '../service/bugdet/budget.service';
import { Budget } from '../service/bugdet/budget';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-budget',
  templateUrl: './budget.component.html',
  styleUrls: ['./budget.component.css']
})
export class BudgetComponent implements OnInit {

  budgets: Budget[];
  budgetName: String;
  constructor(private budgetService: BudgetService) { }

  ngOnInit() {
    this.getBudgets();
  }

  getBudgets(): void {
    this.budgetService.getBudgets()
    .subscribe(budgets => this.budgets = budgets);
  }

  createBudget(budgetName: string, startDate: string, endDate: string, expenditureLimit: string) {
    this.budgetService.createBudget(budgetName, new Date(startDate), new Date(endDate), expenditureLimit)
      .subscribe(() => { this.ngOnInit(); });
  }
}
