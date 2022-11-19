package com.rushchak.springbootlab.service.impl;

import com.rushchak.springbootlab.repository.TransactionRepository;
import com.rushchak.springbootlab.service.TransactionProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionProcedureServiceImpl implements TransactionProcedureService {

    @Autowired
    TransactionRepository transactionRepository;

    public void addTransaction(String senderName, String receiverName, Double amount) {
        transactionRepository.addTransaction(senderName, receiverName, amount);
    }

}
