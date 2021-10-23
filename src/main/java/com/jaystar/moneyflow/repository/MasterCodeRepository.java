package com.jaystar.moneyflow.repository;

import com.jaystar.moneyflow.domain.MasterCode;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MasterCodeRepository extends CrudRepository<MasterCode, Long> {
    List<MasterCode> findAll();

    Optional<MasterCode> findById(Long id);
}
