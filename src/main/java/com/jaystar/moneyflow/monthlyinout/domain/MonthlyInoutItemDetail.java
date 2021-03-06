package com.jaystar.moneyflow.monthlyinout.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonthlyInoutItemDetail {

    private Long id;

    private MonthlyInoutItem parent;

    private String itemName;

    private int amount;
}
