package com.jaystar.moneyflow.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MasterCode {
    private final Long id;

    private final String code;

    private String codeName;

    public void updateCodeName(String codeName) {
        this.codeName = codeName;
    }
}
