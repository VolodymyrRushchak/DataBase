package com.rushchak.springbootlab.exception;

public class CardNotFoundException extends RuntimeException {
    public CardNotFoundException (Integer id) {
        super("Could not find 'card' with id=" + id);
    }
}