package com.rushchak.springbootlab.service;

import com.rushchak.springbootlab.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ClientService extends GeneralService<Client, Integer> {

    List<Client> findClientsByHomeAddressId(Integer homeAddressId);

}
