package com.rushchak.springbootlab.service;

import com.rushchak.springbootlab.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TransactionService extends GeneralService<Transaction, Integer> {

    List<Transaction> findTransactionsByReceiverId(Integer receiverId);

    List<Transaction> findTransactionsBySenderId(Integer senderId);

}
