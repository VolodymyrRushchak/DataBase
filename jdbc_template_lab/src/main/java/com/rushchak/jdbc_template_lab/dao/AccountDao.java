package com.rushchak.jdbc_template_lab.dao;

import com.rushchak.jdbc_template_lab.domain.Account;

import java.util.Optional;

public interface AccountDao extends GeneralDao<Account, Integer> {

    Optional<Account> findByName(String name);

}
