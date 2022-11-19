package com.rushchak.springbootlab.service.impl;

import com.rushchak.springbootlab.domain.Account;
import com.rushchak.springbootlab.exception.AccountNotFoundException;
import com.rushchak.springbootlab.repository.AccountRepository;
import com.rushchak.springbootlab.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account findById(Integer id) {
        return accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
    }

    @Override
    @Transactional
    public Account create(Account account) {
        accountRepository.save(account);
        return account;
    }

    @Override
    @Transactional
    public void update(Integer id, Account updAccount) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
        account.setClient(updAccount.getClient());
        account.setAccountName(updAccount.getAccountName());
        account.setDateCreated(updAccount.getDateCreated());
        accountRepository.save(account);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
        accountRepository.delete(account);
    }

}
