package com.jaystar.moneyflow.company.repository;

import com.jaystar.moneyflow.company.domain.FinancialCompany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancialCompanyRepository extends JpaRepository<FinancialCompany, Long> {
}
