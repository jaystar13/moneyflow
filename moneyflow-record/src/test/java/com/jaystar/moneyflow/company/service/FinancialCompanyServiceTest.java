package com.jaystar.moneyflow.company.service;

import com.jaystar.moneyflow.common.exception.EntityNotFoundException;
import com.jaystar.moneyflow.common.exception.ErrorCode;
import com.jaystar.moneyflow.company.domain.CompanyType;
import com.jaystar.moneyflow.company.domain.FinancialCompany;
import com.jaystar.moneyflow.company.dto.FinancialCompanyRequest;
import com.jaystar.moneyflow.company.dto.FinancialCompanyResponse;
import com.jaystar.moneyflow.company.repository.FinancialCompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

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
        FinancialCompanyRequest financialCompanyRequest = FinancialCompanyRequest.builder()
                .name("우리증권")
                .companyType("STOCK")
                .isUsable(Boolean.TRUE)
                .definition("설명설명설명").build();

        financialCompanyService.save(financialCompanyRequest);

        verify(financialCompanyRepository).save(financialCompanyRequest.toFinancialCompany());
    }

    @DisplayName("금융기관 단건을 조회한다.")
    @Test
    void find() {
        FinancialCompany financialCompany = FinancialCompany.builder()
                .name("우리증권")
                .companyType(CompanyType.STOCK)
                .isUsable(Boolean.TRUE)
                .definition("설명설명설명").build();

        given(financialCompanyRepository.findById(anyLong())).willReturn(Optional.of(financialCompany));

        FinancialCompanyResponse response = financialCompanyService.findFinancialCompany(1L);

        assertAll(
                () -> assertThat(response.getName()).isEqualTo(financialCompany.getName()),
                () -> assertThat(response.getCompanyType()).isEqualTo(financialCompany.getCompanyType().name()),
                () -> assertThat(response.getIsUsable()).isEqualTo(financialCompany.getIsUsable()),
                () -> assertThat(response.getDefinition()).isEqualTo(financialCompany.getDefinition()));
    }

    @DisplayName("모든 금융기관을 조회한다.")
    @Test
    void findAll() {
        FinancialCompany financialCompany1 = FinancialCompany.builder()
                .name("null")
                .companyType(CompanyType.BANK)
                .isUsable(Boolean.TRUE)
                .definition("null")
                .build();

        FinancialCompany financialCompany2 = FinancialCompany.builder()
                .name("null")
                .companyType(CompanyType.BANK)
                .isUsable(Boolean.TRUE)
                .definition("null")
                .build();

        given(financialCompanyRepository.findAll()).willReturn(Arrays.asList(financialCompany1, financialCompany2));

        List<FinancialCompanyResponse> financialCompanyResponses = financialCompanyService.findAll();

        assertThat(financialCompanyResponses).containsExactly(FinancialCompanyResponse.of(financialCompany1),
                FinancialCompanyResponse.of(financialCompany1));
    }

    @DisplayName("금융기관을 수정한다.")
    @Test
    void update() {
        FinancialCompany financialCompany = FinancialCompany.builder()
                .name("대한은행")
                .companyType(CompanyType.BANK)
                .isUsable(Boolean.TRUE)
                .definition("설명123")
                .build();

        FinancialCompanyRequest financialCompanyRequest = FinancialCompanyRequest.builder()
                .name("우리증권")
                .companyType("STOCK")
                .isUsable(Boolean.FALSE)
                .definition("수정34567")
                .build();

        given(financialCompanyRepository.findById(anyLong())).willReturn(Optional.of(financialCompany));

        FinancialCompanyResponse update = financialCompanyService.update(1L, financialCompanyRequest);

        assertAll(
                () -> assertThat(update.getName()).isEqualTo(financialCompanyRequest.getName()),
                () -> assertThat(update.getCompanyType()).isEqualTo(financialCompanyRequest.getCompanyType()),
                () -> assertThat(update.getIsUsable()).isEqualTo(financialCompanyRequest.isUsable()),
                () -> assertThat(update.getDefinition()).isEqualTo(financialCompanyRequest.getDefinition())
        );
    }

    @DisplayName("금융기관 단건을 삭제한다.")
    @Test
    void delete() {
        financialCompanyService.delete(1L);

        verify(financialCompanyRepository).deleteById(1L);
    }

    @DisplayName("존재하지 않는 금융기관 id 조회시 예외가 발생한다.")
    @Test
    void findNotExistFinanceCompany() {
        given(financialCompanyRepository.findById(anyLong())).willReturn(Optional.empty());

        assertThatThrownBy(() -> financialCompanyService.findFinancialCompany(1L))
                .hasMessage(ErrorCode.FINANCIAL_COMPANY_NOT_FOUND.getMessage())
                .isInstanceOf(EntityNotFoundException.class);
    }
}
