package com.jaystar.moneyflow.repository;

import com.jaystar.moneyflow.domain.Code;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

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
        Code codeForSave = new Code();
        codeForSave.setName("코드1");

        //when
        Code savedCode = codeRepository.save(codeForSave);

        //then
        assertThat(codeForSave).isEqualTo(savedCode);
    }

    @DisplayName("모든 코드를 조회한다.")
    @Test
    void findAll() {
        //given
        Code code1 = new Code();
        codeRepository.save(code1);

        Code code2 = new Code();
        codeRepository.save(code2);

        Code code3 = new Code();
        codeRepository.save(code3);

        //when
        Iterable<Code> codes = codeRepository.findAll();

        //then
        assertThat(codes).hasSize(3).contains(code1, code2, code3);
    }

    @DisplayName("코드가 삭제된다.")
    @Test
    void delete() {
        //given
        Code code1 = new Code();
        codeRepository.save(code1);

        Code code2 = new Code();
        codeRepository.save(code2);

        Code code3 = new Code();
        codeRepository.save(code3);

        //when
        codeRepository.deleteById(code2.getId());
        Iterable<Code> codes = codeRepository.findAll();

        //then
        assertThat(codes).hasSize(2).contains(code1, code3);
    }

    @DisplayName("모든코드가 삭제된다.")
    @Test
    void deleteAll() {
        Code code1 = new Code();
        codeRepository.save(code1);

        Code code2 = new Code();
        codeRepository.save(code2);

        Code code3 = new Code();
        codeRepository.save(code3);

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
        Code codeForUpdate = new Code();
        codeForUpdate.setName("코드100");

        Code savedCode = codeRepository.save(codeForUpdate);

        //when
        Code code = codeRepository.findById(savedCode.getId()).get();
        code.setName("코드100_update");

        //then
        assertThat(codeRepository.findById(code.getId()).get().getName()).isEqualTo("코드100_update");
    }

}
