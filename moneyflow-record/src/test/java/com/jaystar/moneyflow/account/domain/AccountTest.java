package com.jaystar.moneyflow.account.domain;

import com.jaystar.moneyflow.company.domain.CompanyType;
import com.jaystar.moneyflow.company.domain.FinancialCompany;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {

    @DisplayName("동일성 비교")
    @Test
    void bankName() {
        //given
        FinancialCompany financialCompany = FinancialCompany.builder()
                .id(1L)
                .name("대박은행")
                .companyType(CompanyType.BANK)
                .build();

        Date fromDate = new Date();
        Date toDate = new Date();

        Account account1 = Account.builder()
                .id(2L)
                .name("은행계좌")
                .financialCompany(financialCompany)
                .accountNo("123456789")
                .fromDate(fromDate)
                .toDate(toDate)
                .build();

        Account account2 = Account.builder()
                .id(2L)
                .name("은행계좌")
                .financialCompany(financialCompany)
                .accountNo("123456789")
                .fromDate(fromDate)
                .toDate(toDate)
                .build();
        //when

        //then
        assertThat(account1).isEqualTo(account2);
    }
}
