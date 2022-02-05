package com.jaystar.moneyflow.code.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CodeTest {

    @DisplayName("코드 객체 생성을 확인한다.")
    @Test
    void create() {
        Code code = new Code();

        Assertions.assertThat(code).isEqualTo(new Code());
    }
}
