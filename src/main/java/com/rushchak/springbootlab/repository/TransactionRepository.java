package com.rushchak.springbootlab.repository;

import com.rushchak.springbootlab.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    @Procedure("add_transaction")
    void addTransaction(String senderName, String receiverName, Double amount);

}
