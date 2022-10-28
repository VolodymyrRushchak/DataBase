package com.rushchak.jdbc_template_lab.controller.impl;

import com.rushchak.jdbc_template_lab.controller.AccountController;
import com.rushchak.jdbc_template_lab.domain.Account;
import com.rushchak.jdbc_template_lab.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountControllerImpl implements AccountController {

    @Autowired
    private AccountService accountService;

    @Override
    public List<Account> findAll() {
        return accountService.findAll();
    }

    @Override
    public Optional<Account> findById(Integer id) {
        return accountService.findById(id);
    }

    @Override
    public int create(Account account) {
        return accountService.create(account);
    }

    @Override
    public int update(Integer id, Account account) {
        return accountService.update(id, account);
    }

    @Override
    public int delete(Integer id) {
        return accountService.delete(id);
    }

    @Override
    public Optional<Account> findByName(String name) {
        return accountService.findByName(name);
    }

}
