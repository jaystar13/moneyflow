package com.jaystar.moneyflow.code.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CodeTest {

    @DisplayName("코드 객체 생성을 확인한다.")
    @Test
    void create() {
        Code code = Code.builder()
                .name("code")
                .build();

        Assertions.assertThat(code).isEqualTo(Code.builder()
                .name("code")
                .build());
    }
}
