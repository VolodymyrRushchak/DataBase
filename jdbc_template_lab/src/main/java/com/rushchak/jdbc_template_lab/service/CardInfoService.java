package com.rushchak.jdbc_template_lab.service;

import com.rushchak.jdbc_template_lab.domain.CardInfo;

import java.util.Optional;

public interface CardInfoService extends GeneralService<CardInfo, Integer> {

    Optional<CardInfo> findByName(String name);

}
