package com.jaystar.moneyflow.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DetailCodeTest {
    private MasterCode masterCode;

    private DetailCode detailCode;

    @BeforeEach
    void setup() {
        masterCode = new MasterCode(1L, new CodeItem("A123", "master-code"));
        CodeItem detailCodeItem = new CodeItem("A123-1", "detail-code");

        this.detailCode = DetailCode.builder()
                .id(1L)
                .masterCode(masterCode)
                .codeItem(detailCodeItem)
                .build();
    }

    @Test
    void find() {
        assertThat(detailCode.getMasterCode()).isEqualTo("A123");
        assertThat(detailCode.code()).isEqualTo("A123-1");
        assertThat(detailCode.codeName()).isEqualTo("detail-code");
    }

    @Test
    void update() {
        CodeItem codeItem = new CodeItem("A123-1", "detail-code-update");
        DetailCode updateDetailCode = detailCode.update(DetailCode.builder()
                .id(1L)
                .masterCode(masterCode)
                .codeItem(codeItem)
                .build());

        assertThat(updateDetailCode.codeName()).isEqualTo("detail-code-update");
    }
}
