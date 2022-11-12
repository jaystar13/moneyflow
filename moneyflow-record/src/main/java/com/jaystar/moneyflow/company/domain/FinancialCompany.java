package com.jaystar.moneyflow.company.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Entity(name = "fnncl_cmp")
public class FinancialCompany {

    @Id
    @GeneratedValue
    @Column(name = "fnncl_cmp_id")
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private CompanyType companyType;

    @Column(columnDefinition = "tinyint(1) default 1")
    private Boolean usable = Boolean.TRUE;

    private String definition;

    @Builder
    public FinancialCompany(Long id, String name, CompanyType companyType, Boolean usable, String definition) {
        this.id = id;
        this.name = name;
        this.companyType = companyType;
        this.usable = usable;
        this.definition = definition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinancialCompany that = (FinancialCompany) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && companyType == that.companyType && Objects.equals(usable, that.usable) && Objects.equals(definition, that.definition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, companyType, usable, definition);
    }

    public void update(FinancialCompany financialCompany) {
        this.name = financialCompany.name;
        this.companyType = financialCompany.companyType;
        this.usable = financialCompany.usable;
        this.definition = financialCompany.definition;
    }
}
