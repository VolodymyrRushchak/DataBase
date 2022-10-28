package com.rushchak.jdbc_template_lab.service;

import com.rushchak.jdbc_template_lab.domain.Client;

import java.util.Optional;

public interface ClientService extends GeneralService<Client, Integer> {

    Optional<Client> findBySurname(String surname);

    Optional<Client> findByName(String name);

}
