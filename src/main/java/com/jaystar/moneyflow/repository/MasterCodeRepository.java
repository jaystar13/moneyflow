package com.jaystar.moneyflow.repository;

import com.jaystar.moneyflow.domain.MasterCode;

import java.util.List;
import java.util.Optional;

public interface MasterCodeRepository {
    List<MasterCode> findAll();

    Optional<MasterCode> findById(Long id);

    <S extends MasterCode> S save(S entity);
}
