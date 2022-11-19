package com.rushchak.springbootlab.service.impl;

import com.rushchak.springbootlab.domain.Account;
import com.rushchak.springbootlab.domain.Card;
import com.rushchak.springbootlab.domain.Transaction;
import com.rushchak.springbootlab.exception.AccountNotFoundException;
import com.rushchak.springbootlab.exception.CardNotFoundException;
import com.rushchak.springbootlab.repository.AccountRepository;
import com.rushchak.springbootlab.repository.CardRepository;
import com.rushchak.springbootlab.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Card> findAll() {
        return cardRepository.findAll();
    }

    @Override
    public Card findById(Integer id) {
        return cardRepository.findById(id).orElseThrow(() -> new CardNotFoundException(id));
    }

    @Override
    @Transactional
    public Card create(Card card) {
        cardRepository.save(card);
        return card;
    }

    @Override
    @Transactional
    public void update(Integer id, Card updCard) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new CardNotFoundException(id));
        card.setAccount(updCard.getAccount());
        card.setCardInfo(updCard.getCardInfo());
        card.setCardCredentials(updCard.getCardCredentials());
        cardRepository.save(card);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new CardNotFoundException(id));
        cardRepository.delete(card);
    }

    public List<Card> findCardsByAccountId(Integer accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException(accountId));
        return account.getCards().stream().toList();
    }

    public List<Card> findSendersByReceiverId(Integer receiverId) {
        Card receiver = cardRepository.findById(receiverId).orElseThrow(() -> new CardNotFoundException(receiverId));
        return receiver.getTransactionsByReceiverId().stream().map(Transaction::getSenderCard).toList();
    }

    public List<Card> findReceiversBySenderId(Integer senderId) {
        Card sender = cardRepository.findById(senderId).orElseThrow(() -> new CardNotFoundException(senderId));
        return sender.getTransactionsBySenderId().stream().map(Transaction::getReceiverCard).toList();
    }

}
