package com.jaystar.moneyflow.company.controller;

import com.jaystar.moneyflow.company.domain.CompanyType;
import com.jaystar.moneyflow.company.dto.FinancialCompanyResponse;
import com.jaystar.moneyflow.company.service.FinancialCompanyService;
import com.jaystar.moneyflow.util.mapper.EnumMapper;
import com.jaystar.moneyflow.util.mapper.EnumMapperValue;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/financial-company")
public class FinancialCompanyController {

    private final EnumMapper enumMapper;

    private final FinancialCompanyService financialCompanyService;

    public FinancialCompanyController(FinancialCompanyService financialCompanyService) {
        this.enumMapper = getEnumMapper();
        this.financialCompanyService = financialCompanyService;
    }

    @Bean
    private EnumMapper getEnumMapper() {
        EnumMapper mapper = new EnumMapper();
        mapper.put("CompanyType", CompanyType.class);
        return mapper;
    }

    @GetMapping("/companyTypes")
    public List<EnumMapperValue> getCompanyTypes() {
        return enumMapper.get("CompanyType");
    }

    @GetMapping("/{id}")
    public ResponseEntity<FinancialCompanyResponse> findFinancialCompany(@PathVariable Long id) {
        return ResponseEntity.ok(financialCompanyService.findFinancialCompany(id));
    }

    @GetMapping
    public ResponseEntity<List<FinancialCompanyResponse>> findAll() {
        return ResponseEntity.ok(financialCompanyService.findAll());
    }

}
