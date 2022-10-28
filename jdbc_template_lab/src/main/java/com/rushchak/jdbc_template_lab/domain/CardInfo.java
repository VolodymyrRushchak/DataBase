package com.rushchak.jdbc_template_lab.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CardInfo {
    private Integer id;
    private Float balance;
    private String cardName;
    private Date dateOpened;
    private Date dateExpires;
}
