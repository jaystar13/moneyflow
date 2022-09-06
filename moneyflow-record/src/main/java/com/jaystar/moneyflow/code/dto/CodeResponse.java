package com.jaystar.moneyflow.code.dto;

import com.jaystar.moneyflow.code.domain.Code;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class CodeResponse {
    private Long id;

    private String name;

    public static CodeResponse of(Code code) {
        return new CodeResponse(code.getId(), code.getName());
    }

    public static List<CodeResponse> listOf(List<Code> codes) {
        List<CodeResponse> codeResponses = new ArrayList<>();

        for (Code code : codes) {
            codeResponses.add(of(code));
        }

        return codeResponses;
    }
}
