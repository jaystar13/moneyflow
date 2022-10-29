package com.jaystar.moneyflow.company.service;

import com.jaystar.moneyflow.common.exception.EntityNotFoundException;
import com.jaystar.moneyflow.common.exception.ErrorCode;
import com.jaystar.moneyflow.company.domain.FinancialCompany;
import com.jaystar.moneyflow.company.dto.FinancialCompanyRequest;
import com.jaystar.moneyflow.company.dto.FinancialCompanyResponse;
import com.jaystar.moneyflow.company.repository.FinancialCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

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

    @Transactional(readOnly = true)
    public FinancialCompanyResponse findFinancialCompany(Long id) {
        return FinancialCompanyResponse.of(findById(id));
    }

    private FinancialCompany findById(Long id) {
        return financialCompanyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.CODE_TYPE_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public List<FinancialCompanyResponse> findAll() {
        return Collections.unmodifiableList(FinancialCompanyResponse.listOf(financialCompanyRepository.findAll()));
    }

    @Transactional
    public FinancialCompanyResponse update(Long id, FinancialCompanyRequest request) {
        FinancialCompany financialCompany = findById(id);
        financialCompany.update(request.toFinancialCompany());

        return FinancialCompanyResponse.of(financialCompany);
    }
}
