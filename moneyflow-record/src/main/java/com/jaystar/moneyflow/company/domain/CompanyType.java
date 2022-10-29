package com.jaystar.moneyflow.company.domain;

import com.jaystar.moneyflow.util.mapper.EnumMapperType;

public enum CompanyType implements EnumMapperType {
    BANK("은행"),
    STOCK("증권");

    private String title;

    CompanyType(String title) {
        this.title = title;
    }

    @Override
    public String getCode() {
        return name();
    }

    @Override
    public String getTitle() {
        return title;
    }
}
