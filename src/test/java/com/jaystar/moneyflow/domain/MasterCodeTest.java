package com.jaystar.moneyflow.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MasterCodeTest {
    private MasterCode masterCode;

    @BeforeEach
    void setup() {
        masterCode = new MasterCode(1L, "A123", "CODE");
    }

    @Test
    void find() {
        assertThat(masterCode.getCode()).isEqualTo("A123");
        assertThat(masterCode.getCodeName()).isEqualTo("CODE");
    }

    @Test
    void updateCodeName() {
        masterCode.updateCodeName("UPDATE_CODE");
        assertThat(masterCode.getCodeName()).isEqualTo("UPDATE_CODE");
    }
}
