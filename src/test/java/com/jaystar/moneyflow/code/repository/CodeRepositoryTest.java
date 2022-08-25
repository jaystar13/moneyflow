package com.jaystar.moneyflow.code.repository;

import com.jaystar.moneyflow.code.domain.Code;
import com.jaystar.moneyflow.code.domain.CodeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
class CodeRepositoryTest {

    @Autowired
    private CodeRepository codeRepository;

    @Autowired
    private CodeTypeRepository codeTypeRepository;

    private Code code1;

    private Code code2;

    private Code code3;

    @BeforeEach
    void setUp() {
        CodeType codeType = CodeType.builder()
                .name("코드타입")
                .build();

        code1 = Code.builder()
                .name("TEST_1")
                .codeType(codeType)
                .build();

        code2 = Code.builder()
                .name("TEST_2")
                .codeType(codeType)
                .build();

        code3 = Code.builder()
                .name("EST_3")
                .codeType(codeType)
                .build();
    }

    @DisplayName("코드가 저장된다.")
    @Test
    void save() {
        //when
        Code savedCode = codeRepository.saveAndFlush(code1);

        //then
        assertThat(savedCode).isEqualTo(code1);
    }

    @DisplayName("단건 코드를 조회한다.")
    @Test
    void findById() {
        Code save = codeRepository.saveAndFlush(code1);
        Code find = codeRepository.findById(save.getId()).get();

        assertThat(find.getCodeType().getName()).isEqualTo("코드타입");
    }

    @DisplayName("모든 코드를 조회한다.")
    @Test
    void findAll() {
        //when
        codeRepository.saveAll(Arrays.asList(code1, code2, code3));
        List<Code> findingCodes = codeRepository.findAll();

        //then
        assertThat(findingCodes).contains(code1, code2, code3);
    }

    @DisplayName("코드가 삭제된다.")
    @Test
    void delete() {
        Code code = codeRepository.saveAndFlush(code1);
        codeRepository.deleteById(code.getId());

        List<Code> all = codeRepository.findAll();

        assertThat(all).hasSize(0);
    }

    @DisplayName("모든코드가 삭제된다.코드타입은 삭제되지 않는다.")
    @Test
    void deleteAll() {
        codeRepository.saveAll(Arrays.asList(code1, code2, code3));
        int codeTypeSize = codeTypeRepository.findAll().size();
        codeRepository.deleteAll();

        assertThat(codeRepository.findAll()).isEmpty();
        assertThat(codeTypeRepository.findAll()).hasSize(codeTypeSize);
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
        CodeType codeType = CodeType.builder()
                .name("goodType")
                .build();

        Code code = Code.builder()
                .name("코드100")
                .codeType(codeType)
                .build();

        CodeType updatingCodeType = CodeType.builder()
                .name("코드타입_update")
                .build();

        Code updatingCode = Code.builder()
                .name("코드100_update")
                .codeType(updatingCodeType)
                .build();

        //when
        code.update(updatingCode);

        //then
        assertThat(code).isEqualTo(updatingCode);
    }

    @DisplayName("코드명으로 조회한다.")
    @Test
    void findByNameContaining() {
        //when
        List<Code> saveCodes = codeRepository.saveAll(Arrays.asList(code1, code2, code3));
        List<Code> findingCodes = codeRepository.findByNameContaining("TEST");

        //then
        assertThat(findingCodes).isEqualTo(saveCodes.stream()
                .filter(code -> code.getName().contains("TEST"))
                .collect(Collectors.toList()));
    }

    @DisplayName("코드타입명으로 코드를 조회한다.")
    @Test
    void codeSearch() {
        //given
        codeRepository.saveAll(Arrays.asList(code1, code2, code3));
        
        //when
        String codeTypeName = "드타";
        List<Code> codes = codeRepository.findByCodeTypeNameContaining(codeTypeName);

        //then
        assertThat(codes).hasSize(3);

    }
}
