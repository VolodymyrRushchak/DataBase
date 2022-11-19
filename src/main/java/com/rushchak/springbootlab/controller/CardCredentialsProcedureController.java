package com.rushchak.springbootlab.controller;


import com.rushchak.springbootlab.domain.CardCredentials;
import com.rushchak.springbootlab.dto.CardCredentialsDto;
import com.rushchak.springbootlab.service.CardCredentialsProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/cardCreds")
public class CardCredentialsProcedureController {

    @Autowired
    private CardCredentialsProcedureService cardCredentialsProcedureService;

    @PostMapping(value = "")
    public ResponseEntity<Integer> addCardCredentials(@RequestBody CardCredentials cardCredentials) {
        cardCredentialsProcedureService.addCardCredentials(cardCredentials.getCardNumber(), cardCredentials.getCvv(), cardCredentials.getPin());
        return new ResponseEntity<>(0, HttpStatus.CREATED);
    }

}
