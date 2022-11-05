package com.rushchak.springbootlab.repository;

import com.rushchak.springbootlab.domain.CardInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardInfoRepository extends JpaRepository<CardInfo, Integer> {
}
