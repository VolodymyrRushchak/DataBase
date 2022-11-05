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
@Relation(itemRelation = "home_address", collectionRelation = "home_addresses")
public class HomeAddressDto extends RepresentationModel<HomeAddressDto> {
    private final Integer id;
    private final String country;
    private final String state;
    private final String city;
    private final String streetName;
    private final String houseNumber;
}