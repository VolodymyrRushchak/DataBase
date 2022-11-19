package com.rushchak.springbootlab.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.sql.Date;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "card_info", collectionRelation = "many_card_info")
public class CardInfoDto extends RepresentationModel<CardInfoDto> {
    private final Integer id;
    private final Double balance;
    private final String cardName;
    private final Date dateOpened;
    private final Date dateExpires;
}