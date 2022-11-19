package com.rushchak.springbootlab.dto.assembler;

import com.rushchak.springbootlab.controller.CardCredentialsController;
import com.rushchak.springbootlab.domain.CardCredentials;
import com.rushchak.springbootlab.dto.CardCredentialsDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CardCredentialsDtoAssembler implements RepresentationModelAssembler<CardCredentials, CardCredentialsDto> {
    @Override
    public CardCredentialsDto toModel(CardCredentials entity) {
        CardCredentialsDto cardCredentialsDto = CardCredentialsDto.builder()
                .id(entity.getId())
                .cardNumber(entity.getCardNumber())
                .cvv(entity.getCvv())
                .pin(entity.getPin())
                .build();
        Link selfLink = linkTo(methodOn(CardCredentialsController.class).getCardCredentials(cardCredentialsDto.getId())).withSelfRel();
        cardCredentialsDto.add(selfLink);
        return cardCredentialsDto;
    }

    @Override
    public CollectionModel<CardCredentialsDto> toCollectionModel(Iterable<? extends CardCredentials> entities) {
        CollectionModel<CardCredentialsDto> cardCredentialsDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(CardCredentialsController.class).getAllCardCredentials()).withSelfRel();
        cardCredentialsDtos.add(selfLink);
        return cardCredentialsDtos;
    }

    public CollectionModel<CardCredentialsDto> toCollectionModel(Iterable<? extends CardCredentials> entities, Link link) {
        CollectionModel<CardCredentialsDto> cardCredentialsDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        cardCredentialsDtos.add(link);
        return cardCredentialsDtos;
    }
}