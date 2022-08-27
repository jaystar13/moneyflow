package com.jaystar.moneyflow.code.service;

import com.jaystar.moneyflow.code.domain.Code;
import com.jaystar.moneyflow.code.domain.CodeType;
import com.jaystar.moneyflow.code.dto.CodeRequest;
import com.jaystar.moneyflow.code.dto.CodeResponse;
import com.jaystar.moneyflow.code.repository.CodeRepository;
import com.jaystar.moneyflow.common.exception.EntityNotFoundException;
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
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CodeServiceTest {
    private CodeService codeService;

    @Mock
    private CodeRepository codeRepository;

    @BeforeEach
    void setUp() {
        codeService = new CodeService(codeRepository);
    }

    @DisplayName("모든 코드를 조회한다.")
    @Test
    void findAllCodes() {
        CodeType codeType = CodeType.builder()
                .name("codeType")
                .build();

        Code code1 = Code.builder()
                .name("code1")
                .codeType(codeType)
                .build();

        Code code2 = Code.builder()
                .name("code2")
                .codeType(codeType)
                .build();

        when(codeRepository.findAll()).thenReturn(Arrays.asList(code1, code2));

        List<CodeResponse> codes = codeService.findAllCodes();

        assertThat(codes).extracting("name")
                .containsExactly(code1.getName(), code2.getName());
    }

    @DisplayName("단일 코드를 조회한다.")
    @Test
    void findCode() {
        Code code = Code.builder()
                .name("code1")
                .codeType(CodeType.builder()
                        .name("코드타입")
                        .build())
                .build();

        when(codeRepository.findById(anyLong())).thenReturn(Optional.of(code));

        CodeResponse codeResponse = codeService.findCode(1L);

        assertThat(codeResponse.getName()).isEqualTo(code.getName());
    }

    @DisplayName("단일 코드 조회시 해당 ID가 없으면 예외 처리한다.")
    @Test
    void findCodeException() {
        when(codeRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> codeService.findCode(0L))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @DisplayName("코드를 생성한다.")
    @Test
    void save() {
        CodeRequest codeRequest = CodeRequest.builder()
                .name("테스트")
                .build();
        codeService.save(codeRequest);

        verify(codeRepository).save(codeRequest.toCode());
    }

    @DisplayName("코드명을 수정한다.")
    @Test
    void update() {
        Code code = Code.builder()
                .name("테스트")
                .codeType(CodeType.builder()
                        .name("코드타입")
                        .build())
                .build();
        when(codeRepository.findById(anyLong())).thenReturn(Optional.of(code));

        CodeRequest codeRequest = CodeRequest.builder()
                .name("테스트_update")
                .build();
        codeService.updateCode(1L, codeRequest);

        assertThat(code.getName()).isEqualTo(codeRequest.getName());
    }

    @DisplayName("코드를 삭제한다.")
    @Test
    void delete() {
        codeService.deleteCode(1L);

        verify(codeRepository).deleteById(1L);
    }

    @DisplayName("코드명으로 조회한다.")
    @Test
    void containing() {
        when(codeRepository.findByNameContaining("테스")).thenReturn(anyList());

        List<CodeResponse> codeResponses = codeService.findByNameContaining("테스");

        assertThat(codeResponses).isNotNull();
    }
}
