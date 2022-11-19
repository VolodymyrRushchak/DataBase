package com.rushchak.springbootlab.dto.assembler;

import com.rushchak.springbootlab.controller.AccountController;
import com.rushchak.springbootlab.controller.CardController;
import com.rushchak.springbootlab.domain.Account;
import com.rushchak.springbootlab.dto.AccountDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AccountDtoAssembler implements RepresentationModelAssembler<Account, AccountDto> {
    @Override
    public AccountDto toModel(Account entity) {
        AccountDto accountDto = AccountDto.builder()
                .id(entity.getId())
                .accountName(entity.getAccountName())
                .dateCreated(entity.getDateCreated())
                .client(entity.getClient().getName() + " " + entity.getClient().getSurname())
                .build();
        Link selfLink = linkTo(methodOn(AccountController.class).getAccount(accountDto.getId())).withSelfRel();
        accountDto.add(selfLink);
        Link cardsLink = linkTo(methodOn(CardController.class).getCardsByAccountId(entity.getId())).withRel("cards");
        accountDto.add(cardsLink);
        return accountDto;
    }

    @Override
    public CollectionModel<AccountDto> toCollectionModel(Iterable<? extends Account> entities) {
        CollectionModel<AccountDto> accountDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(AccountController.class).getAllAccounts()).withSelfRel();
        accountDtos.add(selfLink);
        return accountDtos;
    }

    public CollectionModel<AccountDto> toCollectionModel(Iterable<? extends Account> entities, Link link) {
        CollectionModel<AccountDto> accountDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        accountDtos.add(link);
        return accountDtos;
    }
}
