package com.rushchak.springbootlab.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Card {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
    private Account account;
    @OneToOne
    @JoinColumn(name = "card_info_id", referencedColumnName = "id", nullable = false)
    private CardInfo cardInfo;
    @OneToOne
    @JoinColumn(name = "card_credentials_id", referencedColumnName = "id", nullable = false)
    private CardCredentials cardCredentials;
    @OneToMany(mappedBy = "senderCard")
    private List<Transaction> transactionsBySenderId;
    @OneToMany(mappedBy = "receiverCard")
    private List<Transaction> transactionsByReceiverId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(id, card.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public CardInfo getCardInfo() {
        return cardInfo;
    }

    public void setCardInfo(CardInfo cardInfo) {
        this.cardInfo = cardInfo;
    }

    public CardCredentials getCardCredentials() {
        return cardCredentials;
    }

    public void setCardCredentials(CardCredentials cardCredentials) {
        this.cardCredentials = cardCredentials;
    }

    public List<Transaction> getTransactionsBySenderId() {
        return transactionsBySenderId;
    }

    public void setTransactionsBySenderId(List<Transaction> transactionsBySenderId) {
        this.transactionsBySenderId = transactionsBySenderId;
    }

    public List<Transaction> getTransactionsByReceiverId() {
        return transactionsByReceiverId;
    }

    public void setTransactionsByReceiverId(List<Transaction> transactionsByReceiverId) {
        this.transactionsByReceiverId = transactionsByReceiverId;
    }
}
