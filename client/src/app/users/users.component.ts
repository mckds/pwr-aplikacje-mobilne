import {Component, OnInit} from '@angular/core';
import {AccountService} from '../service/account/account.service';
import {Account} from '../service/account/account';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  accounts: Account[];

  constructor(private accountService: AccountService) {
  }

  ngOnInit() {
    this.getAccounts();
  }

  getAccounts(): void {
    this.accountService.getAccounts()
      .subscribe(accounts => this.accounts = accounts);
  }

}
