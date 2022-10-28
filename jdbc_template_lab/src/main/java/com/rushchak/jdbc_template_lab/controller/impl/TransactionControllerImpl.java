package com.rushchak.jdbc_template_lab.controller.impl;

import com.rushchak.jdbc_template_lab.controller.TransactionController;
import com.rushchak.jdbc_template_lab.domain.Transaction;
import com.rushchak.jdbc_template_lab.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionControllerImpl implements TransactionController {
    @Autowired
    private TransactionService transactionService;

    @Override
    public List<Transaction> findAll() {
        return transactionService.findAll();
    }

    @Override
    public Optional<Transaction> findById(Integer id) {
        return transactionService.findById(id);
    }

    @Override
    public int create(Transaction transaction) {
        return transactionService.create(transaction);
    }

    @Override
    public int update(Integer id, Transaction transaction) {
        return transactionService.update(id, transaction);
    }

    @Override
    public int delete(Integer id) {
        return transactionService.delete(id);
    }
    
}
