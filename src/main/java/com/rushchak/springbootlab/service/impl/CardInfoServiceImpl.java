package com.rushchak.springbootlab.service.impl;


import com.rushchak.springbootlab.domain.CardInfo;
import com.rushchak.springbootlab.exception.CardInfoNotFoundException;
import com.rushchak.springbootlab.repository.CardInfoRepository;
import com.rushchak.springbootlab.service.CardInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class CardInfoServiceImpl implements CardInfoService {
    @Autowired
    private CardInfoRepository cardInfoRepository;
    
    @Override
    public List<CardInfo> findAll() {
        return cardInfoRepository.findAll();
    }

    @Override
    public CardInfo findById(Integer id) {
        return cardInfoRepository.findById(id).orElseThrow(() -> new CardInfoNotFoundException(id));
    }

    @Override
    @Transactional
    public CardInfo create(CardInfo cardInfo) {
        cardInfoRepository.save(cardInfo);
        return cardInfo;
    }

    @Override
    @Transactional
    public void update(Integer id, CardInfo updCardInfo) {
        CardInfo cardInfo = cardInfoRepository.findById(id)
                .orElseThrow(() -> new CardInfoNotFoundException(id));
        cardInfo.setBalance(updCardInfo.getBalance());
        cardInfo.setCardName(updCardInfo.getCardName());
        cardInfo.setDateOpened(updCardInfo.getDateOpened());
        cardInfo.setDateExpires(updCardInfo.getDateExpires());
        cardInfoRepository.save(cardInfo);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        CardInfo cardInfo = cardInfoRepository.findById(id)
                .orElseThrow(() -> new CardInfoNotFoundException(id));
        cardInfoRepository.delete(cardInfo);
    }


}
