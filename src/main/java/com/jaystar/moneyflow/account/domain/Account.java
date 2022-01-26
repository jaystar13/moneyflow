package com.jaystar.moneyflow.account.domain;

import com.jaystar.moneyflow.code.domain.Code;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Account {

    private Long id;

    private String name;

    private Code bankCode;

    private String accountNo;

    private Date fromDate;

    private Date toDate;

    private String definition;

    public String bankName() {
        return bankCode.getName();
    }

}
