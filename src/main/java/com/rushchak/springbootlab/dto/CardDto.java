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
@Relation(itemRelation = "card", collectionRelation = "cards")
public class CardDto extends RepresentationModel<CardDto> {
    private final Integer id;
    private final String account;
    private final String cardInfo;
    private final String cardCredentials;
}