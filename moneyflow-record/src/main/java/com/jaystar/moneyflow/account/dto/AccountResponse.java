package com.jaystar.moneyflow.account.dto;

import com.jaystar.moneyflow.account.domain.Account;
import com.jaystar.moneyflow.company.dto.FinancialCompanyResponse;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
public class AccountResponse {
    private Long id;

    private String name;

    private FinancialCompanyResponse financialCompany;

    private LocalDate fromDate;

    private LocalDate toDate;

    private String accountNo;

    private String definition;

    public AccountResponse(Long id, String name, FinancialCompanyResponse financialCompany, LocalDate fromDate, LocalDate toDate, String accountNo, String definition) {
        this.id = id;
        this.name = name;
        this.financialCompany = financialCompany;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.accountNo = accountNo;
        this.definition = definition;
    }

    public static AccountResponse of(Account account) {
        return new AccountResponse(account.getId(),
                account.getName(),
                FinancialCompanyResponse.of(account.getFinancialCompany()),
                account.getFromDate(),
                account.getToDate(),
                account.getAccountNo(),
                account.getDefinition());
    }

    public static List<AccountResponse> listOf(List<Account> accounts) {
        List<AccountResponse> responses = new ArrayList<>();

        for (Account account : accounts) {
            responses.add(of(account));
        }

        return responses;
    }
}
