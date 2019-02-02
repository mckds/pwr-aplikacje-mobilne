package com.pwr.mobileapplications.expensemanager.service;

import com.pwr.mobileapplications.expensemanager.dto.AccountDto;

import java.util.List;

public interface AccountService {

    AccountDto findById(Long id);
    List<AccountDto> findAll();
    AccountDto findByName(String dto);

}