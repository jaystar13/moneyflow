package com.jaystar.moneyflow.dto;

import com.jaystar.moneyflow.domain.CodeItem;
import com.jaystar.moneyflow.domain.MasterCode;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MasterCodeRequest {

    private String code;

    private String codeName;

    @Builder
    public MasterCodeRequest(String code, String codeName) {
        this.code = code;
        this.codeName = codeName;
    }

    public MasterCode toMasterCode() {
        return MasterCode.builder()
                .codeItem(toCodeItem())
                .build();
    }

    public CodeItem toCodeItem() {
        return new CodeItem(code, codeName);
    }
}
