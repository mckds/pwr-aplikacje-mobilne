import { BudgetService } from './../service/bugdet/budget.service';
import { Budget } from './../service/bugdet/budget';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-budget',
  templateUrl: './budget.component.html',
  styleUrls: ['./budget.component.css']
})
export class BudgetComponent implements OnInit {

  budget: Budget;

  constructor(private budgetService: BudgetService) { }

  ngOnInit() {
    this.getBudget();
  }

  getBudget(): void {
    this.budgetService.getBudget(1)
    .subscribe(budget => this.budget = budget);
  }

}
