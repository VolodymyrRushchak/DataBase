package com.rushchak.springbootlab.dto.assembler;

import com.rushchak.springbootlab.controller.CardController;
import com.rushchak.springbootlab.domain.Card;
import com.rushchak.springbootlab.dto.CardDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CardDtoAssembler implements RepresentationModelAssembler<Card, CardDto> {
    @Override
    public CardDto toModel(Card entity) {
        CardDto cardDto = CardDto.builder()
                .id(entity.getId())
                .account(entity.getAccount().getAccountName())
                .cardInfo(entity.getCardInfo().getBalance().toString())
                .cardCredentials(entity.getCardCredentials().getCardNumber())
                .build();
        Link selfLink = linkTo(methodOn(CardController.class).getCard(cardDto.getId())).withSelfRel();
        cardDto.add(selfLink);
        Link receiversLink = linkTo(methodOn(CardController.class).getReceiversBySenderId(entity.getId())).withRel("receivers");
        cardDto.add(receiversLink);
        Link sendersLink = linkTo(methodOn(CardController.class).getSendersByReceiverId(entity.getId())).withRel("senders");
        cardDto.add(sendersLink);
        return cardDto;
    }

    @Override
    public CollectionModel<CardDto> toCollectionModel(Iterable<? extends Card> entities) {
        CollectionModel<CardDto> cardDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(CardController.class).getAllCards()).withSelfRel();
        cardDtos.add(selfLink);
        return cardDtos;
    }

    public CollectionModel<CardDto> toCollectionModel(Iterable<? extends Card> entities, Link link) {
        CollectionModel<CardDto> cardDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        cardDtos.add(link);
        return cardDtos;
    }
}