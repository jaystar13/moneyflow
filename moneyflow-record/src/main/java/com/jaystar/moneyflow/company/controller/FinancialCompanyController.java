package com.jaystar.moneyflow.company.controller;

import com.jaystar.moneyflow.company.domain.CompanyType;
import com.jaystar.moneyflow.company.dto.FinancialCompanyRequest;
import com.jaystar.moneyflow.company.dto.FinancialCompanyResponse;
import com.jaystar.moneyflow.company.service.FinancialCompanyService;
import com.jaystar.moneyflow.util.mapper.EnumMapper;
import com.jaystar.moneyflow.util.mapper.EnumMapperValue;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
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

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid FinancialCompanyRequest financialCompanyRequest) {
        Long saveId = financialCompanyService.save(financialCompanyRequest);
        return ResponseEntity.created(URI.create("/api/financial-company/" + saveId)).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid FinancialCompanyRequest financialCompanyRequest) {
        financialCompanyService.update(id, financialCompanyRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        financialCompanyService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
