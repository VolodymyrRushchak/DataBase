package com.rushchak.jdbc_template_lab.controller;

import com.rushchak.jdbc_template_lab.domain.CardInfo;

import java.util.Optional;

public interface CardInfoController extends GeneralController<CardInfo, Integer> {

    Optional<CardInfo> findByName(String name);

}
