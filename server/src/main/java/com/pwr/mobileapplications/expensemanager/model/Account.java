package com.pwr.mobileapplications.expensemanager.model;


import javax.persistence.Entity;


public class Account {
    private long accountId;
    private String username;
    private String password;

    private Budget budget;
}
