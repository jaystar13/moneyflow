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

    private CodeRequest parent;

    @Builder
    public CodeRequest(String name, CodeRequest parent) {
        this.name = name;
        this.parent = parent;
    }

    public Code toCode() {
        Code code = new Code();
        code.setName(this.name);
        code.setParent(this.parent.toCode());

        return code;
    }
}
