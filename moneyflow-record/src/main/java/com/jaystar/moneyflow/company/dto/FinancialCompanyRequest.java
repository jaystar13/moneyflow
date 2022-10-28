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

    public String companyTypeName;

    public boolean isUsable;

    public String definition;

    @Builder
    public FinancialCompanyRequest(String name, String companyTypeName, boolean isUsable, String definition) {
        this.name = name;
        this.companyTypeName = companyTypeName;
        this.isUsable = isUsable;
        this.definition = definition;
    }

    public FinancialCompany toFinancialCompany() {
        return FinancialCompany.builder()
                .name(name)
                .companyType(CompanyType.valueOf(companyTypeName))
                .isUsable(isUsable)
                .definition(definition)
                .build();
    }
}
