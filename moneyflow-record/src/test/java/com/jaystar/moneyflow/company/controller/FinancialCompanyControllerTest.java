package com.jaystar.moneyflow.company.controller;

import com.jaystar.moneyflow.company.dto.FinancialCompanyResponse;
import com.jaystar.moneyflow.company.service.FinancialCompanyService;
import com.jaystar.moneyflow.config.EnableMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableMockMvc
@WebMvcTest(controllers = FinancialCompanyController.class)
class FinancialCompanyControllerTest {

    @MockBean
    private FinancialCompanyService financialCompanyService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @DisplayName("모든 금융기관을 조회한다.")
    @Test
    void findAll() throws Exception {
        List<FinancialCompanyResponse> financialCompanies = Arrays.asList(
                FinancialCompanyResponse.builder()
                        .name("대한은행")
                        .companyType("BANK")
                        .isUsable(Boolean.TRUE)
                        .definition("definition 1")
                        .build()
                , FinancialCompanyResponse.builder()
                        .name("우리증권")
                        .companyType("STOCK")
                        .isUsable(Boolean.FALSE)
                        .definition("definition 2")
                        .build()
        );

        given(financialCompanyService.findAll()).willReturn(financialCompanies);

        mockMvc.perform(get("/api/financial-company")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("definition")))
                .andDo(print());
    }

    @DisplayName("금융기관 단건을 조회한다.")
    @Test
    void findFinancialCompany() throws Exception {
        FinancialCompanyResponse financialCompanyResponse = FinancialCompanyResponse.builder()
                .name("대한은행")
                .companyType("BANK")
                .isUsable(true)
                .definition("설명에대한 입력테스트")
                .build();

        given(financialCompanyService.findFinancialCompany(anyLong())).willReturn(financialCompanyResponse);
        mockMvc.perform(get("/api/financial-company/{id}", 1L)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("설명에대한 입력테스트")))
                .andDo(print());
    }
}
