package com.rushchak.springbootlab.controller;

import com.rushchak.springbootlab.domain.CardCredentials;
import com.rushchak.springbootlab.dto.CardCredentialsDto;
import com.rushchak.springbootlab.dto.assembler.CardCredentialsDtoAssembler;
import com.rushchak.springbootlab.service.CardCredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/cardCredentials")
public class CardCredentialsController {
    @Autowired
    private CardCredentialsService cardCredentialsService;
    @Autowired
    private CardCredentialsDtoAssembler cardCredentialsDtoAssembler;

    @GetMapping(value = "/{cardCredentialsId}")
    public ResponseEntity<CardCredentialsDto> getCardCredentials(@PathVariable Integer cardCredentialsId) {
        CardCredentials cardCredentials = cardCredentialsService.findById(cardCredentialsId);
        CardCredentialsDto cardCredentialsDto = cardCredentialsDtoAssembler.toModel(cardCredentials);
        return new ResponseEntity<>(cardCredentialsDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<CardCredentialsDto>> getAllCardCredentials() {
        List<CardCredentials> cardCredentials = cardCredentialsService.findAll();
        CollectionModel<CardCredentialsDto> cardCredentialsDtos = cardCredentialsDtoAssembler.toCollectionModel(cardCredentials);
        return new ResponseEntity<>(cardCredentialsDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<CardCredentialsDto> addCardCredentials(@RequestBody CardCredentials cardCredentials) {
        CardCredentials newCardCredentials = cardCredentialsService.create(cardCredentials);
        CardCredentialsDto cardCredentialsDto = cardCredentialsDtoAssembler.toModel(newCardCredentials);
        return new ResponseEntity<>(cardCredentialsDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{cardCredentialsId}")
    public ResponseEntity<?> updateCardCredentials(@RequestBody CardCredentials updCardCredentials, @PathVariable Integer cardCredentialsId) {
        cardCredentialsService.update(cardCredentialsId, updCardCredentials);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{cardCredentialsId}")
    public ResponseEntity<?> deleteCardCredentials(@PathVariable Integer cardCredentialsId) {
        cardCredentialsService.delete(cardCredentialsId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
