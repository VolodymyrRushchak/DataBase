package com.rushchak.springbootlab.dto.assembler;

import com.rushchak.springbootlab.controller.ClientController;
import com.rushchak.springbootlab.domain.Client;
import com.rushchak.springbootlab.dto.ClientDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ClientDtoAssembler implements RepresentationModelAssembler<Client, ClientDto> {
    
    @Override
    public ClientDto toModel(Client entity) {
        String homeAddr = entity.getHomeAddress() == null ? "null" : entity.getHomeAddress().getCountry() + ", " + entity.getHomeAddress().getStreetName() + ", " + entity.getHomeAddress().getHouseNumber();
        ClientDto clientDto = ClientDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .phoneNumber(entity.getPhoneNumber())
                .email(entity.getEmail())
                .homeAddress(homeAddr)
                .build();
        Link selfLink = linkTo(methodOn(ClientController.class).getClient(clientDto.getId())).withSelfRel();
        clientDto.add(selfLink);
        return clientDto;
    }

    @Override
    public CollectionModel<ClientDto> toCollectionModel(Iterable<? extends Client> entities) {
        CollectionModel<ClientDto> clientDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(ClientController.class).getAllClients()).withSelfRel();
        clientDtos.add(selfLink);
        return clientDtos;
    }

    public CollectionModel<ClientDto> toCollectionModel(Iterable<? extends Client> entities, Link link) {
        CollectionModel<ClientDto> clientDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        clientDtos.add(link);
        return clientDtos;
    }
    
}