package com.jaystar.moneyflow.code.controller;

import com.jaystar.moneyflow.code.dto.CodeResponse;
import com.jaystar.moneyflow.code.service.CodeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CodeController.class)
class CodeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CodeService codeService;

    @DisplayName("코드를 전체 조회한다.")
    @Test
    void findCodes() throws Exception {
        List<CodeResponse> codeResponses = Arrays.asList(
                new CodeResponse(1L, "TEST1"),
                new CodeResponse(2L, "TEST2")
        );

        given(codeService.findAllCodes()).willReturn(codeResponses);

        mockMvc.perform(get("/api/codes")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("TEST")));
    }
}
