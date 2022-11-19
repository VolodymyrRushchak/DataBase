package com.rushchak.springbootlab.exception;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(Integer id) {
        super("Could not find 'account' with id=" + id);
    }
}
