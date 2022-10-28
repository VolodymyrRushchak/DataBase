package com.rushchak.jdbc_template_lab.dao;

import com.rushchak.jdbc_template_lab.domain.Client;

import java.util.Optional;

public interface ClientDao extends GeneralDao<Client, Integer> {

    Optional<Client> findBySurname(String surname);

    Optional<Client> findByName(String name);

}
