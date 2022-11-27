package com.jaystar.moneyflow.account.domain;

import com.jaystar.moneyflow.company.domain.FinancialCompany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
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

    private LocalDate fromDate;

    private LocalDate toDate;

    private String definition;

    public Account(String name, FinancialCompany financialCompany, String accountNo, LocalDate fromDate, LocalDate toDate) {
        this(null, name, financialCompany, accountNo, fromDate, toDate, "");
    }

    public Account(String name, FinancialCompany financialCompany, String accountNo, LocalDate fromDate, LocalDate toDate, String definition) {
        this(null, name, financialCompany, accountNo, fromDate, toDate, definition);
    }

    public Account(Long id, String name, FinancialCompany financialCompany, String accountNo, LocalDate fromDate, LocalDate toDate, String definition) {
        this.id = id;
        this.name = name;
        this.financialCompany = financialCompany;
        this.accountNo = accountNo;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.definition = definition;

        if (!checkDate(this.fromDate, this.toDate)) {
            throw new IllegalArgumentException("시작일자가 종료일자 보다 클 수 없습니다.");
        }
    }

    private boolean checkDate(LocalDate fromDate, LocalDate toDate) {
        if (fromDate == null || toDate == null) {
            return false;
        }

        return fromDate.compareTo(toDate) <= 0;
    }

    public void update(Account account) {
        this.name = account.name;
        this.financialCompany = account.financialCompany;
        this.accountNo = account.accountNo;
        this.fromDate = account.fromDate;
        this.toDate = account.toDate;
        this.definition = account.definition;
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
