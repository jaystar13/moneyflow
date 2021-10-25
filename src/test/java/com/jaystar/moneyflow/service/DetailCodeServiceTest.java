package com.jaystar.moneyflow.service;

import com.jaystar.moneyflow.domain.DetailCode;
import com.jaystar.moneyflow.repository.DetailCodeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

class DetailCodeServiceTest {
    @InjectMocks
    private DetailCodeService detailCodeService;

    @Mock
    private DetailCodeRepository detailCodeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void find() {
        List<DetailCode> mockDetailCodes = new ArrayList<>();
        mockDetailCodes.add(new DetailCode(1234L, "A123", "A1231", "detailCode"));

        given(detailCodeRepository.findByMasterCode("A123")).willReturn(mockDetailCodes);

        List<DetailCode> detailCodes = detailCodeService.getDetailCodes("A123");
        DetailCode detailCode = detailCodes.get(0);

        assertThat(detailCode.getMasterCode()).isEqualTo("A123");
    }

    @Test
    void modifyDetailCodeName() {
        DetailCode detailCode = new DetailCode(1234L, "A123", "A1231", "detail_code");

        given(detailCodeRepository.findById(1234L))
                .willReturn(detailCode);

        DetailCode updated = detailCodeService.modifyDetailCodeName(1234L, "updated_detail_code");

        assertThat(updated.getDetailCodeName()).isEqualTo("updated_detail_code");
    }
}
