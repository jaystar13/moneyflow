package com.jaystar.moneyflow.company.dto;

import com.jaystar.moneyflow.company.domain.FinancialCompany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class FinancialCompanyResponse {
    private String name;

    private String companyType;

    private Boolean isUsable;

    private String definition;

    @Builder
    public FinancialCompanyResponse(String name, String companyType, Boolean isUsable, String definition) {
        this.name = name;
        this.companyType = companyType;
        this.isUsable = isUsable;
        this.definition = definition;
    }

    public static FinancialCompanyResponse of(FinancialCompany financialCompany) {
        return FinancialCompanyResponse.builder()
                .name(financialCompany.getName())
                .companyType(financialCompany.getCompanyType().name())
                .isUsable(financialCompany.getIsUsable())
                .definition(financialCompany.getDefinition())
                .build();
    }

    public static List<FinancialCompanyResponse> listOf(List<FinancialCompany> financialCompanies) {
        List<FinancialCompanyResponse> financialCompanyResponses = new ArrayList<>();

        for (FinancialCompany financialCompany : financialCompanies) {
            financialCompanyResponses.add(of(financialCompany));
        }

        return financialCompanyResponses;
    }
}
