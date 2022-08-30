package com.jaystar.moneyflow.code.dto;

import com.jaystar.moneyflow.code.domain.CodeType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CodeTypeRequest {
    private String name;
    
    @Builder
    public CodeTypeRequest(String name) {
        this.name = name;
    }

    public CodeType toCodeType() {
        return CodeType.builder()
                .name(this.name)
                .build();
    }
}
