import {BudgetService} from '../service/bugdet/budget.service';
import {Budget} from '../service/bugdet/budget';
import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-budget',
  templateUrl: './budget.component.html',
  styleUrls: ['./budget.component.css']
})
export class BudgetComponent implements OnInit {

  budgets: Budget[];
  budgetName: string;
  expenditureLimit: string;
  sDate: string;
  eDate: string;

  constructor(private budgetService: BudgetService) {
  }

  ngOnInit() {
    this.getBudgets();
  }

  getBudgets(): void {
    this.budgetService.getBudgets()
      .subscribe(budgets => this.budgets = budgets);
  }

  createBudget() {
    this.budgetService.createBudget(this.budgetName, new Date(this.sDate), new Date(this.eDate), this.expenditureLimit)
      .subscribe(b => {
        this.budgets.push(b);
        this.clear();
        alert('Budget added');
      });
  }

  private clear() {
    this.budgetName = '';
    this.sDate = '';
    this.eDate = '';
    this.expenditureLimit = '';
  }
}
