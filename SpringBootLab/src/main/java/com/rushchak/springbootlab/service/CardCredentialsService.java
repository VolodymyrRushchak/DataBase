package com.rushchak.springbootlab.service;

import com.rushchak.springbootlab.domain.CardCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CardCredentialsService extends GeneralService<CardCredentials, Integer> {
}
