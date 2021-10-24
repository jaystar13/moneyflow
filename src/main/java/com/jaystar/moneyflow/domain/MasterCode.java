package com.jaystar.moneyflow.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class MasterCode {

    @Setter
    private Long id;

    private String code;

    private String codeName;

    public void modifyCodeName(String codeName) {
        this.codeName = codeName;
    }
}
