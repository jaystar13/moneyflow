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

    @BeforeEach
    void setUp() {
    }

    @DisplayName("코드가 저장된다.")
    @Test
    void save() {
        //given
        Code code = givenCode();

        //when
        Code saveCode = codeRepository.saveAndFlush(code);

        //then
        assertThat(saveCode).isEqualTo(code);
    }

    @DisplayName("단건 코드를 조회한다.")
    @Test
    void findById() {
        //given
        Code code = givenCode();

        //when
        Code saveCode = codeRepository.saveAndFlush(code);
        Code find = codeRepository.findById(saveCode.getId()).get();

        //then
        assertThat(find.getName()).isEqualTo(saveCode.getName());
    }

    @DisplayName("모든 코드를 조회한다.")
    @Test
    void findAll() {
        //given
        List<Code> givenCodes = givenCodes();

        //when
        codeRepository.saveAll(givenCodes);
        List<Code> findingCodes = codeRepository.findAll();

        //then
        assertThat(findingCodes).isEqualTo(givenCodes);
    }

    @DisplayName("코드가 삭제된다.")
    @Test
    void delete() {
        //given
        Code code = givenCode();

        //when
        Code saveCode = codeRepository.saveAndFlush(code);
        codeRepository.deleteById(saveCode.getId());

        List<Code> all = codeRepository.findAll();

        //then
        assertThat(all).hasSize(0);
    }

    @DisplayName("모든코드가 삭제된다.코드타입은 삭제되지 않는다.")
    @Test
    void deleteAll() {
        //given
        List<Code> givenCodes = givenCodes();

        //when
        codeRepository.saveAll(givenCodes);
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
        //given
        List<Code> givenCodes = givenCodes();

        //when
        List<Code> saveCodes = codeRepository.saveAll(givenCodes);
        List<Code> findingCodes = codeRepository.findByNameContaining("은행");

        //then
        assertThat(findingCodes).isEqualTo(saveCodes.stream()
                .filter(code -> code.getName().contains("은행"))
                .collect(Collectors.toList()));
    }

    @DisplayName("코드타입명으로 코드를 조회한다.")
    @Test
    void codeSearch() {
        //given
        List<Code> givenCodes = givenCodes();
        codeRepository.saveAll(givenCodes);

        //when
        String codeTypeName = "행구";
        List<Code> codes = codeRepository.findByCodeTypeNameContaining(codeTypeName);

        //then
        assertThat(codes).hasSize(3);

    }

    private CodeType saveCodeType() {
        CodeType codeType = CodeType.builder()
                .name("은행구분")
                .build();

        return codeTypeRepository.save(codeType);
    }

    private Code givenCode() {
        CodeType saveCodeType = saveCodeType();

        Code code = Code.builder()
                .name("대한은행")
                .codeType(saveCodeType)
                .build();

        return code;
    }

    private List<Code> givenCodes() {
        CodeType saveCodeType = saveCodeType();

        Code code1 = Code.builder()
                .name("대한은행")
                .codeType(saveCodeType)
                .build();

        Code code2 = Code.builder()
                .name("민국은행")
                .codeType(saveCodeType)
                .build();

        Code code3 = Code.builder()
                .name("만세은행")
                .codeType(saveCodeType)
                .build();

        return Arrays.asList(code1, code2, code3);
    }
}
