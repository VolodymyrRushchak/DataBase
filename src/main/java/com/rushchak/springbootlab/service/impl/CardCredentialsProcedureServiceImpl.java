package com.rushchak.springbootlab.service.impl;

import com.rushchak.springbootlab.repository.CardCredentialsRepository;
import com.rushchak.springbootlab.service.CardCredentialsProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CardCredentialsProcedureServiceImpl implements CardCredentialsProcedureService {

    @Autowired
    CardCredentialsRepository cardCredentialsRepository;

    @Override
    public void addCardCredentials(String card_number, String cvv, String pin) {
        cardCredentialsRepository.addCardCredentials(card_number, cvv, pin);
    }
}
