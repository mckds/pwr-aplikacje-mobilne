package com.pwr.mobileapplications.expensemanager.controller;


import com.pwr.mobileapplications.expensemanager.dto.AccountDto;
import com.pwr.mobileapplications.expensemanager.dto.AccountRegisterDto;
import com.pwr.mobileapplications.expensemanager.service.AccountRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/register")
class RegisterController {
    private final AccountRegistrationService accountRegistrationService;

    @Autowired
    public RegisterController(AccountRegistrationService accountRegistrationService) {
        this.accountRegistrationService = accountRegistrationService;
    }

    @PostMapping
    public ResponseEntity<AccountDto> register(@RequestBody @Valid AccountRegisterDto registerDto) {
        return new ResponseEntity<>(accountRegistrationService.register(registerDto), HttpStatus.CREATED);
    }

}
