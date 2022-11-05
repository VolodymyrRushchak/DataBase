package com.rushchak.springbootlab.dto.assembler;

import com.rushchak.springbootlab.controller.CardInfoController;
import com.rushchak.springbootlab.domain.CardInfo;
import com.rushchak.springbootlab.dto.CardInfoDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CardInfoDtoAssembler implements RepresentationModelAssembler<CardInfo, CardInfoDto> {
    @Override
    public CardInfoDto toModel(CardInfo entity) {
        CardInfoDto cardInfoDto = CardInfoDto.builder()
                .id(entity.getId())
                .balance(entity.getBalance())
                .cardName(entity.getCardName())
                .dateOpened(entity.getDateOpened())
                .dateExpires(entity.getDateExpires())
                .build();
        Link selfLink = linkTo(methodOn(CardInfoController.class).getCardInfo(cardInfoDto.getId())).withSelfRel();
        cardInfoDto.add(selfLink);
        return cardInfoDto;
    }

    @Override
    public CollectionModel<CardInfoDto> toCollectionModel(Iterable<? extends CardInfo> entities) {
        CollectionModel<CardInfoDto> cardInfoDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(CardInfoController.class).getAllCardInfos()).withSelfRel();
        cardInfoDtos.add(selfLink);
        return cardInfoDtos;
    }

    public CollectionModel<CardInfoDto> toCollectionModel(Iterable<? extends CardInfo> entities, Link link) {
        CollectionModel<CardInfoDto> cardInfoDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        cardInfoDtos.add(link);
        return cardInfoDtos;
    }
}