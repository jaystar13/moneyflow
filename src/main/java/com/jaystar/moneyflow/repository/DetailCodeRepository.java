package com.jaystar.moneyflow.repository;

import com.jaystar.moneyflow.domain.DetailCode;

import java.util.List;

public interface DetailCodeRepository {
    List<DetailCode> findByMasterCode(String masterCode);
}
