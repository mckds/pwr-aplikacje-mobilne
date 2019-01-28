package com.pwr.mobileapplications.expensemanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountRegisterDto {
    private String username;
    private String password;
}
