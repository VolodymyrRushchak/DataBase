package com.rushchak.springbootlab.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "card_info", schema = "rushchak", catalog = "")
public class CardInfo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "balance")
    private Double balance;
    @Basic
    @Column(name = "card_name")
    private String cardName;
    @Basic
    @Column(name = "date_opened")
    private Date dateOpened;
    @Basic
    @Column(name = "date_expires")
    private Date dateExpires;
    @OneToOne(mappedBy = "cardInfo")
    private Card card;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public Date getDateOpened() {
        return dateOpened;
    }

    public void setDateOpened(Date dateOpened) {
        this.dateOpened = dateOpened;
    }

    public Date getDateExpires() {
        return dateExpires;
    }

    public void setDateExpires(Date dateExpires) {
        this.dateExpires = dateExpires;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardInfo cardInfo = (CardInfo) o;
        return Objects.equals(id, cardInfo.id) && Objects.equals(balance, cardInfo.balance) && Objects.equals(cardName, cardInfo.cardName) && Objects.equals(dateOpened, cardInfo.dateOpened) && Objects.equals(dateExpires, cardInfo.dateExpires);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, balance, cardName, dateOpened, dateExpires);
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
