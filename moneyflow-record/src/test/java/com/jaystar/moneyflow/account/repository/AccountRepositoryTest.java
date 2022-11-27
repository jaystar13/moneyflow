package com.jaystar.moneyflow.account.repository;

import com.jaystar.moneyflow.account.domain.Account;
import com.jaystar.moneyflow.company.domain.CompanyType;
import com.jaystar.moneyflow.company.domain.FinancialCompany;
import com.jaystar.moneyflow.company.repository.FinancialCompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AccountRepositoryTest {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private FinancialCompanyRepository financialCompanyRepository;

    private FinancialCompany saveFinancialCompany;

    @BeforeEach
    void setUp() {
        FinancialCompany financialCompany = FinancialCompany.builder()
                .id(1L)
                .name("KB은행")
                .companyType(CompanyType.BANK)
                .usable(Boolean.TRUE)
                .build();

        saveFinancialCompany = financialCompanyRepository.saveAndFlush(financialCompany);
    }

    @DisplayName("계좌가 저장된다.")
    @Test
    void save() {
        //given
        Account account = new Account("대출계좌",
                saveFinancialCompany,
                "012345678990",
                LocalDate.of(2022, 1, 1),
                LocalDate.of(2023, 1, 1),
                "설명");

        //when
        Account save = accountRepository.saveAndFlush(account);

        //then
        assertThat(save).isEqualTo(account);
    }

    @DisplayName("계좌를 모두 조회한다.")
    @Test
    void findAll() {
        //given
        saveAccounts();

        //when
        List<Account> accounts = accountRepository.findAll();

        //then
        assertThat(accounts.size()).isEqualTo(3);
    }

    @DisplayName("계좌 단건을 조회한다.")
    @Test
    void find() {
        //given
        Account save = saveAccounts().get(0);

        //when
        Account account = accountRepository.findById(save.getId()).get();

        //then
        assertThat(account).isEqualTo(save);
    }

    @DisplayName("계좌 한건을 삭제한다.")
    @Test
    void delete() {
        //given
        List<Account> saves = saveAccounts();

        //when
        accountRepository.delete(saves.get(0));

        //then
        assertThat(accountRepository.findAll().size()).isEqualTo(saves.size() - 1);
    }

    private List<Account> saveAccounts() {

        Account account1 = new Account("대출계좌",
                saveFinancialCompany,
                "012345678990",
                LocalDate.of(2022, 1, 1),
                LocalDate.of(2023, 1, 1),
                "설명1");
        Account account2 = new Account("급여계좌",
                saveFinancialCompany,
                "100110004",
                LocalDate.of(2022, 1, 1),
                LocalDate.of(2023, 1, 1),
                "설명2");
        Account account3 = new Account("대출계좌",
                saveFinancialCompany,
                "5905015488",
                LocalDate.of(2022, 1, 1),
                LocalDate.of(2023, 1, 1),
                "설명3");

        return accountRepository.saveAll(Arrays.asList(account1, account2, account3));
    }
}
