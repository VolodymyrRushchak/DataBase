package com.rushchak.springbootlab.controller;


import com.rushchak.springbootlab.domain.Transaction;
import com.rushchak.springbootlab.service.TransactionProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/transactions")
public class TransactionProcedureController {

    @Autowired
    TransactionProcedureService transactionProcedureService;

    @PostMapping("/procedure/{sender_name}/{receiver_name}")
    public ResponseEntity<?> addTransaction(@RequestBody Transaction transaction, @PathVariable String sender_name, @PathVariable String receiver_name) {
        transactionProcedureService.addTransaction(sender_name, receiver_name, transaction.getAmount());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
