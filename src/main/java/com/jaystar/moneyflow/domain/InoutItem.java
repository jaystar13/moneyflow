package com.jaystar.moneyflow.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class InoutItem {
    private Long id;

    private String name;

    private Date fromYearMonth;

    private Date toYearMonth;

    private String displayName;

    private int orderNo;

    private Code classCode;

    private Code categoryCode;

    private boolean isRegular;

    private boolean hasDetail;

    public String className() {
        return classCode.getName();
    }

    public String categoryName() {
        return categoryCode.getName();
    }

}
