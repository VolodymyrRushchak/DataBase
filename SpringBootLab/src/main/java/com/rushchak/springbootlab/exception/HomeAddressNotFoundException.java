package com.rushchak.springbootlab.exception;

public class HomeAddressNotFoundException extends RuntimeException {
    public HomeAddressNotFoundException (Integer id) {
        super("Could not find 'home address' with id=" + id);
    }
}