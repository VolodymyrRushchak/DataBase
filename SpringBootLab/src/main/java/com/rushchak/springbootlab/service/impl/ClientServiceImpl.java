package com.rushchak.springbootlab.service.impl;


import com.rushchak.springbootlab.domain.Client;
import com.rushchak.springbootlab.domain.HomeAddress;
import com.rushchak.springbootlab.exception.ClientNotFoundException;
import com.rushchak.springbootlab.exception.HomeAddressNotFoundException;
import com.rushchak.springbootlab.repository.ClientRepository;
import com.rushchak.springbootlab.repository.HomeAddressRepository;
import com.rushchak.springbootlab.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private HomeAddressRepository homeAddressRepository;

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client findById(Integer id) {
        return clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
    }

    @Override
    @Transactional
    public Client create(Client client) {
        clientRepository.save(client);
        return client;
    }

    @Override
    @Transactional
    public void update(Integer id, Client updClient) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));
        client.setName(updClient.getName());
        client.setSurname(updClient.getSurname());
        client.setPhoneNumber(updClient.getPhoneNumber());
        client.setEmail(updClient.getEmail());
        client.setHomeAddress(updClient.getHomeAddress());
        clientRepository.save(client);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));
        clientRepository.delete(client);
    }

    public List<Client> findClientsByHomeAddressId(Integer homeAddressId){
        HomeAddress homeAddress = homeAddressRepository.findById(homeAddressId).orElseThrow(() -> new HomeAddressNotFoundException(homeAddressId));
        return homeAddress.getClients().stream().toList();
    }

}
