package com.jaystar.moneyflow.service;

import com.jaystar.moneyflow.domain.CodeItem;
import com.jaystar.moneyflow.domain.MasterCode;
import com.jaystar.moneyflow.dto.MasterCodeRequest;
import com.jaystar.moneyflow.dto.MasterCodeResponse;
import com.jaystar.moneyflow.repository.MasterCodeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class MasterCodeServiceTest {

    @InjectMocks
    private MasterCodeService masterCodeService;

    @Mock
    private MasterCodeRepository masterCodeRepository;

    private MasterCode masterCode1;

    private MasterCode masterCode2;

    private MasterCodeRequest masterCodeRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        masterCode1 = new MasterCode(1L, new CodeItem("123", "master code1"));
        masterCode2 = new MasterCode(2L, new CodeItem("B", "master code2"));

        masterCodeRequest = MasterCodeRequest.builder()
                .code("A")
                .codeName("마스터코드1")
                .build();
    }

    @DisplayName("모든 마스터 코드를 조회한다.")
    @Test
    void findAllMasterCodes() {
        given(masterCodeRepository.findAll()).willReturn(Arrays.asList(masterCode1, masterCode2));

        List<MasterCodeResponse> masterCodes = masterCodeService.findAllMasterCodes();

        assertThat(masterCodes).extracting("codeName")
                .containsExactly(masterCode1.codeName(), masterCode2.codeName());
    }

    @DisplayName("마스터코드를 한건 조회한다.")
    @Test
    void findMasterCode() {
        given(masterCodeRepository.findById(anyLong()))
                .willReturn(Optional.of(masterCode1));

        MasterCodeResponse masterCodeResponse = masterCodeService.findMasterCode(1L);

        assertThat(masterCodeResponse.getCode()).isEqualTo(masterCode1.code());
        assertThat(masterCodeResponse.getCodeName()).isEqualTo(masterCode1.codeName());
    }

    @DisplayName("단일 건 조회시 해당 ID가 존재하지 않으면 예외를 발생한다.")
    @Test
    void findMasterCodeException() {
        given(masterCodeRepository.findById(anyLong())).willReturn(Optional.empty());

        assertThatThrownBy(() -> masterCodeService.findMasterCode(0L))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @DisplayName("마스터코드명으로 조회한다.")
    @Test
    void containCodeName() {
        given(masterCodeRepository.findByCodeNameContaining("코드1")).willReturn(anyList());

        List<MasterCodeResponse> masterCodeResponses = masterCodeService.findByCodeNameContaining("코드1");
        assertThat(masterCodeResponses).isNotNull();
    }

    @DisplayName("마스터 코드를 추가한다.")
    @Test
    void add() {
        given(masterCodeRepository.save(any())).willReturn(masterCode1);

        masterCodeService.add(masterCodeRequest);

        verify(masterCodeRepository).save(any());
    }

    @DisplayName("마스터 코드를 수정한다.")
    @Test
    void update() {
        given(masterCodeRepository.findById(anyLong()))
                .willReturn(Optional.of(masterCode1));

        masterCodeService.update(1L, masterCodeRequest);

        assertThat(masterCode1.codeName()).isEqualTo(masterCodeRequest.getCodeName());
    }

    @DisplayName("마스터 코드를 삭제한다")
    @Test
    void deleteMasterCode() {
        masterCodeService.deleteMasterCode(1L);

        verify(masterCodeRepository).deleteById(1L);
    }

    @DisplayName("모든 마스터 코드를 삭제한다")
    @Test
    void deleteAllMasterCodes() {
        masterCodeService.deleteAllMasterCodes();

        verify(masterCodeRepository).deleteAll();
    }
}
