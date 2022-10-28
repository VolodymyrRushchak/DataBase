package com.rushchak.jdbc_template_lab.service.impl;

import com.rushchak.jdbc_template_lab.dao.AccountDao;
import com.rushchak.jdbc_template_lab.domain.Account;
import com.rushchak.jdbc_template_lab.domain.Client;
import com.rushchak.jdbc_template_lab.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Override
    public List<Account> findAll() {
        return accountDao.findAll();
    }

    @Override
    public Optional<Account> findById(Integer id) {
        return accountDao.findById(id);
    }

    @Override
    public int create(Account account) {
        return accountDao.create(account);
    }

    @Override
    public int update(Integer id, Account account) {
        return accountDao.update(id, account);
    }

    @Override
    public int delete(Integer id) {
        return accountDao.delete(id);
    }

    @Override
    public Optional<Account> findByName(String name) {
        return accountDao.findByName(name);
    }

}
