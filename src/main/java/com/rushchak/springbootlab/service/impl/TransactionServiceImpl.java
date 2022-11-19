package com.rushchak.springbootlab.service.impl;


import com.rushchak.springbootlab.domain.Card;
import com.rushchak.springbootlab.domain.Transaction;
import com.rushchak.springbootlab.exception.CardNotFoundException;
import com.rushchak.springbootlab.exception.TransactionNotFoundException;
import com.rushchak.springbootlab.repository.AccountRepository;
import com.rushchak.springbootlab.repository.CardRepository;
import com.rushchak.springbootlab.repository.TransactionRepository;
import com.rushchak.springbootlab.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CardRepository cardRepository;

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction findById(Integer id) {
        return transactionRepository.findById(id).orElseThrow(() -> new TransactionNotFoundException(id));
    }

    @Override
    @Transactional
    public Transaction create(Transaction transaction) {
        transactionRepository.save(transaction);
        return transaction;
    }

    @Override
    @Transactional
    public void update(Integer id, Transaction updTransaction) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(id));
        transaction.setDate(updTransaction.getDate());
        transaction.setAmount(updTransaction.getAmount());
        transaction.setPurpose(updTransaction.getPurpose());
        transaction.setSenderCard(updTransaction.getSenderCard());
        transaction.setReceiverCard(updTransaction.getReceiverCard());
        transactionRepository.save(transaction);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(id));
        transactionRepository.delete(transaction);
    }

    public List<Transaction> findTransactionsByReceiverId(Integer receiverId) {
        Card receiver = cardRepository.findById(receiverId).orElseThrow(() -> new CardNotFoundException(receiverId));
        return receiver.getTransactionsByReceiverId();
    }

    public List<Transaction> findTransactionsBySenderId(Integer senderId) {
        Card sender = cardRepository.findById(senderId).orElseThrow(() -> new CardNotFoundException(senderId));
        return sender.getTransactionsBySenderId();
    }

}
