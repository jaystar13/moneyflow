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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class MasterCodeServiceTest {

    @InjectMocks
    private MasterCodeService masterCodeService;

    @Mock
    private MasterCodeRepository masterCodeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("모든 마스터 코드를 조회한다.")
    @Test
    void findAllMasterCodes() {
        MasterCode masterCode1 = new MasterCode(1L, "123", "master code1");
        MasterCode masterCode2 = new MasterCode(2L, "B", "master code2");

        given(masterCodeRepository.findAll()).willReturn(Arrays.asList(masterCode1, masterCode2));

        List<MasterCodeResponse> masterCodes = masterCodeService.findAllMasterCodes();

        assertThat(masterCodes).extracting("codeName")
                .containsExactly(masterCode1.getCodeName(), masterCode2.getCodeName());
    }

    @DisplayName("마스터코드를 한건 조회한다.")
    @Test
    void findMasterCode() {
        MasterCode masterCode1 = new MasterCode(10L, "A123", "TEST");
        given(masterCodeRepository.findById(anyLong()))
                .willReturn(Optional.of(masterCode1));

        MasterCodeResponse masterCodeResponse = masterCodeService.findMasterCode(1L);

        assertThat(masterCodeResponse.getCode()).isEqualTo(masterCode1.getCode());
        assertThat(masterCodeResponse.getCodeName()).isEqualTo(masterCode1.getCodeName());
    }

    @Test
    void addMasterCode() {
        given(masterCodeRepository.save(any())).willReturn(MasterCode.builder()
                .code("1234")
                .codeName("code")
                .build());

        MasterCodeRequest request = MasterCodeRequest.builder()
                .code("")
                .codeName("")
                .build();

        masterCodeService.addMasterCode(request);

        verify(masterCodeRepository).save(any());
    }

    @Test
    void updateMasterCode() {
        MasterCode masterCode = new MasterCode(123L, "1234", "code");

        given(masterCodeRepository.findById(123L))
                .willReturn(Optional.of(masterCode));

        masterCodeService.updateMasterCodeName(123L, "code1");

        assertThat(masterCode.getCodeName()).isEqualTo("code1");
    }
}
