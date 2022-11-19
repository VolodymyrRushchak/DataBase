package com.rushchak.springbootlab.service.impl;

import com.rushchak.springbootlab.repository.ClientRepository;
import com.rushchak.springbootlab.service.ClientProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientProcedureServiceImpl implements ClientProcedureService {

    @Autowired
    ClientRepository clientRepository;

    public void addPackOfClients(String name, String surname) {
        clientRepository.addPackOfClients(name, surname);
    }

    public void useCursor() {
        clientRepository.useCursor();
    }

}
