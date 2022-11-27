package com.jaystar.moneyflow.account.dto;

import com.jaystar.moneyflow.account.domain.Account;
import com.jaystar.moneyflow.company.domain.FinancialCompany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class AccountRequest {

    private String name;

    private Long financialCompanyId;

    private String accountNo;

    private LocalDate fromDate;

    private LocalDate toDate;

    private String definition;

    public Account toAccount(FinancialCompany financialCompany) {
        return new Account(name, financialCompany, accountNo, fromDate, toDate, definition);
    }

}
