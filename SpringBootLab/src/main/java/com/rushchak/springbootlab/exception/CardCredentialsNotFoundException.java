package com.rushchak.springbootlab.exception;

public class CardCredentialsNotFoundException extends RuntimeException {
    public CardCredentialsNotFoundException (Integer id) {
        super("Could not find 'card credentials' with id=" + id);
    }
}
