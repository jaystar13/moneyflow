package com.jaystar.moneyflow.repository;

import com.jaystar.moneyflow.domain.MasterCode;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MasterCodeRepository {
    List<MasterCode> findAll();

    Optional<MasterCode> findById(Long id);

    List<MasterCode> findByCodeNameContaining(String contain);

    <S extends MasterCode> S save(S entity);
}
