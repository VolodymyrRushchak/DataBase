package com.rushchak.jdbc_template_lab.dao.impl;


import com.rushchak.jdbc_template_lab.dao.CardInfoDao;
import com.rushchak.jdbc_template_lab.domain.CardInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Service
public class CardInfoDaoImpl implements CardInfoDao {
    private static final String FIND_ALL = "SELECT * FROM card_info";
    private static final String FIND_BY_ID = "SELECT * FROM card_info WHERE id=?";
    private static final String FIND_BY_NAME = "SELECT * FROM card_info WHERE card_name=?";
    private static final String CREATE = "INSERT card_info(balance, card_name, date_opened, date_expires) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE card_info SET balance=?, card_name=?, date_opened=?, date_expires=? WHERE id=?";
    private static final String DELETE = "DELETE FROM card_info WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<CardInfo> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(CardInfo.class));
    }

    @Override
    public Optional<CardInfo> findById(Integer id) {
        Optional<CardInfo> cardInfo;
        try {
            cardInfo = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(CardInfo.class), id));
        } catch (EmptyResultDataAccessException e) {
            cardInfo = Optional.empty();
        }
        return cardInfo;
    }

    @Override
    public Optional<CardInfo> findByName(String name) {
        Optional<CardInfo> cardInfo;
        try {
            cardInfo = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_NAME,
                    BeanPropertyRowMapper.newInstance(CardInfo.class), name));
        } catch (EmptyResultDataAccessException e) {
            cardInfo = Optional.empty();
        }
        return cardInfo;
    }

    @Override
    public int create(CardInfo cardInfo) {
        return jdbcTemplate.update(CREATE, cardInfo.getBalance(), cardInfo.getCardName(), cardInfo.getDateOpened(), cardInfo.getDateExpires());
    }

    @Override
    public int update(Integer id, CardInfo cardInfo) {
        return jdbcTemplate.update(UPDATE, cardInfo.getBalance(), cardInfo.getCardName(), cardInfo.getDateOpened(), cardInfo.getDateExpires(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }
}
