package com.jaystar.moneyflow.account.domain;

import com.jaystar.moneyflow.company.domain.CompanyType;
import com.jaystar.moneyflow.company.domain.FinancialCompany;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AccountTest {

    private FinancialCompany financialCompany;

    @BeforeEach
    void setUp() {
        financialCompany = FinancialCompany.builder()
                .id(1L)
                .name("대박은행")
                .companyType(CompanyType.BANK)
                .build();
    }

    @DisplayName("두 객체의 값이 값으면 equal 결과는 true 를 리턴한다.")
    @Test
    void equal() {
        //given
        LocalDate fromDate = LocalDate.of(2022, 11, 1);
        LocalDate toDate = LocalDate.of(2023, 11, 1);

        Account account1 = new Account(2L, "은행계좌", financialCompany, "123456789", fromDate, toDate, "");
        Account account2 = new Account(2L, "은행계좌", financialCompany, "123456789", fromDate, toDate, "");

        //then
        assertThat(account1).isEqualTo(account2);
    }

    @DisplayName("시작일자가 종료일자 보다 큰 경우 예외가 발생한다.")
    @Test
    void checkDate() {
        //given
        LocalDate fromDate = LocalDate.of(2024, 11, 1);
        LocalDate toDate = LocalDate.of(2023, 11, 1);

        //then
        assertThatThrownBy(() ->
                new Account(1L, "은행", financialCompany, "1", fromDate, toDate, ""))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
