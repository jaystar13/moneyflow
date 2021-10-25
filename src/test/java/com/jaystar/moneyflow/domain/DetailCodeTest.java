package com.jaystar.moneyflow.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DetailCodeTest {
    private DetailCode detailCode;

    @BeforeEach
    void setup() {
        String detailCode = "A123-1";
        String detailCodeName = "첫번째상세코드";

        this.detailCode = new DetailCode(10L, "A123", detailCode, detailCodeName);
    }

    @Test
    void find() {
        assertThat(detailCode.getMasterCode()).isEqualTo("A123");
        assertThat(detailCode.getDetailCode()).isEqualTo("A123-1");
        assertThat(detailCode.getDetailCodeName()).isEqualTo("첫번째상세코드");
    }

    @Test
    void updateDetailCodeName() {
        detailCode.modifyDetailCodeName("update 첫번째상세코드");
        assertThat(detailCode.getDetailCodeName()).isEqualTo("update 첫번째상세코드");
    }
}
