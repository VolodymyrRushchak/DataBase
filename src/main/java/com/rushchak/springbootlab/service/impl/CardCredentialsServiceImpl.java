package com.rushchak.springbootlab.service.impl;

import com.rushchak.springbootlab.domain.CardCredentials;
import com.rushchak.springbootlab.exception.CardCredentialsNotFoundException;
import com.rushchak.springbootlab.repository.CardCredentialsRepository;
import com.rushchak.springbootlab.service.CardCredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CardCredentialsServiceImpl implements CardCredentialsService {

    @Autowired
    private CardCredentialsRepository cardCredentialsRepository;

    @Override
    public List<CardCredentials> findAll() {
        return cardCredentialsRepository.findAll();
    }

    @Override
    public CardCredentials findById(Integer id) {
        return cardCredentialsRepository.findById(id).orElseThrow(() -> new CardCredentialsNotFoundException(id));
    }

    @Override
    @Transactional
    public CardCredentials create(CardCredentials cardCredentials) {
        cardCredentialsRepository.save(cardCredentials);
        return cardCredentials;
    }

    @Override
    @Transactional
    public void update(Integer id, CardCredentials updCardCredentials) {
        CardCredentials cardCredentials = cardCredentialsRepository.findById(id)
                .orElseThrow(() -> new CardCredentialsNotFoundException(id));
        cardCredentials.setCardNumber(updCardCredentials.getCardNumber());
        cardCredentials.setCvv(updCardCredentials.getCvv());
        cardCredentials.setPin(updCardCredentials.getPin());
        cardCredentialsRepository.save(cardCredentials);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        CardCredentials cardCredentials = cardCredentialsRepository.findById(id)
                .orElseThrow(() -> new CardCredentialsNotFoundException(id));
        cardCredentialsRepository.delete(cardCredentials);
    }

}
