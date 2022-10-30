package com.jaystar.moneyflow.company.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaystar.moneyflow.company.dto.FinancialCompanyRequest;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@EnableMockMvc
@WebMvcTest(controllers = FinancialCompanyController.class)
class FinancialCompanyControllerTest {

    @MockBean
    private FinancialCompanyService financialCompanyService;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
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

    @DisplayName("금융기관 한건을 저장한다.")
    @Test
    void save() throws Exception {
        given(financialCompanyService.save(any())).willReturn(1L);

        FinancialCompanyRequest financialCompanyRequest = FinancialCompanyRequest.builder()
                .name("대박은행")
                .companyType("BANK")
                .isUsable(true)
                .definition("대박은행에대한 설명입니다")
                .build();

        mockMvc.perform(post("/api/financial-company")
                        .content(objectMapper.writeValueAsString(financialCompanyRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/financial-company/1"))
                .andDo(print());
    }

    @DisplayName("금융기관 한건을 수정한다.")
    @Test
    void update() throws Exception {
        FinancialCompanyRequest financialCompanyRequest = FinancialCompanyRequest.builder()
                .name("대박은행")
                .companyType("BANK")
                .isUsable(true)
                .definition("대박은행에대한 설명입니다")
                .build();

        mockMvc.perform(put("/api/financial-company/{id}", 1L)
                        .content(objectMapper.writeValueAsString(financialCompanyRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @DisplayName("금융기관 한건을 삭제한다.")
    @Test
    void deleteFinancialCompany() throws Exception {
        mockMvc.perform(delete("/api/financial-company/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(print());
    }
}
