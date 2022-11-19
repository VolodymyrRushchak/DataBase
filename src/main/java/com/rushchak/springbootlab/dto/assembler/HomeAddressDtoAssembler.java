package com.rushchak.springbootlab.dto.assembler;

import com.rushchak.springbootlab.controller.ClientController;
import com.rushchak.springbootlab.controller.HomeAddressController;
import com.rushchak.springbootlab.domain.HomeAddress;
import com.rushchak.springbootlab.dto.HomeAddressDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class HomeAddressDtoAssembler implements RepresentationModelAssembler<HomeAddress, HomeAddressDto> {
    
    @Override
    public HomeAddressDto toModel(HomeAddress entity) {
        HomeAddressDto homeAddressDto = HomeAddressDto.builder()
                .id(entity.getId())
                .country(entity.getCountry())
                .state(entity.getState())
                .city(entity.getCity())
                .streetName(entity.getStreetName())
                .houseNumber(entity.getHouseNumber())
                .build();
        Link selfLink = linkTo(methodOn(HomeAddressController.class).getHomeAddress(homeAddressDto.getId())).withSelfRel();
        homeAddressDto.add(selfLink);
        Link clientsLink = linkTo(methodOn(ClientController.class).getClientsByHomeAddressId(homeAddressDto.getId())).withSelfRel();
        homeAddressDto.add(clientsLink);
        return homeAddressDto;
    }

    @Override
    public CollectionModel<HomeAddressDto> toCollectionModel(Iterable<? extends HomeAddress> entities) {
        CollectionModel<HomeAddressDto> homeAddressDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(HomeAddressController.class).getAllHomeAddresses()).withSelfRel();
        homeAddressDtos.add(selfLink);
        return homeAddressDtos;
    }

    public CollectionModel<HomeAddressDto> toCollectionModel(Iterable<? extends HomeAddress> entities, Link link) {
        CollectionModel<HomeAddressDto> homeAddressDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        homeAddressDtos.add(link);
        return homeAddressDtos;
    }

}