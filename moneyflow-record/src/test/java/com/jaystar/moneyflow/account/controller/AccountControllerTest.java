package com.jaystar.moneyflow.account.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaystar.moneyflow.account.dto.AccountRequest;
import com.jaystar.moneyflow.account.dto.AccountResponse;
import com.jaystar.moneyflow.account.service.AccountService;
import com.jaystar.moneyflow.company.domain.CompanyType;
import com.jaystar.moneyflow.company.domain.FinancialCompany;
import com.jaystar.moneyflow.company.dto.FinancialCompanyResponse;
import com.jaystar.moneyflow.config.EnableMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@EnableMockMvc
@WebMvcTest(controllers = AccountController.class)
class AccountControllerTest {

    @MockBean
    private AccountService accountService;

    @Autowired
    private MockMvc mockMvc;

    private FinancialCompany financialCompany;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();

        financialCompany = FinancialCompany.builder()
                .name("우리은행")
                .companyType(CompanyType.BANK)
                .usable(Boolean.TRUE)
                .build();
    }

    @DisplayName("단건 계좌를 조회한다.")
    @Test
    void findAccount() throws Exception {
        AccountResponse accountResponse = new AccountResponse(
                1L,
                "급여계좌",
                FinancialCompanyResponse.of(financialCompany),
                LocalDate.of(2022, 1, 1),
                LocalDate.of(2022, 12, 31),
                "11009992",
                "매월 급여가 들어오는 계좌입니다."
        );

        given(accountService.findAccount(anyLong())).willReturn(accountResponse);

        mockMvc.perform(get("/api/accounts/{id}", 1L)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("매월 급여")))
                .andDo(print());
    }

    @DisplayName("모든 계좌를 조회한다.")
    @Test
    void findAllAccounts() throws Exception {

        AccountResponse account1 = new AccountResponse(
                1L,
                "급여계좌",
                FinancialCompanyResponse.of(financialCompany),
                LocalDate.of(2022, 1, 1),
                LocalDate.of(2022, 12, 31),
                "11009992",
                "매월 급여가 들어오는 계좌입니다."
        );

        AccountResponse account2 = new AccountResponse(
                2L,
                "급여계좌2",
                FinancialCompanyResponse.of(financialCompany),
                LocalDate.of(2022, 1, 1),
                LocalDate.of(2022, 12, 31),
                "11009992",
                "매월 급여가 들어오는 계좌입니다."
        );

        List<AccountResponse> accounts = Arrays.asList(account1, account2);

        given(accountService.findAllAccounts()).willReturn(accounts);

        mockMvc.perform(get("/api/accounts")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("")))
                .andDo(print());
    }

    @DisplayName("단건 계좌를 등록한다.")
    @Test
    void createAccount() throws Exception {
        AccountRequest accountRequest = new AccountRequest(
                "급여계좌",
                1L,
                "001999222",
                "20220101",
                "20221231",
                "설명11122"
        );

        given(accountService.saveAccount(any())).willReturn(1L);

        mockMvc.perform(post("/api/accounts")
                        .content(objectMapper.writeValueAsString(accountRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/accounts/1"))
                .andDo(print())
        ;
    }

    @DisplayName("계좌정보를 수정한다.")
    @Test
    void modify() throws Exception {
        AccountResponse accountResponse = new AccountResponse(
                1L,
                "급여계좌",
                FinancialCompanyResponse.of(financialCompany),
                LocalDate.of(2022, 1, 1),
                LocalDate.of(2022, 12, 31),
                "001992333",
                "definition"
        );

        given(accountService.findAccount(anyLong())).willReturn(accountResponse);

        AccountRequest modifyRequest = new AccountRequest("급여계좌-수정",
                1L,
                "111222333",
                "20230101", "20231231",
                "definition-modify");

        mockMvc.perform(put("/api/accounts/{id}", 1L)
                        .content(objectMapper.writeValueAsString(modifyRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @DisplayName("계좌 단건을 삭제한다.")
    @Test
    void deleteAccount() throws Exception {
        mockMvc.perform(delete("/api/accounts/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(print());
    }
}
