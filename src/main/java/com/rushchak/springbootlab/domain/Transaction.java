package com.rushchak.springbootlab.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Transaction {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "date")
    private Date date;
    @Basic
    @Column(name = "amount")
    private Double amount;
    @Basic
    @Column(name = "purpose")
    private String purpose;
    @ManyToOne
    @JoinColumn(name = "senders_card_id", referencedColumnName = "id", nullable = false)
    private Card senderCard;
    @ManyToOne
    @JoinColumn(name = "receivers_card_id", referencedColumnName = "id", nullable = false)
    private Card receiverCard;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id) && Objects.equals(date, that.date) && Objects.equals(amount, that.amount) && Objects.equals(purpose, that.purpose);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, amount, purpose);
    }

    public Card getSenderCard() {
        return senderCard;
    }

    public void setSenderCard(Card senderCard) {
        this.senderCard = senderCard;
    }

    public Card getReceiverCard() {
        return receiverCard;
    }

    public void setReceiverCard(Card receiverCard) {
        this.receiverCard = receiverCard;
    }
}
