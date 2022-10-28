package com.rushchak.jdbc_template_lab.dao.impl;


import com.rushchak.jdbc_template_lab.dao.TransactionDao;
import com.rushchak.jdbc_template_lab.domain.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Service
public class TransactionDaoImpl implements TransactionDao {
    private static final String FIND_ALL = "SELECT * FROM transaction";
    private static final String FIND_BY_ID = "SELECT * FROM transaction WHERE id=?";
    private static final String CREATE = "INSERT transaction(date, amount, purpose, senders_card_id, receivers_card_id) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE transaction SET date=?, amount=?, purpose=?, senders_card_id=?, receivers_card_id=? WHERE id=?";
    private static final String DELETE = "DELETE FROM transaction WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Transaction> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Transaction.class));
    }

    @Override
    public Optional<Transaction> findById(Integer id) {
        Optional<Transaction> transaction;
        try {
            transaction = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Transaction.class), id));
        } catch (EmptyResultDataAccessException e) {
            transaction = Optional.empty();
        }
        return transaction;
    }

    @Override
    public int create(Transaction transaction) {
        return jdbcTemplate.update(CREATE, transaction.getDate(), transaction.getAmount(), transaction.getPurpose(), transaction.getSendersCardId(), transaction.getReceiversCardId());
    }

    @Override
    public int update(Integer id, Transaction transaction) {
        return jdbcTemplate.update(UPDATE, transaction.getDate(), transaction.getAmount(), transaction.getPurpose(), transaction.getSendersCardId(), transaction.getReceiversCardId(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }
}
