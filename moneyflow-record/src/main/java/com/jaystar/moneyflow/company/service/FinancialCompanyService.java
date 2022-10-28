package com.jaystar.moneyflow.company.service;

import com.jaystar.moneyflow.company.domain.FinancialCompany;
import com.jaystar.moneyflow.company.dto.FinancialCompanyRequest;
import com.jaystar.moneyflow.company.repository.FinancialCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FinancialCompanyService {
    private final FinancialCompanyRepository financialCompanyRepository;

    @Transactional
    public Long save(FinancialCompanyRequest request) {
        FinancialCompany financialCompany = request.toFinancialCompany();
        financialCompanyRepository.save(financialCompany);

        return financialCompany.getId();
    }
}
