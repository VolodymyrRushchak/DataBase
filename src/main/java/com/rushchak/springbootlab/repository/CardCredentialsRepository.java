package com.rushchak.springbootlab.repository;

import com.rushchak.springbootlab.domain.CardCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CardCredentialsRepository extends JpaRepository<CardCredentials, Integer> {

    @Procedure("add_card_cred")
    void addCardCredentials(String card_number, String cvv, String pin);

}
