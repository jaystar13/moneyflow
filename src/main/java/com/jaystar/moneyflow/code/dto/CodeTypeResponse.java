package com.jaystar.moneyflow.code.dto;

import com.jaystar.moneyflow.code.domain.CodeType;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CodeTypeResponse {

    private Long id;

    private String name;

    private List<CodeResponse> codes;

    public static CodeTypeResponse of(CodeType codeType) {
        return new CodeTypeResponse(codeType.getId(), codeType.getName(), CodeResponse.listOf(codeType.getCodes()));
    }

    public static List<CodeTypeResponse> listOf(List<CodeType> codeTypes) {
        List<CodeTypeResponse> codeTypeResponses = new ArrayList<>();

        for (CodeType codeType : codeTypes) {
            codeTypeResponses.add(of(codeType));
        }

        return codeTypeResponses;
    }
}
