package com.rushchak.springbootlab.controller;

import com.rushchak.springbootlab.domain.Transaction;
import com.rushchak.springbootlab.dto.TransactionDto;
import com.rushchak.springbootlab.dto.assembler.TransactionDtoAssembler;
import com.rushchak.springbootlab.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private TransactionDtoAssembler transactionDtoAssembler;

    @GetMapping(value = "/{transactionId}")
    public ResponseEntity<TransactionDto> getTransaction(@PathVariable Integer transactionId) {
        Transaction transaction = transactionService.findById(transactionId);
        TransactionDto transactionDto = transactionDtoAssembler.toModel(transaction);
        return new ResponseEntity<>(transactionDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<TransactionDto>> getAllTransactions() {
        List<Transaction> transactions = transactionService.findAll();
        CollectionModel<TransactionDto> transactionDtos = transactionDtoAssembler.toCollectionModel(transactions);
        return new ResponseEntity<>(transactionDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/byReceiver/{receiverId}")
    public ResponseEntity<CollectionModel<TransactionDto>> getTransactionsByReceiverId(@PathVariable Integer receiverId) {
        List<Transaction> transactions = transactionService.findTransactionsByReceiverId(receiverId);
        CollectionModel<TransactionDto> transactionDtos = transactionDtoAssembler.toCollectionModel(transactions);
        return new ResponseEntity<>(transactionDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/bySender/{senderId}")
    public ResponseEntity<CollectionModel<TransactionDto>> getTransactionsBySenderId(@PathVariable Integer senderId) {
        List<Transaction> transactions = transactionService.findTransactionsBySenderId(senderId);
        CollectionModel<TransactionDto> transactionDtos = transactionDtoAssembler.toCollectionModel(transactions);
        return new ResponseEntity<>(transactionDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<TransactionDto> addTransaction(@RequestBody Transaction transaction) {
        Transaction newTransaction = transactionService.create(transaction);
        TransactionDto transactionDto = transactionDtoAssembler.toModel(newTransaction);
        return new ResponseEntity<>(transactionDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{transactionId}")
    public ResponseEntity<?> updateTransaction(@RequestBody Transaction updTransaction, @PathVariable Integer transactionId) {
        transactionService.update(transactionId, updTransaction);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{transactionId}")
    public ResponseEntity<?> deleteTransaction(@PathVariable Integer transactionId) {
        transactionService.delete(transactionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
