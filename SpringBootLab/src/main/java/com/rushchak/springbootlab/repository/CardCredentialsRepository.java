package com.rushchak.springbootlab.repository;

import com.rushchak.springbootlab.domain.CardCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardCredentialsRepository extends JpaRepository<CardCredentials, Integer> {
}
