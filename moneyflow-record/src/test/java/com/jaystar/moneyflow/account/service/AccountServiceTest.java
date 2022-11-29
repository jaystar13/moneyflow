package com.jaystar.moneyflow.account.service;

import com.jaystar.moneyflow.account.domain.Account;
import com.jaystar.moneyflow.account.dto.AccountRequest;
import com.jaystar.moneyflow.account.dto.AccountResponse;
import com.jaystar.moneyflow.account.repository.AccountRepository;
import com.jaystar.moneyflow.common.exception.EntityNotFoundException;
import com.jaystar.moneyflow.common.exception.ErrorCode;
import com.jaystar.moneyflow.company.domain.CompanyType;
import com.jaystar.moneyflow.company.domain.FinancialCompany;
import com.jaystar.moneyflow.company.repository.FinancialCompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private FinancialCompanyRepository financialCompanyRepository;

    private AccountService accountService;

    private FinancialCompany financialCompany;

    @BeforeEach
    void setUp() {
        accountService = new AccountService(accountRepository, financialCompanyRepository);
        financialCompany = FinancialCompany.builder()
                .id(1L)
                .name("대한증권")
                .companyType(CompanyType.STOCK)
                .usable(Boolean.TRUE)
                .definition("설명")
                .build();
    }

    @DisplayName("계좌를 한건 가져온다")
    @Test
    void findAccount() {
        //given
        Account account = new Account(
                "주식계좌",
                financialCompany,
                "123456789",
                LocalDate.of(2022, 1, 1),
                LocalDate.of(2023, 1, 1));

        given(accountRepository.findById(anyLong())).willReturn(Optional.of(account));

        //when
        AccountResponse foundedAccount = accountService.findAccount(1L);

        //then
        assertAll(
                () -> assertThat(foundedAccount.getName()).isEqualTo(account.getName()),
                () -> assertThat(foundedAccount.getAccountNo()).isEqualTo(account.getAccountNo())
        );
    }

    @DisplayName("존재하지 않는 계좌 아이디를 검색 시 예외를 발생한다.")
    @Test
    void findNoExistAccountId() {
        //given
        given(accountRepository.findById(anyLong())).willReturn(Optional.empty());

        //then
        assertThatThrownBy(() -> accountService.findAccount(1L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage(ErrorCode.ACCOUNT_NOT_FOUND.getMessage());
    }

    @DisplayName("모든 계좌를 가져온다")
    @Test
    void findAllAccounts() {
        //given
        Account account1 = new Account(
                "급여계좌",
                financialCompany,
                "12345",
                LocalDate.of(2022, 1, 1),
                LocalDate.of(2023, 1, 1));

        Account account2 = new Account(
                "급여계좌2",
                financialCompany,
                "3545489",
                LocalDate.of(2022, 1, 1),
                LocalDate.of(2023, 1, 1));

        Account account3 = new Account(
                "대출계좌1",
                financialCompany,
                "100258712",
                LocalDate.of(2022, 1, 1),
                LocalDate.of(2023, 1, 1));

        Account account4 = new Account(
                "생활비계좌",
                financialCompany,
                "33555774411",
                LocalDate.of(2022, 1, 1),
                LocalDate.of(2023, 1, 1));

        List<Account> accounts = Arrays.asList(account1, account2, account3, account4);
        given(accountRepository.findAll()).willReturn(accounts);

        //when
        List<AccountResponse> allAccounts = accountService.findAllAccounts();

        //then
        assertThat(allAccounts.size()).isEqualTo(accounts.size());
    }

    @DisplayName("계좌를 단건 생성한다.")
    @Test
    void save() {
        //given
        AccountRequest accountRequest = new AccountRequest(
                "급여계좌",
                1L,
                "122077",
                "20220101",
                "20221231",
                "메인 급여계좌 설명");

        given(financialCompanyRepository.findById(anyLong())).willReturn(Optional.of(financialCompany));

        //when
        accountService.saveAccount(accountRequest);

        //then
        verify(accountRepository).save(accountRequest.toAccount(financialCompany));
    }

    @DisplayName("계좌를 수정한다.")
    @Test
    void update() {
        //given
        Account account = new Account(
                1L,
                "급여계좌",
                financialCompany,
                "11223344",
                LocalDate.of(2022, 1, 1),
                LocalDate.of(2022, 12, 31),
                "설명");
        given(financialCompanyRepository.findById(anyLong())).willReturn(Optional.of(financialCompany));
        given(accountRepository.findById(anyLong())).willReturn(Optional.of(account));

        //when
        AccountRequest modifyRequest = new AccountRequest(
                "급여계좌변경",
                1L,
                "1122334455",
                "20221102",
                "20221228",
                "메인 급여계좌 설명");

        AccountResponse modify = accountService.update(1L, modifyRequest);

        //then
        assertThat(modify.getName()).isEqualTo(modifyRequest.getName());
        assertThat(modify.getAccountNo()).isEqualTo(modifyRequest.getAccountNo());
        assertThat(modify.getFromDate()).isEqualTo(modifyRequest.getFromDate());
        assertThat(modify.getToDate()).isEqualTo(modifyRequest.getToDate());
        assertThat(modify.getDefinition()).isEqualTo(modifyRequest.getDefinition());
    }

    @DisplayName("계좌 단건을 삭제한다.")
    @Test
    void delete() {
        //when
        accountService.delete(1L);

        //then
        verify(accountRepository).deleteById(1L);
    }
}
