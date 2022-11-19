package com.rushchak.springbootlab.repository;

import com.rushchak.springbootlab.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    @Procedure("add_pack_of_clients")
    void addPackOfClients(String name, String surname);

    @Procedure("use_cursor")
    void useCursor();

}
