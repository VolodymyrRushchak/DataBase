package com.rushchak.jdbc_template_lab.service.impl;


import com.rushchak.jdbc_template_lab.dao.TransactionDao;
import com.rushchak.jdbc_template_lab.domain.Transaction;
import com.rushchak.jdbc_template_lab.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionDao transactionDao;

    @Override
    public List<Transaction> findAll() {
        return transactionDao.findAll();
    }

    @Override
    public Optional<Transaction> findById(Integer id) {
        return transactionDao.findById(id);
    }

    @Override
    public int create(Transaction transaction) {
        return transactionDao.create(transaction);
    }

    @Override
    public int update(Integer id, Transaction transaction) {
        return transactionDao.update(id, transaction);
    }

    @Override
    public int delete(Integer id) {
        return transactionDao.delete(id);
    }
    
}
