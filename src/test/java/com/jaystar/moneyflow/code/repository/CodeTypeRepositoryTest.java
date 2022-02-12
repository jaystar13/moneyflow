package com.jaystar.moneyflow.code.repository;

import com.jaystar.moneyflow.code.domain.CodeType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CodeTypeRepositoryTest {
    @Autowired
    private CodeTypeRepository codeTypeRepository;

    @DisplayName("코드타입을 저장한다.")
    @Test
    void save() {
        CodeType codeType = CodeType.builder()
                .name("코드타입")
                .build();

        CodeType saved = codeTypeRepository.save(codeType);

        assertThat(saved).isEqualTo(codeType);
    }

    @DisplayName("코드타입을 수정한다.")
    @Test
    void update() {
        CodeType codeType = CodeType.builder()
                .name("코드타입1")
                .build();

        CodeType updatingCodeType = CodeType.builder()
                .name("업데이트 코드타입1")
                .build();

        codeType.update(updatingCodeType);

        assertThat(codeType.getName()).isEqualTo(updatingCodeType.getName());
    }
}
