package com.jaystar.moneyflow.inoutitem.domain;

import com.jaystar.moneyflow.account.domain.Account;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class InoutItemAccount {

    private Long id;

    private InoutItem inoutItem;

    private Account account;

    private Date fromDate;

    private Date toDate;

    private int directDebitDay;

    private String note;
}
