package com.rushchak.jdbc_template_lab.service.impl;


import com.rushchak.jdbc_template_lab.dao.CardInfoDao;
import com.rushchak.jdbc_template_lab.domain.CardInfo;
import com.rushchak.jdbc_template_lab.service.CardInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardInfoServiceImpl implements CardInfoService {
    @Autowired
    private CardInfoDao cardInfoDao;

    @Override
    public List<CardInfo> findAll() {
        return cardInfoDao.findAll();
    }

    @Override
    public Optional<CardInfo> findById(Integer id) {
        return cardInfoDao.findById(id);
    }

    @Override
    public int create(CardInfo cardInfo) {
        return cardInfoDao.create(cardInfo);
    }

    @Override
    public int update(Integer id, CardInfo cardInfo) {
        return cardInfoDao.update(id, cardInfo);
    }

    @Override
    public int delete(Integer id) {
        return cardInfoDao.delete(id);
    }

    @Override
    public Optional<CardInfo> findByName(String name) {
        return cardInfoDao.findByName(name);
    }
    
}
