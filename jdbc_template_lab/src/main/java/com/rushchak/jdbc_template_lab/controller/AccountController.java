package com.rushchak.jdbc_template_lab.controller;

import com.rushchak.jdbc_template_lab.domain.Account;

import java.util.Optional;

public interface AccountController extends GeneralController<Account, Integer> {

    Optional<Account> findByName(String name);

}
