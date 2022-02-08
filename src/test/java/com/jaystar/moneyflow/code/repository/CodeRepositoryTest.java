package com.jaystar.moneyflow.code.repository;

import com.jaystar.moneyflow.code.domain.Code;
import com.jaystar.moneyflow.code.domain.CodeType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
public class CodeRepositoryTest {

    @Autowired
    CodeRepository codeRepository;

    @DisplayName("코드가 저장된다.")
    @Test
    void save() {
        //given
        Code codeForSave = Code.builder()
                .name("코드1")
                .build();

        CodeType codeType = CodeType.builder()
                .name("코드타입")
                .build();
        codeForSave.setCodeType(codeType);

        //when
        Code savedCode = codeRepository.saveAndFlush(codeForSave);

        //then
        assertThat(codeForSave).isEqualTo(savedCode);
    }

    @DisplayName("모든 코드를 조회한다.")
    @Test
    void findAll() {
        //given
        CodeType codeType = CodeType.builder()
                .name("코드타입")
                .build();

        Code code1 = Code.builder()
                .name("TEST_1")
                .codeType(codeType)
                .build();

        Code code2 = Code.builder()
                .name("TEST_2")
                .codeType(codeType)
                .build();

        Code code3 = Code.builder()
                .name("TEST_3")
                .codeType(codeType)
                .build();

        List<Code> codes = Arrays.asList(code1, code2, code3);
        codeRepository.saveAll(codes);

        //when
        List<Code> findingCodes = codeRepository.findAll();

        //then
        assertThat(findingCodes).hasSize(3).contains(code1, code2, code3);
    }

    @DisplayName("코드가 삭제된다.")
    @Test
    void delete() {
        //given
        CodeType codeType = CodeType.builder()
                .name("코드타입")
                .build();

        Code code1 = Code.builder()
                .name("TEST_1")
                .codeType(codeType)
                .build();

        Code code2 = Code.builder()
                .name("TEST_2")
                .codeType(codeType)
                .build();

        Code code3 = Code.builder()
                .name("EST_3")
                .codeType(codeType)
                .build();

        List<Code> codes = Arrays.asList(code1, code2, code3);
        codeRepository.saveAll(codes);

        //when
        codeRepository.deleteById(code2.getId());
        List<Code> findingCodes = codeRepository.findAll();

        //then
        assertThat(findingCodes).hasSize(2).contains(code1, code3);
    }

    @DisplayName("모든코드가 삭제된다.")
    @Test
    void deleteAll() {
        //given
        CodeType codeType = CodeType.builder()
                .name("코드타입")
                .build();

        Code code1 = Code.builder()
                .name("TEST_1")
                .codeType(codeType)
                .build();

        Code code2 = Code.builder()
                .name("TEST_2")
                .codeType(codeType)
                .build();

        Code code3 = Code.builder()
                .name("EST_3")
                .codeType(codeType)
                .build();

        List<Code> codes = Arrays.asList(code1, code2, code3);
        codeRepository.saveAll(codes);

        codeRepository.deleteAll();

        assertThat(codeRepository.findAll()).isEmpty();
    }

    @DisplayName("존재하지 않는 아이디 삭제시 예외를 발생한다.")
    @Test
    void delete_exception() {
        Long notExistId = 9999L;

        //then
        assertThatThrownBy(() -> codeRepository.deleteById(notExistId))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    @DisplayName("코드가 수정된다.")
    @Test
    void update() {
        //given
        Code code = Code.builder()
                .name("코드100")
                .codeType(CodeType.builder()
                        .name("코드타입")
                        .build())
                .build();

        Code updatingCode = Code.builder()
                .name("코드100_update")
                .codeType(CodeType.builder()
                        .name("코드타입")
                        .build())
                .build();

        //when
        code.update(updatingCode);

        //then
        assertThat(code.getName()).isEqualTo(updatingCode.getName());
    }

    @DisplayName("코드명으로 조회한다.")
    @Test
    void findByNameContaining() {
        //given
        CodeType codeType = CodeType.builder()
                .name("코드타입")
                .build();

        Code code1 = Code.builder()
                .name("TEST_1")
                .codeType(codeType)
                .build();

        Code code2 = Code.builder()
                .name("TEST_2")
                .codeType(codeType)
                .build();

        Code code3 = Code.builder()
                .name("EST_3")
                .codeType(codeType)
                .build();

        List<Code> codes = Arrays.asList(code1, code2, code3);
        codeRepository.saveAll(codes);

        //when
        Iterable<Code> findingCodes = codeRepository.findByNameContaining("TEST");

        //then
        assertThat(findingCodes).containsExactly(code1, code2);
    }

}
