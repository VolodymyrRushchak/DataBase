package com.rushchak.springbootlab.controller;

import com.rushchak.springbootlab.domain.HomeAddress;
import com.rushchak.springbootlab.dto.HomeAddressDto;
import com.rushchak.springbootlab.dto.assembler.HomeAddressDtoAssembler;
import com.rushchak.springbootlab.service.HomeAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/homeAddresses")
public class HomeAddressController {
    @Autowired
    private HomeAddressService homeAddressService;
    @Autowired
    private HomeAddressDtoAssembler homeAddressDtoAssembler;

    @GetMapping(value = "/{homeAddressId}")
    public ResponseEntity<HomeAddressDto> getHomeAddress(@PathVariable Integer homeAddressId) {
        HomeAddress homeAddress = homeAddressService.findById(homeAddressId);
        HomeAddressDto homeAddressDto = homeAddressDtoAssembler.toModel(homeAddress);
        return new ResponseEntity<>(homeAddressDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<HomeAddressDto>> getAllHomeAddresses() {
        List<HomeAddress> homeAddresses = homeAddressService.findAll();
        CollectionModel<HomeAddressDto> homeAddressDtos = homeAddressDtoAssembler.toCollectionModel(homeAddresses);
        return new ResponseEntity<>(homeAddressDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<HomeAddressDto> addHomeAddress(@RequestBody HomeAddress homeAddress) {
        HomeAddress newHomeAddress = homeAddressService.create(homeAddress);
        HomeAddressDto homeAddressDto = homeAddressDtoAssembler.toModel(newHomeAddress);
        return new ResponseEntity<>(homeAddressDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{homeAddressId}")
    public ResponseEntity<?> updateHomeAddress(@RequestBody HomeAddress updHomeAddress, @PathVariable Integer homeAddressId) {
        homeAddressService.update(homeAddressId, updHomeAddress);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{homeAddressId}")
    public ResponseEntity<?> deleteHomeAddress(@PathVariable Integer homeAddressId) {
        homeAddressService.delete(homeAddressId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
