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

    private CodeType codeType;

    private List<Code> saveCodes;

    @BeforeEach
    void setUp() {
        codeType = CodeType.builder()
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

        saveCodes = Arrays.asList(code1, code2, code3);
        codeRepository.saveAll(saveCodes);
        codeRepository.flush();
    }

    @DisplayName("코드가 저장된다.")
    @Test
    void save() {
        //given
        Code codeForSave = Code.builder()
                .name("코드1")
                .build();

        codeForSave.setCodeType(codeType);

        //when
        Code savedCode = codeRepository.saveAndFlush(codeForSave);

        //then
        assertThat(codeForSave).isEqualTo(savedCode);
    }

    @DisplayName("단건 코드를 조회한다.")
    @Test
    void findById() {
        Code code = codeRepository.findById(1L).orElse(null);
        
        assertThat(code.getCodeType().getName()).isEqualTo("코드타입");
    }

    @DisplayName("모든 코드를 조회한다.")
    @Test
    void findAll() {
        //when
        List<Code> findingCodes = codeRepository.findAll();

        //then
        assertThat(findingCodes).isEqualTo(saveCodes);
    }

    @DisplayName("코드가 삭제된다.")
    @Test
    void delete() {
        //when
        codeRepository.deleteById(saveCodes.get(0).getId());
        List<Code> findingCodes = codeRepository.findAll();

        //then
        assertThat(findingCodes).hasSize(saveCodes.size() - 1);
    }

    @DisplayName("모든코드가 삭제된다.코드타입은 삭제되지 않는다.")
    @Test
    void deleteAll() {
        int codTypeSize = codeTypeRepository.findAll().size();
        codeRepository.deleteAll();

        assertThat(codeRepository.findAll()).isEmpty();
        assertThat(codeTypeRepository.findAll()).hasSize(codTypeSize);
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
        List<Code> findingCodes = codeRepository.findByNameContaining("TEST");

        //then
        assertThat(findingCodes).isEqualTo(saveCodes.stream()
                .filter(code -> code.getName().contains("TEST"))
                .collect(Collectors.toList()));
    }

    @DisplayName("코드타입명으로 코드를 조회한다.")
    @Test
    void codeSearch() {
        //when
        String codeTypeName = "드타";
        List<Code> codes = codeRepository.findByCodeTypeNameContaining(codeTypeName);

        //then
        assertThat(codes).hasSize(3);

    }
}
