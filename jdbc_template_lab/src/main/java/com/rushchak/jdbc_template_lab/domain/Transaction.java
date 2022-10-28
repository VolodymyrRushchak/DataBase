package com.rushchak.jdbc_template_lab.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Transaction {
    private Integer id;
    private Date date;
    private Float amount;
    private String purpose;
    private Integer sendersCardId;
    private Integer receiversCardId;
}
