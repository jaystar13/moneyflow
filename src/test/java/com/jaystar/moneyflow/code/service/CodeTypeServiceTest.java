package com.jaystar.moneyflow.code.service;

import com.jaystar.moneyflow.code.domain.CodeType;
import com.jaystar.moneyflow.code.dto.CodeTypeRequest;
import com.jaystar.moneyflow.code.dto.CodeTypeResponse;
import com.jaystar.moneyflow.code.repository.CodeTypeRepository;
import com.jaystar.moneyflow.common.exception.EntityNotFoundException;
import com.jaystar.moneyflow.common.exception.ErrorCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CodeTypeServiceTest {

    private CodeTypeService codeTypeService;

    @Mock
    private CodeTypeRepository codeTypeRepository;

    @BeforeEach
    void setUp() {
        codeTypeService = new CodeTypeService(codeTypeRepository);
    }

    @DisplayName("코드타입 단건을 조회한다.")
    @Test
    void findCodeType() {
        CodeType codeType = CodeType.builder()
                .name("code type1")
                .build();

        when(codeTypeRepository.findById(anyLong())).thenReturn(Optional.of(codeType));

        CodeTypeResponse codeTypeResponse = codeTypeService.findCodeType(1L);

        assertThat(codeTypeResponse.getName()).isEqualTo(codeType.getName());
    }

    @DisplayName("모든 코드타입을 조회한다.")
    @Test
    void findCodeTypes() {
        //given
        CodeType codeType1 = CodeType.builder()
                .name("code type1")
                .build();

        CodeType codeType2 = CodeType.builder()
                .name("code type2")
                .build();

        CodeType codeType3 = CodeType.builder()
                .name("code type3")
                .build();

        CodeType codeType4 = CodeType.builder()
                .name("code type4")
                .build();

        //when
        when(codeTypeRepository.findAll()).thenReturn(Arrays.asList(codeType1, codeType2, codeType3, codeType4));
        List<CodeTypeResponse> codeTypeResponses = codeTypeService.findAllCodeTypes();

        //then
        assertThat(codeTypeResponses).containsExactly(CodeTypeResponse.of(codeType1),
                CodeTypeResponse.of(codeType2),
                CodeTypeResponse.of(codeType3),
                CodeTypeResponse.of(codeType4));

    }

    @DisplayName("존재하지 않는 코드타입 조회시 예외를 발생한다.")
    @Test
    void findCodeTypeNotExist() {
        //when
        when(codeTypeRepository.findById(anyLong())).thenReturn(Optional.empty());

        //then
        assertThatThrownBy(() -> codeTypeService.findCodeType(1L))
                .hasMessage(ErrorCode.CODE_TYPE_NOT_FOUND.getMessage())
                .isInstanceOf(EntityNotFoundException.class);
    }

    @DisplayName("코드타입을 저장한다.")
    @Test
    void save() {
        CodeTypeRequest codeTypeRequest = CodeTypeRequest.builder()
                .name("code type request")
                .build();

        codeTypeService.save(codeTypeRequest);

        verify(codeTypeRepository).save(codeTypeRequest.toCodeType());
    }

    @DisplayName("코드타입을 수정한다.")
    @Test
    void update() {
        CodeType codeType = CodeType.builder()
                .name("code type")
                .build();

        CodeTypeRequest updateRequest = CodeTypeRequest.builder()
                .name("update code type")
                .build();

        when(codeTypeRepository.findById(anyLong())).thenReturn(Optional.of(codeType));

        codeTypeService.update(1L, updateRequest);

        assertThat(codeType.getName()).isEqualTo(updateRequest.getName());
    }

    @DisplayName("코드타입을 삭제한다.")
    @Test
    void delete() {
        codeTypeService.delete(1L);

        verify(codeTypeRepository).deleteById(1L);
    }
}