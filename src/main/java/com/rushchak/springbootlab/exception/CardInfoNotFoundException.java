package com.rushchak.springbootlab.exception;

public class CardInfoNotFoundException extends RuntimeException {
    public CardInfoNotFoundException(Integer id) {
        super("Could not find 'card info' with id=" + id);
    }
}