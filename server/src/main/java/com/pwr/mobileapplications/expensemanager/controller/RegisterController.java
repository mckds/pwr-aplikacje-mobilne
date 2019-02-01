package com.pwr.mobileapplications.expensemanager.controller;


import com.pwr.mobileapplications.expensemanager.dto.AccountDto;
import com.pwr.mobileapplications.expensemanager.dto.AccountRegisterDto;
import com.pwr.mobileapplications.expensemanager.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/register")
public class RegisterController {
    private final AccountService accountService;

    @Autowired
    public RegisterController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountDto> register(@RequestBody @Valid AccountRegisterDto registerDto) {
        return ResponseEntity.ok((accountService.save(registerDto)));
    }

}
