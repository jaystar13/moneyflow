package com.jaystar.moneyflow.service;

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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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

        masterCode1 = new MasterCode(1L, "123", "master code1");
        masterCode2 = new MasterCode(2L, "B", "master code2");

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
                .containsExactly(masterCode1.getCodeName(), masterCode2.getCodeName());
    }

    @DisplayName("마스터코드를 한건 조회한다.")
    @Test
    void findMasterCode() {
        given(masterCodeRepository.findById(anyLong()))
                .willReturn(Optional.of(masterCode1));

        MasterCodeResponse masterCodeResponse = masterCodeService.findMasterCode(1L);

        assertThat(masterCodeResponse.getCode()).isEqualTo(masterCode1.getCode());
        assertThat(masterCodeResponse.getCodeName()).isEqualTo(masterCode1.getCodeName());
    }

    @DisplayName("단일 건 조회시 해당 ID가 존재하지 않으면 예외를 발생한다.")
    @Test
    void findMasterCodeException() {
        given(masterCodeRepository.findById(anyLong())).willReturn(Optional.empty());

        assertThatThrownBy(() -> masterCodeService.findMasterCode(0L))
                .isInstanceOf(EntityNotFoundException.class);
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

        assertThat(masterCode1.getCodeName()).isEqualTo(masterCodeRequest.getCodeName());
    }
}
