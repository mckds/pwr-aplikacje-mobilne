package com.pwr.mobileapplications.expensemanager.service;

import com.pwr.mobileapplications.expensemanager.model.Account;

public interface AccountService extends AbstractCrudService<Account, Long> {
    Account findByUsername(String username);
}
