package com.rushchak.jdbc_template_lab.dao.impl;

import com.rushchak.jdbc_template_lab.dao.AccountDao;
import com.rushchak.jdbc_template_lab.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Service
public class AccountDaoImpl implements AccountDao {
    private static final String FIND_ALL = "SELECT * FROM account";
    private static final String FIND_BY_ID = "SELECT * FROM account WHERE id=?";
    private static final String FIND_BY_NAME = "SELECT * FROM account WHERE account_name=?";
    private static final String CREATE = "INSERT account(client_id, account_name, date_created) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE account SET client_id=?, account_name=?, date_created=? WHERE id=?";
    private static final String DELETE = "DELETE FROM account WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Account> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Account.class));
    }

    @Override
    public Optional<Account> findById(Integer id) {
        Optional<Account> account;
        try {
            account = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Account.class), id));
        } catch (EmptyResultDataAccessException e) {
            account = Optional.empty();
        }
        return account;
    }

    @Override
    public Optional<Account> findByName(String name) {
        Optional<Account> account;
        try {
            account = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_NAME,
                    BeanPropertyRowMapper.newInstance(Account.class), name));
        } catch (EmptyResultDataAccessException e) {
            account = Optional.empty();
        }
        return account;
    }

    @Override
    public int create(Account account) {
        return jdbcTemplate.update(CREATE, account.getClientId(), account.getAccountName(), account.getDateCreated());
    }

    @Override
    public int update(Integer id, Account account) {
        return jdbcTemplate.update(UPDATE, account.getClientId(), account.getAccountName(), account.getDateCreated(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }

}
