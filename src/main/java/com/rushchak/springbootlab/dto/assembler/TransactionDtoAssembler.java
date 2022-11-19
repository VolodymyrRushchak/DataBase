package com.rushchak.springbootlab.dto.assembler;

import com.rushchak.springbootlab.controller.TransactionController;
import com.rushchak.springbootlab.domain.Transaction;
import com.rushchak.springbootlab.domain.Transaction;
import com.rushchak.springbootlab.dto.TransactionDto;
import com.rushchak.springbootlab.dto.TransactionDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.sql.Date;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TransactionDtoAssembler implements RepresentationModelAssembler<Transaction, TransactionDto> {
    
    @Override
    public TransactionDto toModel(Transaction entity) {
        TransactionDto transactionDto = TransactionDto.builder()
                .id(entity.getId())
                .date(entity.getDate())
                .amount(entity.getAmount())
                .purpose(entity.getPurpose())
                .senderCard(entity.getSenderCard().toString())
                .receiverCard(entity.getReceiverCard().toString())
                .build();
        Link selfLink = linkTo(methodOn(TransactionController.class).getTransaction(transactionDto.getId())).withSelfRel();
        transactionDto.add(selfLink);
        return transactionDto;
    }

    @Override
    public CollectionModel<TransactionDto> toCollectionModel(Iterable<? extends Transaction> entities) {
        CollectionModel<TransactionDto> transactionDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(TransactionController.class).getAllTransactions()).withSelfRel();
        transactionDtos.add(selfLink);
        return transactionDtos;
    }

    public CollectionModel<TransactionDto> toCollectionModel(Iterable<? extends Transaction> entities, Link link) {
        CollectionModel<TransactionDto> transactionDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        transactionDtos.add(link);
        return transactionDtos;
    }

}