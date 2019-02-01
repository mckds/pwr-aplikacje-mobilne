package com.pwr.mobileapplications.expensemanager.service.implementation;

import com.pwr.mobileapplications.expensemanager.dto.AccountDto;
import com.pwr.mobileapplications.expensemanager.dto.AccountRegisterDto;
import com.pwr.mobileapplications.expensemanager.exception.AccountNotFoundException;
import com.pwr.mobileapplications.expensemanager.model.Account;
import com.pwr.mobileapplications.expensemanager.repository.AccountRepository;
import com.pwr.mobileapplications.expensemanager.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public AccountDto findByUsername(String username) {
        return AccountDto.from(findAccountByUserName(username));
    }

    @Override
    public List<AccountDto> findAll() {
        List<AccountDto> accounts = new ArrayList<>();
        accountRepository.findAll().forEach(account -> accounts.add(AccountDto.from(account)));
        return accounts;
    }

    @Override
    public AccountDto save(AccountRegisterDto dto) {
        Account account = new Account();
        account.setUsername(dto.getUsername());
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return AccountDto.from(accountRepository.save(account));
    }

    @Override
    public AccountDto deleteByName(String name) {
        Account account = findAccountByUserName(name);
        accountRepository.delete(account);
        return AccountDto.from(account);
    }

    private Account findAccountByUserName(String username) {
        return accountRepository.findByUsername(username)
                .orElseThrow(() ->  new AccountNotFoundException("Account `" + username + "` not found."));
    }
}
