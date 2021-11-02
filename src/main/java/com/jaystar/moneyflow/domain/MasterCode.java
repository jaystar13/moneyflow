package com.jaystar.moneyflow.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;

@Getter
public class MasterCode {

    @Id
    @Setter
    private Long id;

    private String code;

    private String codeName;

    @Builder
    public MasterCode(Long id, String code, String codeName) {
        this.id = id;
        this.code = code;
        this.codeName = codeName;
    }

    public void modifyCodeName(String codeName) {
        this.codeName = codeName;
    }
}
