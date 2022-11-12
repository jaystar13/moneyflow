package com.jaystar.moneyflow.company.dto;

import com.jaystar.moneyflow.company.domain.FinancialCompany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class FinancialCompanyResponse {

    private Long id;

    private String name;

    private String companyType;

    private String companyTypeTitle;

    private boolean isUsable;

    private String definition;

    @Builder
    public FinancialCompanyResponse(Long id, String name, String companyType, String companyTypeTitle, boolean isUsable, String definition) {
        this.id = id;
        this.name = name;
        this.companyType = companyType;
        this.companyTypeTitle = companyTypeTitle;
        this.isUsable = isUsable;
        this.definition = definition;
    }

    public static FinancialCompanyResponse of(FinancialCompany financialCompany) {
        return FinancialCompanyResponse.builder()
                .id(financialCompany.getId())
                .name(financialCompany.getName())
                .companyType(financialCompany.getCompanyType().name())
                .companyTypeTitle(financialCompany.getCompanyType().getTitle())
                .isUsable(financialCompany.getUsable().booleanValue())
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
