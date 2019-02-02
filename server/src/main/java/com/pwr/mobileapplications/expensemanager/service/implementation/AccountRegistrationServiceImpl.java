package com.pwr.mobileapplications.expensemanager.service.implementation;

import com.pwr.mobileapplications.expensemanager.dto.AccountDto;
import com.pwr.mobileapplications.expensemanager.dto.AccountRegisterDto;
import com.pwr.mobileapplications.expensemanager.exception.AccountAlreadyExists;
import com.pwr.mobileapplications.expensemanager.model.Account;
import com.pwr.mobileapplications.expensemanager.repository.AccountRepository;
import com.pwr.mobileapplications.expensemanager.service.AccountRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountRegistrationServiceImpl implements AccountRegistrationService {

	private final AccountRepository accountRepository;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public AccountRegistrationServiceImpl(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
		this.accountRepository = accountRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public AccountDto register(AccountRegisterDto dto) {
		if (accountRepository.findByUsername(dto.getUsername()).isPresent()){
			throw new AccountAlreadyExists();
		}
		Account account = new Account();
		account.setUsername(dto.getUsername());
		account.setPassword(passwordEncoder.encode(dto.getPassword()));
		Account save = accountRepository.save(account);
		return AccountDto.from(save);
	}
}
