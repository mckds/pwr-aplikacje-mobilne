package com.pwr.mobileapplications.expensemanager.service;

import com.pwr.mobileapplications.expensemanager.model.Account;

public interface AccountService {
    Account findByUsername(String username);
}
