package com.rushchak.jdbc_template_lab.dao.impl;


import com.rushchak.jdbc_template_lab.dao.CardDao;
import com.rushchak.jdbc_template_lab.domain.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Service
public class CardDaoImpl implements CardDao {
    private static final String FIND_ALL = "SELECT * FROM card";
    private static final String FIND_BY_ID = "SELECT * FROM card WHERE id=?";
    private static final String CREATE = "INSERT card(account_id, card_info_id) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE card SET account_id=?, card_info_id=? WHERE id=?";
    private static final String DELETE = "DELETE FROM card WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Card> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Card.class));
    }

    @Override
    public Optional<Card> findById(Integer id) {
        Optional<Card> card;
        try {
            card = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Card.class), id));
        } catch (EmptyResultDataAccessException e) {
            card = Optional.empty();
        }
        return card;
    }

    @Override
    public int create(Card card) {
        return jdbcTemplate.update(CREATE, card.getAccountId(), card.getCardInfoId());
    }

    @Override
    public int update(Integer id, Card card) {
        return jdbcTemplate.update(UPDATE, card.getAccountId(), card.getCardInfoId(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }
}
