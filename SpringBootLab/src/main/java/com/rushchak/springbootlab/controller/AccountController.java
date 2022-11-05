package com.rushchak.springbootlab.controller;


import com.rushchak.springbootlab.domain.Account;
import com.rushchak.springbootlab.dto.AccountDto;
import com.rushchak.springbootlab.dto.assembler.AccountDtoAssembler;
import com.rushchak.springbootlab.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountDtoAssembler accountDtoAssembler;

    @GetMapping(value = "/{accountId}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable Integer accountId) {
        Account account = accountService.findById(accountId);
        AccountDto accountDto = accountDtoAssembler.toModel(account);
        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<AccountDto>> getAllAccounts() {
        List<Account> accounts = accountService.findAll();
        CollectionModel<AccountDto> accountDtos = accountDtoAssembler.toCollectionModel(accounts);
        return new ResponseEntity<>(accountDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<AccountDto> addAccount(@RequestBody Account account) {
        Account newAccount = accountService.create(account);
        AccountDto accountDto = accountDtoAssembler.toModel(newAccount);
        return new ResponseEntity<>(accountDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{accountId}")
    public ResponseEntity<?> updateAccount(@RequestBody Account updAccount, @PathVariable Integer accountId) {
        accountService.update(accountId, updAccount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{accountId}")
    public ResponseEntity<?> deleteAccount(@PathVariable Integer accountId) {
        accountService.delete(accountId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
