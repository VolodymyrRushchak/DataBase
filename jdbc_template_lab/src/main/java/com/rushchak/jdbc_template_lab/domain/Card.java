package com.rushchak.jdbc_template_lab.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Card {
    private Integer id;
    private Integer accountId;
    private Integer cardInfoId;
}
