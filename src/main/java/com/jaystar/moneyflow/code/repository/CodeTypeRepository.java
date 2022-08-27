package com.jaystar.moneyflow.code.repository;

import com.jaystar.moneyflow.code.domain.CodeType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CodeTypeRepository extends JpaRepository<CodeType, Long> {
    List<CodeType> findByNameContaining(String name);
}
