package com.rushchak.jdbc_template_lab.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Account {
    private Integer id;
    private Integer clientId;
    private String accountName;
    private Date dateCreated;
}
