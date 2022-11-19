package com.rushchak.springbootlab.controller;

import com.rushchak.springbootlab.service.ClientProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/clients")
public class ClientProcedureController {

    @Autowired
    ClientProcedureService clientProcedureService;

    @PostMapping("/packOfClients/{name}/{surname}")
    public ResponseEntity<?> addPackOfClients(@PathVariable String name, @PathVariable String surname) {
        clientProcedureService.addPackOfClients(name, surname);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/useCursor")
    public ResponseEntity<?> useCursor() {
        clientProcedureService.useCursor();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
