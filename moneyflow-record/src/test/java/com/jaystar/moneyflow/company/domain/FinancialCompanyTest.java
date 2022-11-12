package com.jaystar.moneyflow.company.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FinancialCompanyTest {

    @DisplayName("프로퍼티의 값이 동일하면 동일 객체이다.")
    @Test
    void equal() {
        FinancialCompany financialCompany1 = FinancialCompany.builder()
                .id(1L)
                .name("대한은행")
                .companyType(CompanyType.BANK)
                .usable(Boolean.TRUE)
                .definition("설명문")
                .build();

        FinancialCompany financialCompany2 = FinancialCompany.builder()
                .id(1L)
                .name("대한은행")
                .companyType(CompanyType.BANK)
                .usable(Boolean.TRUE)
                .definition("설명문")
                .build();

        assertThat(financialCompany1).isEqualTo(financialCompany2);
    }
}