package com.pwr.mobileapplications.expensemanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AccountAlreadyExists extends RuntimeException {
	public AccountAlreadyExists() {
		super("Account already exists");
	}

	public AccountAlreadyExists(String message) {
		super(message);
	}
}
