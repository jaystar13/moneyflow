package com.jaystar.moneyflow.monthlyinout.domain;

import com.jaystar.moneyflow.inoutitem.domain.InoutItem;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MonthlyInoutItem {

    private Long id;

    private InoutItem inoutItem;

    private int amount;

    private List<MonthlyInoutItemDetail> details;
}
