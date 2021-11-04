package com.jaystar.moneyflow.controller;

import com.jaystar.moneyflow.dto.MasterCodeResponse;
import com.jaystar.moneyflow.service.MasterCodeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MaterCodeController.class)
public class MaterCodeControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private MasterCodeService masterCodeService;

    @Test
    void getMasterCodes() throws Exception {
        List<MasterCodeResponse> masterCodeResponses = Arrays.asList(
                new MasterCodeResponse(1L, "AAA", "master code1"),
                new MasterCodeResponse(2L, "BBB", "master code2")
        );

        given(masterCodeService.findAllMasterCodes()).willReturn(masterCodeResponses);

        mvc.perform(get("/master-codes"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("master code2")));
    }

    @Test
    void addMasterCode() {

    }
}
