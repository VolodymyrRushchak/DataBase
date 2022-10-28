package com.rushchak.jdbc_template_lab.controller.impl;

import com.rushchak.jdbc_template_lab.controller.CardController;
import com.rushchak.jdbc_template_lab.domain.Card;
import com.rushchak.jdbc_template_lab.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardControllerImpl implements CardController {
    @Autowired
    private CardService cardService;

    @Override
    public List<Card> findAll() {
        return cardService.findAll();
    }

    @Override
    public Optional<Card> findById(Integer id) {
        return cardService.findById(id);
    }

    @Override
    public int create(Card card) {
        return cardService.create(card);
    }

    @Override
    public int update(Integer id, Card card) {
        return cardService.update(id, card);
    }

    @Override
    public int delete(Integer id) {
        return cardService.delete(id);
    }

}
