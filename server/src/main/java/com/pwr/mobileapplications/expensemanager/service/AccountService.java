package com.pwr.mobileapplications.expensemanager.service;

import com.pwr.mobileapplications.expensemanager.dto.AccountDto;
import com.pwr.mobileapplications.expensemanager.dto.AccountRegisterDto;

import java.util.List;

public interface AccountService {
    AccountDto findByUsername(String username);
    List<AccountDto> findAll();
    AccountDto save(AccountRegisterDto dto);
    AccountDto deleteByName(String name);
}
