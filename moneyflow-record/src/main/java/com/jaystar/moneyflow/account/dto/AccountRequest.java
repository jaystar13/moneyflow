package com.jaystar.moneyflow.account.dto;

import com.jaystar.moneyflow.account.domain.Account;
import com.jaystar.moneyflow.company.domain.FinancialCompany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.jaystar.moneyflow.util.converter.DateConverter.stringToLocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class AccountRequest {

    @NotBlank
    private String name;

    @NotNull
    private Long financialCompanyId;

    @NotBlank
    private String accountNo;

    private String fromDate;

    private String toDate;

    private String definition;

    public Account toAccount(FinancialCompany financialCompany) {
        return new Account(name, financialCompany, accountNo, stringToLocalDate(fromDate), stringToLocalDate(toDate), definition);
    }

}
