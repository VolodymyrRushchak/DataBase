package com.rushchak.springbootlab.controller;

import com.rushchak.springbootlab.service.CardInfoProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/cardInfos")
public class CardInfoProcedureController {
    @Autowired
    private CardInfoProcedureService cardInfoProcedureService;

    @GetMapping(value = "/minBalance")
    public ResponseEntity<Integer> getMinBalance() {
        Integer minBalance = cardInfoProcedureService.getMinBalance();
        return new ResponseEntity<>(minBalance, HttpStatus.OK);
    }

}
