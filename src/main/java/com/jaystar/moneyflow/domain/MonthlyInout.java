package com.jaystar.moneyflow.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class MonthlyInout {

    private Long id;

    private Date targetMonth;

    private String note;

    private List<MonthlyInoutItem> monthlyInoutItems;

}
