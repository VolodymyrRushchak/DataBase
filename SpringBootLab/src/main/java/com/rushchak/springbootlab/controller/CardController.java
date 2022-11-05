package com.rushchak.springbootlab.controller;

import com.rushchak.springbootlab.domain.Card;
import com.rushchak.springbootlab.dto.CardDto;
import com.rushchak.springbootlab.dto.assembler.CardDtoAssembler;
import com.rushchak.springbootlab.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/cards")
public class CardController {
    @Autowired
    private CardService cardService;
    @Autowired
    private CardDtoAssembler cardDtoAssembler;

    @GetMapping(value = "/{cardId}")
    public ResponseEntity<CardDto> getCard(@PathVariable Integer cardId) {
        Card card = cardService.findById(cardId);
        CardDto cardDto = cardDtoAssembler.toModel(card);
        return new ResponseEntity<>(cardDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<CardDto>> getAllCards() {
        List<Card> cards = cardService.findAll();
        CollectionModel<CardDto> cardDtos = cardDtoAssembler.toCollectionModel(cards);
        return new ResponseEntity<>(cardDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/ofAccount/{accountId}")
    public ResponseEntity<CollectionModel<CardDto>> getCardsByAccountId(@PathVariable Integer accountId) {
        List<Card> cards = cardService.findCardsByAccountId(accountId);
        CollectionModel<CardDto> cardDtos = cardDtoAssembler.toCollectionModel(cards);
        return new ResponseEntity<>(cardDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/sendersOfReceiver/{receiverId}")
    public ResponseEntity<CollectionModel<CardDto>> getSendersByReceiverId(@PathVariable Integer receiverId) {
        List<Card> cards = cardService.findSendersByReceiverId(receiverId);
        CollectionModel<CardDto> cardDtos = cardDtoAssembler.toCollectionModel(cards);
        return new ResponseEntity<>(cardDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/receiversOfSender/{senderId}")
    public ResponseEntity<CollectionModel<CardDto>> getReceiversBySenderId(@PathVariable Integer senderId) {
        List<Card> cards = cardService.findReceiversBySenderId(senderId);
        CollectionModel<CardDto> cardDtos = cardDtoAssembler.toCollectionModel(cards);
        return new ResponseEntity<>(cardDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<CardDto> addCard(@RequestBody Card card) {
        Card newCard = cardService.create(card);
        CardDto cardDto = cardDtoAssembler.toModel(newCard);
        return new ResponseEntity<>(cardDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{cardId}")
    public ResponseEntity<?> updateCard(@RequestBody Card updCard, @PathVariable Integer cardId) {
        cardService.update(cardId, updCard);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{cardId}")
    public ResponseEntity<?> deleteCard(@PathVariable Integer cardId) {
        cardService.delete(cardId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
