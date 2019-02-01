package com.pwr.mobileapplications.expensemanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class AccountRegisterDto {

    @NotNull
    private String username;
    @NotNull
    private String password;
}
