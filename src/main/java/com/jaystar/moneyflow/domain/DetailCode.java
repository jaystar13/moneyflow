package com.jaystar.moneyflow.domain;

import lombok.Builder;

public class DetailCode {

    private Long id;

    private MasterCode masterCode;

    private CodeItem codeItem;

    @Builder
    public DetailCode(Long id, MasterCode masterCode, CodeItem codeItem) {
        this.id = id;
        this.masterCode = masterCode;
        this.codeItem = codeItem;
    }

    public String getMasterCode() {
        return masterCode.code();
    }

    public String code() {
        return codeItem.getCode();
    }

    public String codeName() {
        return codeItem.getCodeName();
    }

    public DetailCode update(DetailCode requestDetailCode) {
        this.codeItem = requestDetailCode.codeItem;
        return this;
    }
}
