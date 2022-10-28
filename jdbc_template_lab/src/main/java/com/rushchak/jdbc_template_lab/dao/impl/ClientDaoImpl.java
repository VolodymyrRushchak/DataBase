package com.rushchak.jdbc_template_lab.dao.impl;


import com.rushchak.jdbc_template_lab.dao.ClientDao;
import com.rushchak.jdbc_template_lab.domain.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Service
public class ClientDaoImpl implements ClientDao {
    private static final String FIND_ALL = "SELECT * FROM client";
    private static final String FIND_BY_ID = "SELECT * FROM client WHERE id=?";
    private static final String FIND_BY_SURNAME = "SELECT * FROM client WHERE surname=?";
    private static final String FIND_BY_NAME = "SELECT * FROM client WHERE name=?";
    private static final String CREATE = "INSERT client(name, surname, phone_number, email) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE client SET name=?, surname=?, phone_number=?, email=? WHERE id=?";
    private static final String DELETE = "DELETE FROM client WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Client> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Client.class));
    }

    @Override
    public Optional<Client> findById(Integer id) {
        Optional<Client> client;
        try {
            client = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Client.class), id));
        } catch (EmptyResultDataAccessException e) {
            client = Optional.empty();
        }
        return client;
    }

    @Override
    public Optional<Client> findBySurname(String surname) {
        Optional<Client> client;
        try {
            client = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_SURNAME,
                    BeanPropertyRowMapper.newInstance(Client.class), surname));
        } catch (EmptyResultDataAccessException e) {
            client = Optional.empty();
        }
        return client;
    }

    @Override
    public Optional<Client> findByName(String name) {
        Optional<Client> client;
        try {
            client = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_NAME,
                    BeanPropertyRowMapper.newInstance(Client.class), name));
        } catch (EmptyResultDataAccessException e) {
            client = Optional.empty();
        }
        return client;
    }

    @Override
    public int create(Client client) {
        return jdbcTemplate.update(CREATE, client.getName(), client.getSurname(), client.getPhoneNumber(), client.getEmail());
    }

    @Override
    public int update(Integer id, Client client) {
        return jdbcTemplate.update(UPDATE, client.getName(), client.getSurname(), client.getPhoneNumber(), client.getEmail(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }
}
