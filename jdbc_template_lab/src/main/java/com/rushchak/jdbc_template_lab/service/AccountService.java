package com.rushchak.jdbc_template_lab.service;

import com.rushchak.jdbc_template_lab.domain.Account;

import java.util.Optional;

public interface AccountService extends GeneralService<Account, Integer> {

    Optional<Account> findByName(String name);

}
