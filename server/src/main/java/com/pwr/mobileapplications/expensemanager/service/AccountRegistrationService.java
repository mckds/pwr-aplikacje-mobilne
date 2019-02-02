package com.pwr.mobileapplications.expensemanager.service;

import com.pwr.mobileapplications.expensemanager.dto.AccountDto;
import com.pwr.mobileapplications.expensemanager.dto.AccountRegisterDto;

public interface AccountRegistrationService {

	AccountDto register(AccountRegisterDto dto);

}
