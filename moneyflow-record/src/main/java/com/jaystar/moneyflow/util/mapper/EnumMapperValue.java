package com.jaystar.moneyflow.util.mapper;

import lombok.Getter;

@Getter
public class EnumMapperValue {
    private String code;
    private String title;

    public EnumMapperValue(EnumMapperType enumMapperType) {
        this.code = enumMapperType.getCode();
        this.title = enumMapperType.getTitle();
    }
}
