package com.rushchak.jdbc_template_lab.controller.impl;

import com.rushchak.jdbc_template_lab.controller.CardInfoController;
import com.rushchak.jdbc_template_lab.domain.CardInfo;
import com.rushchak.jdbc_template_lab.service.CardInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardInfoControllerImpl implements CardInfoController {
    @Autowired
    private CardInfoService cardInfoService;

    @Override
    public List<CardInfo> findAll() {
        return cardInfoService.findAll();
    }

    @Override
    public Optional<CardInfo> findById(Integer id) {
        return cardInfoService.findById(id);
    }

    @Override
    public int create(CardInfo cardInfo) {
        return cardInfoService.create(cardInfo);
    }

    @Override
    public int update(Integer id, CardInfo cardInfo) {
        return cardInfoService.update(id, cardInfo);
    }

    @Override
    public int delete(Integer id) {
        return cardInfoService.delete(id);
    }

    @Override
    public Optional<CardInfo> findByName(String name) {
        return cardInfoService.findByName(name);
    }
    
}
