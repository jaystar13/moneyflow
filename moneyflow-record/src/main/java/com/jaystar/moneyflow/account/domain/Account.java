package com.jaystar.moneyflow.account.domain;

import com.jaystar.moneyflow.company.domain.FinancialCompany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Entity(name = "account")
public class Account {

    @Id
    @GeneratedValue
    @Column(name = "accnt_id")
    private Long id;

    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fnncl_cmp_id")
    private FinancialCompany financialCompany;

    private String accountNo;

    private Date fromDate;

    private Date toDate;

    private String definition;

    @Builder
    public Account(Long id, String name, FinancialCompany financialCompany, String accountNo, Date fromDate, Date toDate, String definition) {
        this.id = id;
        this.name = name;
        this.financialCompany = financialCompany;
        this.accountNo = accountNo;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.definition = definition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(name, account.name) && Objects.equals(financialCompany, account.financialCompany) && Objects.equals(accountNo, account.accountNo) && Objects.equals(fromDate, account.fromDate) && Objects.equals(toDate, account.toDate) && Objects.equals(definition, account.definition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, financialCompany, accountNo, fromDate, toDate, definition);
    }
}
