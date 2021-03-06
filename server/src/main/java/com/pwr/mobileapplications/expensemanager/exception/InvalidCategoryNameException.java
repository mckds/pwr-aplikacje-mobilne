package com.pwr.mobileapplications.expensemanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidCategoryNameException extends RuntimeException {
	public InvalidCategoryNameException() {
		super("Name cannot be null or empty");
	}
}
