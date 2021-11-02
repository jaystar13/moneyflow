package com.jaystar.moneyflow.service;

import com.jaystar.moneyflow.domain.MasterCode;
import com.jaystar.moneyflow.dto.MasterCodeRequest;
import com.jaystar.moneyflow.repository.MasterCodeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
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

    @Test
    void getMasterCodes() {
        List<MasterCode> mockMasterCodes = new ArrayList<>();
        mockMasterCodes.add(new MasterCode(1L, "123", "code"));

        given(masterCodeRepository.findAll()).willReturn(mockMasterCodes);

        List<MasterCode> masterCodes = masterCodeService.getMasterCodes();
        MasterCode masterCode = masterCodes.get(0);

        assertThat(masterCode.getCode()).isEqualTo("123");
        assertThat(masterCode.getCodeName()).isEqualTo("code");
    }

    @Test
    void getMasterCodeById() {
        long codeId = 10L;
        given(masterCodeRepository.findById(codeId)).willReturn(Optional.of(new MasterCode(10L, "A123", "TEST")));

        MasterCode masterCode = masterCodeService.getMasterCodeById(codeId);

        assertThat(masterCode.getCode()).isEqualTo("A123");
        assertThat(masterCode.getCodeName()).isEqualTo("TEST");
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
