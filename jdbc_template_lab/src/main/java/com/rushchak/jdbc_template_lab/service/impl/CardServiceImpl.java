package com.rushchak.jdbc_template_lab.service.impl;


import com.rushchak.jdbc_template_lab.dao.CardDao;
import com.rushchak.jdbc_template_lab.domain.Card;
import com.rushchak.jdbc_template_lab.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    private CardDao cardDao;

    @Override
    public List<Card> findAll() {
        return cardDao.findAll();
    }

    @Override
    public Optional<Card> findById(Integer id) {
        return cardDao.findById(id);
    }

    @Override
    public int create(Card card) {
        return cardDao.create(card);
    }

    @Override
    public int update(Integer id, Card card) {
        return cardDao.update(id, card);
    }

    @Override
    public int delete(Integer id) {
        return cardDao.delete(id);
    }
    
}
