package com.jaystar.moneyflow.code.repository;

import com.jaystar.moneyflow.code.domain.Code;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CodeRepository extends JpaRepository<Code, Long> {
    List<Code> findByNameContaining(String contain);
}
