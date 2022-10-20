package com.jaystar.moneyflow.code.dto;

import com.jaystar.moneyflow.code.domain.Code;
import com.jaystar.moneyflow.code.domain.CodeType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CodeRequest {
    private String name;

    private long codeTypeId;

    @Builder
    public CodeRequest(String name, long codeTypeId) {
        this.name = name;
        this.codeTypeId = codeTypeId;
    }

    public Code toCode() {
        Code code = Code.builder()
                .name(this.name)
                .codeType(CodeType.builder()
                        .id(this.codeTypeId)
                        .build())
                .build();

        return code;
    }
}
