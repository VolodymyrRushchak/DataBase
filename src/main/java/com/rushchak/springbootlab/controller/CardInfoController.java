package com.rushchak.springbootlab.controller;

import com.rushchak.springbootlab.domain.CardInfo;
import com.rushchak.springbootlab.dto.CardInfoDto;
import com.rushchak.springbootlab.dto.assembler.CardInfoDtoAssembler;
import com.rushchak.springbootlab.service.CardInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/cardInfos")
public class CardInfoController {
    @Autowired
    private CardInfoService cardInfoService;
    @Autowired
    private CardInfoDtoAssembler cardInfoDtoAssembler;

    @GetMapping(value = "/{cardInfoId}")
    public ResponseEntity<CardInfoDto> getCardInfo(@PathVariable Integer cardInfoId) {
        CardInfo cardInfo = cardInfoService.findById(cardInfoId);
        CardInfoDto cardInfoDto = cardInfoDtoAssembler.toModel(cardInfo);
        return new ResponseEntity<>(cardInfoDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<CardInfoDto>> getAllCardInfos() {
        List<CardInfo> cardInfos = cardInfoService.findAll();
        CollectionModel<CardInfoDto> cardInfoDtos = cardInfoDtoAssembler.toCollectionModel(cardInfos);
        return new ResponseEntity<>(cardInfoDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<CardInfoDto> addCardInfo(@RequestBody CardInfo cardInfo) {
        CardInfo newCardInfo = cardInfoService.create(cardInfo);
        CardInfoDto cardInfoDto = cardInfoDtoAssembler.toModel(newCardInfo);
        return new ResponseEntity<>(cardInfoDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{cardInfoId}")
    public ResponseEntity<?> updateCardInfo(@RequestBody CardInfo updCardInfo, @PathVariable Integer cardInfoId) {
        cardInfoService.update(cardInfoId, updCardInfo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{cardInfoId}")
    public ResponseEntity<?> deleteCardInfo(@PathVariable Integer cardInfoId) {
        cardInfoService.delete(cardInfoId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
