package com.jaystar.moneyflow.code.repository;

import com.jaystar.moneyflow.code.domain.Code;
import com.jaystar.moneyflow.code.domain.CodeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CodeTypeRepositoryTest {
    @Autowired
    private CodeTypeRepository codeTypeRepository;

    @Autowired
    private CodeRepository codeRepository;

    CodeType codeType;

    @BeforeEach
    void setUp() {
        codeType = CodeType.builder()
                .name("코드타입")
                .build();
    }

    @DisplayName("코드타입을 저장한다.")
    @Test
    void save() {
        CodeType saved = codeTypeRepository.saveAndFlush(codeType);

        assertThat(saved.getId()).isNotNull();
    }

    @DisplayName("저장된 코드타입을 수정한다.")
    @Test
    void update() {
        CodeType saved = codeTypeRepository.saveAndFlush(codeType);
        CodeType updateCodeType = CodeType.builder()
                .name("업데이트 코드타입1")
                .build();

        saved.update(updateCodeType);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getName()).isEqualTo(updateCodeType.getName());
    }

    @DisplayName("코드타입 삭제 시 코드도 삭제된다.")
    @Test
    void delete() {
        Code.builder()
                .codeType(codeType)
                .name("code1")
                .build();

        CodeType saved = codeTypeRepository.saveAndFlush(codeType);
        assertThat(codeRepository.findAll()).hasSize(1);

        codeTypeRepository.deleteById(saved.getId());
        assertThat(codeRepository.findAll()).isEmpty();
    }

    void findCodes() {
    }
}
