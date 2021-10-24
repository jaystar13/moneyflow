package com.jaystar.moneyflow.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class DetailCode {

    @Getter
    private long id;

    private MasterCode masterCode;

    @Getter
    private final String detailCode;

    @Getter
    private String detailCodeName;

    public void modifyDetailCodeName(String detailCodeName) {
        this.detailCodeName = detailCodeName;
    }

    public String getMasterCode() {
        return this.masterCode.getCode();
    }

    public String getMasterCodeName() {
        return this.masterCode.getCodeName();
    }
}
