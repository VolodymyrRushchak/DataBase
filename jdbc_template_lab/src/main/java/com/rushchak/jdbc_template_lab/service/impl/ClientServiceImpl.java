package com.rushchak.jdbc_template_lab.service.impl;


import com.rushchak.jdbc_template_lab.dao.ClientDao;
import com.rushchak.jdbc_template_lab.domain.Client;
import com.rushchak.jdbc_template_lab.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientDao clientDao;

    @Override
    public List<Client> findAll() {
        return clientDao.findAll();
    }

    @Override
    public Optional<Client> findById(Integer id) {
        return clientDao.findById(id);
    }

    @Override
    public int create(Client client) {
        return clientDao.create(client);
    }

    @Override
    public int update(Integer id, Client client) {
        return clientDao.update(id, client);
    }

    @Override
    public int delete(Integer id) {
        return clientDao.delete(id);
    }

    @Override
    public Optional<Client> findBySurname(String surname) {
        return clientDao.findBySurname(surname);
    }

    @Override
    public Optional<Client> findByName(String name) {
        return clientDao.findByName(name);
    }
    
}
