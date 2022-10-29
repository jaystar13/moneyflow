package com.jaystar.moneyflow.company;

import com.jaystar.moneyflow.company.domain.CompanyType;
import com.jaystar.moneyflow.util.mapper.EnumMapper;
import com.jaystar.moneyflow.util.mapper.EnumMapperValue;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/financial-company")
public class FinancialCompanyController {

    private final EnumMapper enumMapper;

    public FinancialCompanyController() {
        this.enumMapper = getEnumMapper();
    }

    private EnumMapper getEnumMapper() {
        EnumMapper enumMapper = new EnumMapper();

        enumMapper.put("CompanyType", CompanyType.class);

        return enumMapper;
    }

    @GetMapping("/companyType")
    public List<EnumMapperValue> getCompanyTypes() {
        return enumMapper.get("CompanyType");
    }
}
