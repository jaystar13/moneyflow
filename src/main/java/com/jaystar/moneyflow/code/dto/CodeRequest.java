package com.jaystar.moneyflow.code.dto;

import com.jaystar.moneyflow.code.domain.Code;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CodeRequest {
    private String name;

    @Builder
    public CodeRequest(String name) {
        this.name = name;
    }

    public Code toCode() {
        Code code = new Code();
        code.setName(this.name);

        return code;
    }
}
