package com.rushchak.jdbc_template_lab.dao;

import com.rushchak.jdbc_template_lab.domain.CardInfo;

import java.util.Optional;

public interface CardInfoDao extends GeneralDao<CardInfo, Integer> {

    Optional<CardInfo> findByName(String name);

}
