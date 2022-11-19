package com.rushchak.springbootlab.service.impl;

import com.rushchak.springbootlab.repository.CardInfoRepository;
import com.rushchak.springbootlab.service.CardInfoProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardInfoProcedureServiceImpl implements CardInfoProcedureService {

    @Autowired
    CardInfoRepository cardInfoRepository;

    @Override
    public Integer getMinBalance() {
        return cardInfoRepository.getMinBalance();
    }

}
