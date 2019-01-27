package com.pwr.mobileapplications.expensemanager.service.implementation;

import com.pwr.mobileapplications.expensemanager.exception.AccountNotFoundException;
import com.pwr.mobileapplications.expensemanager.model.Account;
import com.pwr.mobileapplications.expensemanager.repository.AccountRepository;
import com.pwr.mobileapplications.expensemanager.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account findByUsername(String username) {
        return accountRepository.findByUsername(username)
                .orElseThrow(() ->  new AccountNotFoundException("Account `" + username + "` not found."));
    }
}
