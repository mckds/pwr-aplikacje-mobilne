package com.pwr.mobileapplications.expensemanager.service.implementation;

import com.pwr.mobileapplications.expensemanager.dto.AccountDto;
import com.pwr.mobileapplications.expensemanager.exception.AccountNotFoundException;
import com.pwr.mobileapplications.expensemanager.model.Account;
import com.pwr.mobileapplications.expensemanager.repository.AccountRepository;
import com.pwr.mobileapplications.expensemanager.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto findById(Long id) {
        return AccountDto.from(accountRepository.findById(id)
                .orElseThrow(() ->  new AccountNotFoundException("Account `Id:" + id + "` not found.")));
    }

    @Override
    public List<AccountDto> findAll() {
        return mapToDto(accountRepository.findAll());
    }

    @Override
    public AccountDto findByName(String name) {
        return AccountDto.from(accountRepository.findByUsername(name)
                .orElseThrow(() ->  new AccountNotFoundException("Account `" + name + "` not found.")));
    }

    private List<AccountDto> mapToDto(List<Account> accounts){
        List<AccountDto> accountsDtoList = new ArrayList<>();
        accounts.forEach(account -> accountsDtoList.add(AccountDto.from(account)));
        return accountsDtoList;
    }

}
