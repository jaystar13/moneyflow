package com.jaystar.moneyflow.company.repository;

import com.jaystar.moneyflow.company.domain.CompanyType;
import com.jaystar.moneyflow.company.domain.FinancialCompany;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class FinancialCompanyRepositoryTest {
    @Autowired
    private FinancialCompanyRepository financialCompanyRepository;

    @DisplayName("금융기관 등록이 정상적으로 실행된다.")
    @Test
    void save() {
        FinancialCompany financialCompany = FinancialCompany.builder()
                .name("대박은행")
                .companyType(CompanyType.BANK)
                .isUsable(Boolean.TRUE)
                .definition("대박은행에대한 설명입니다.")
                .build();

        FinancialCompany save = financialCompanyRepository.saveAndFlush(financialCompany);

        assertThat(financialCompany).isEqualTo(save);
    }
}
