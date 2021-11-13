package com.jaystar.moneyflow.domain;

import lombok.Builder;

import javax.persistence.Embedded;
import javax.persistence.Id;

public class MasterCode {

    @Id
    private Long id;

    @Embedded
    private CodeItem codeItem;

    @Builder
    public MasterCode(Long id, CodeItem codeItem) {
        this.id = id;
        this.codeItem = codeItem;
    }

    public MasterCode update(MasterCode masterCodeRequest) {
        this.codeItem = masterCodeRequest.codeItem;

        return this;
    }

    public Long getId() {
        return this.id;
    }

    public String code() {
        return codeItem.getCode();
    }

    public String codeName() {
        return codeItem.getCodeName();
    }

    public void setId(Long id) {
        this.id = id;
    }
}
