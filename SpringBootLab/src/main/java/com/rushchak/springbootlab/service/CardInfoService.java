package com.rushchak.springbootlab.service;

import com.rushchak.springbootlab.domain.CardInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CardInfoService extends GeneralService<CardInfo, Integer> {
}
