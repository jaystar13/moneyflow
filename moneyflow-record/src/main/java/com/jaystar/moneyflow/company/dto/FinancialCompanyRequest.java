package com.jaystar.moneyflow.company.dto;

import com.jaystar.moneyflow.company.domain.CompanyType;
import com.jaystar.moneyflow.company.domain.FinancialCompany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class FinancialCompanyRequest {
    public String name;

    public String companyType;

    public boolean usable;

    public String definition;

    @Builder
    public FinancialCompanyRequest(String name, String companyType, boolean usable, String definition) {
        this.name = name;
        this.companyType = companyType;
        this.usable = usable;
        this.definition = definition;
    }

    public FinancialCompany toFinancialCompany() {
        return FinancialCompany.builder()
                .name(name)
                .companyType(CompanyType.valueOf(companyType))
                .usable(usable)
                .definition(definition)
                .build();
    }
}
