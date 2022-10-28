package com.jaystar.moneyflow.company.service;

import com.jaystar.moneyflow.company.repository.FinancialCompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FinancialCompanyServiceTest {

    private FinancialCompanyService financialCompanyService;

    @Mock
    private FinancialCompanyRepository financialCompanyRepository;

    @BeforeEach
    void setUp() {
        financialCompanyService = new FinancialCompanyService(financialCompanyRepository);
    }

    @DisplayName("금융기관이 정상적으로 등록된다.")
    @Test
    void save() {

    }
}