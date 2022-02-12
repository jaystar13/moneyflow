package com.jaystar.moneyflow.code.repository;

import com.jaystar.moneyflow.code.domain.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CodeRepository extends JpaRepository<Code, Long> {
    List<Code> findByNameContaining(String contain);

    @Query("SELECT c FROM Code c JOIN c.codeType ct WHERE c.codeType.name LIKE %:codeTypeName%")
    List<Code> findByCodeTypeNameContaining(@Param("codeTypeName") String codeTypeName);
}
