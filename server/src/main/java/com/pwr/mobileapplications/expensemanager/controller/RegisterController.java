package com.pwr.mobileapplications.expensemanager.controller;


import com.pwr.mobileapplications.expensemanager.dto.AccountRegisterDto;
import com.pwr.mobileapplications.expensemanager.dto.IdDto;
import com.pwr.mobileapplications.expensemanager.model.Account;
import com.pwr.mobileapplications.expensemanager.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/register")
public class RegisterController {
    private final AccountService accountService;

    @Autowired
    public RegisterController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IdDto register(@RequestBody AccountRegisterDto registerDto) {
        return new IdDto(accountService.save(
                new Account(registerDto.getUsername(),
                        registerDto.getPassword()))
                .getAccountId());
    }

}
