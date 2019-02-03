import { Component, OnInit } from '@angular/core';
import {Budget} from '../service/bugdet/budget';
import {ActivatedRoute} from '@angular/router';
import {BudgetService} from '../service/bugdet/budget.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-budgetdetails',
  templateUrl: './budgetdetails.component.html',
  styleUrls: ['./budgetdetails.component.css']
})
export class BudgetdetailsComponent implements OnInit {

  budget: Budget;

  constructor(
    private budgetService: BudgetService,
    private route: ActivatedRoute,
    private location: Location
  ) { }

  ngOnInit() {
    this.getBudget();
  }

  getBudget(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.budgetService.getBudget(id).subscribe(budget => this.budget = budget);
  }

  goBack(): void {
    this.location.back();
  }
}
