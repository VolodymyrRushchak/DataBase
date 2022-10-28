package com.rushchak.jdbc_template_lab.controller;

import com.rushchak.jdbc_template_lab.domain.Client;

import java.util.Optional;

public interface ClientController extends GeneralController<Client, Integer> {

    Optional<Client> findBySurname(String surname);

    Optional<Client> findByName(String name);

}
