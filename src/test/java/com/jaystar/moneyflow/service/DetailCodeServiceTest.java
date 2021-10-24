package com.jaystar.moneyflow.service;

import com.jaystar.moneyflow.domain.DetailCode;
import com.jaystar.moneyflow.domain.MasterCode;
import com.jaystar.moneyflow.repository.DetailCodeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

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
        List<DetailCode> detailCodes = new ArrayList<>();
        detailCodes.add(new DetailCode(1234L, new MasterCode(111L, "A123", "masterCode"), "A1231", "detailCode"));

        given(detailCodeRepository.findByMasterCode("A123")).willReturn(detailCodes);
    }
}
