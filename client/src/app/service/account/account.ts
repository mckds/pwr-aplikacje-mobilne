import {Expense} from '../expense/expense';

export class Account {
  accountId: number;
  username: String;
  expenses: Expense[];
  expensesValue: number;
}
