package com.jaystar.moneyflow.code.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CodeTypeTest {
    @DisplayName("코드 타입 객체 생성을 확인한다.")
    @Test
    void create() {
        CodeType codeType = CodeType.builder()
                .name("코드타입")
                .build();

        assertThat(codeType).isEqualTo(CodeType.builder()
                .name("코드타입")
                .build());
    }
}
