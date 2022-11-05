package com.rushchak.springbootlab.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rushchak.springbootlab.domain.Card;
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
@Relation(itemRelation = "transaction", collectionRelation = "transactions")
public class TransactionDto extends RepresentationModel<TransactionDto> {
    private final Integer id;
    private final Date date;
    private final Double amount;
    private final String purpose;
    private final String senderCard;
    private final String receiverCard;
}