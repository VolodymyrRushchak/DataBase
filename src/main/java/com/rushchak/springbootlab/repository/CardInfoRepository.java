package com.rushchak.springbootlab.repository;

import com.rushchak.springbootlab.domain.CardInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface CardInfoRepository extends JpaRepository<CardInfo, Integer> {

    @Query(value = "CALL get_min_balance;", nativeQuery = true)
    Integer getMinBalance();

}
