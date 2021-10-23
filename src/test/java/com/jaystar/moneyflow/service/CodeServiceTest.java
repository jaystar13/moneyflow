package com.jaystar.moneyflow.service;

import com.jaystar.moneyflow.domain.MasterCode;
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
import static org.mockito.BDDMockito.given;

public class CodeServiceTest {

    @InjectMocks
    private CodeService codeService;

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

        List<MasterCode> masterCodes = codeService.getMasterCodes();
        MasterCode masterCode = masterCodes.get(0);

        assertThat(masterCode.getCode()).isEqualTo("123");
        assertThat(masterCode.getCodeName()).isEqualTo("code");
    }

    @Test
    void getMasterCodeById() {
        Long codeId = 10L;
        given(masterCodeRepository.findById(codeId)).willReturn(Optional.of(new MasterCode(10L, "A123", "TEST")));

        MasterCode masterCode = codeService.getMasterCodeById(codeId);

        assertThat(masterCode.getCode()).isEqualTo("A123");
        assertThat(masterCode.getCodeName()).isEqualTo("TEST");
    }
}
