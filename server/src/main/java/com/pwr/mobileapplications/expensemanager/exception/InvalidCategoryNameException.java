package com.pwr.mobileapplications.expensemanager.exception;

public class InvalidCategoryNameException extends RuntimeException {
	public InvalidCategoryNameException() {
		super("Name cannot be null or empty");
	}
}
