package com.jaystar.moneyflow.code.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CodeTypeTest {
    @DisplayName("코드 타입 객체의 프로퍼티가 같으면 동일한 객체이다.")
    @Test
    void equal() {
        CodeType codeType = CodeType.builder()
                .name("코드타입")
                .build();

        assertThat(codeType).isEqualTo(CodeType.builder()
                .name("코드타입")
                .build());
    }

    @DisplayName("코드 타입 객체의 프로퍼티가 같지 않으면 다른 객체이다.")
    @Test
    void notEqual() {
        CodeType codeType = CodeType.builder()
                .name("코드타입")
                .build();

        assertThat(codeType).isNotEqualTo(CodeType.builder()
                .name("코드타입X")
                .build());
    }
}
