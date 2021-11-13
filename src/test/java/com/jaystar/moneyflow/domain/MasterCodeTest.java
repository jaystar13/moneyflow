package com.jaystar.moneyflow.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MasterCodeTest {
    private MasterCode masterCode;

    @BeforeEach
    void setup() {
        masterCode = new MasterCode(1L, new CodeItem("A123", "CODE"));
    }

    @Test
    void find() {
        assertThat(masterCode.code()).isEqualTo("A123");
        assertThat(masterCode.codeName()).isEqualTo("CODE");
    }

    @Test
    void update() {
        masterCode.update(new MasterCode(1L, new CodeItem("A1", "UPDATE_CODE")));

        assertThat(masterCode.codeName()).isEqualTo("UPDATE_CODE");
    }
}
