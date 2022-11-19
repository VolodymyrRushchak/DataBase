package com.rushchak.springbootlab.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "card_credentials", collectionRelation = "many_card_credentials")
public class CardCredentialsDto extends RepresentationModel<CardCredentialsDto> {
    private final Integer id;
    private final String cardNumber;
    private final String cvv;
    private final String pin;
}