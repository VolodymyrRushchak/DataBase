package com.rushchak.springbootlab.service;

import com.rushchak.springbootlab.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CardService extends GeneralService<Card, Integer> {

    List<Card> findCardsByAccountId(Integer accountId);

    List<Card> findSendersByReceiverId(Integer receiverId);

    List<Card> findReceiversBySenderId(Integer senderId);

}
