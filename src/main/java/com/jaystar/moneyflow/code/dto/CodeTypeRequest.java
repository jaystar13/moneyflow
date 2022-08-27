package com.jaystar.moneyflow.code.dto;

import com.jaystar.moneyflow.code.domain.CodeType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CodeTypeRequest {

    private Long id;

    private String name;
    
    @Builder
    public CodeTypeRequest(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CodeType toCodeType() {
        return CodeType.builder()
                .id(this.id)
                .name(this.name)
                .build();
    }
}
