import {Component, OnInit} from '@angular/core';
import {Budget} from '../service/bugdet/budget';
import {ActivatedRoute} from '@angular/router';
import {BudgetService} from '../service/bugdet/budget.service';
import {Location} from '@angular/common';
import {Expense} from '../service/expense/expense';
import {ExpenseService} from '../service/expense/expense.service';
import {AccountService} from '../service/account/account.service';
import {Account} from '../service/account/account';

@Component({
  selector: 'app-budgetdetails',
  templateUrl: './budget-details.component.html',
  styleUrls: ['./budget-details.component.css']
})
export class BudgetDetailsComponent implements OnInit {

  budget: Budget;
  expenses: Expense[];
  accountsInBudget: Account[];
  unassignedAccounts: Account[];
  chosenAccount: Account;
  balance: number;

  constructor(
    private budgetService: BudgetService,
    private expenseService: ExpenseService,
    private accountService: AccountService,
    private route: ActivatedRoute,
    private location: Location
  ) {
  }

  ngOnInit() {
    this.getBudget();
    this.getAccountsInBudget();
    this.getUnassignedAccounts();
  }

  getBudget(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.budgetService.getBudget(id).subscribe(budget => {
      this.budget = budget;
      this.getExpenses();
    });
  }

  getExpenses(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.expenseService.getExpenses(id).subscribe(expense => {
      this.expenses = expense;
      this.balance = this.budget.expenditureLimit - expense.reduce((a, b) => a + b.amount, 0);
    });

  }

  getAccountsInBudget(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.accountService.getAccountsInBudget(id).subscribe(a => this.accountsInBudget = a);
  }

  getUnassignedAccounts(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.accountService.getUnassignedAccounts(id).subscribe(a => this.unassignedAccounts = a);
  }

  goBack(): void {
    this.location.back();
  }

  addAccount() {
    const id = +this.route.snapshot.paramMap.get('id');
    if (this.chosenAccount !== undefined) {
      this.accountService.addAccountToBudget(this.chosenAccount.accountId, id).subscribe(() => {
        this.ngOnInit();
      });
    }
  }
}
