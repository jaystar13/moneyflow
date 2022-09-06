package com.jaystar.moneyflow.code.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaystar.moneyflow.code.dto.CodeRequest;
import com.jaystar.moneyflow.code.dto.CodeResponse;
import com.jaystar.moneyflow.code.service.CodeService;
import org.junit.jupiter.api.BeforeEach;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = CodeController.class)
class CodeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CodeService codeService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

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

    @DisplayName("특정단어가 포함된 코드를 모두 조회한다.")
    @Test
    void findByNameContaining() throws Exception {
        List<CodeResponse> codeResponses = Arrays.asList(
                new CodeResponse(1L, "TEST1"),
                new CodeResponse(2L, "TEST2"),
                new CodeResponse(3L, "TEST3")
        );

        given(codeService.findByNameContaining(anyString())).willReturn(codeResponses);

        mockMvc.perform(get("/api/codes/contain-word")
                        .param("contain", "EST")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("EST")))
                .andDo(print());
    }

    @DisplayName("코드 단건을 조회한다.")
    @Test
    void findCode() throws Exception {
        CodeResponse codeResponse = new CodeResponse(9000L, "TEST9000");

        given(codeService.findCode(9000L)).willReturn(codeResponse);

        mockMvc.perform(get("/api/codes/{id}", 9000L)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("TEST9000")))
                .andDo(print());
    }

    @DisplayName("코드를 수정한다.")
    @Test
    void updateCode() throws Exception {
        CodeRequest codeRequest = CodeRequest.builder()
                .name("TEST")
                .build();

        mockMvc.perform(put("/api/codes/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(codeRequest)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @DisplayName("코드를 삭제한다.")
    @Test
    void deleteCode() throws Exception {
        doNothing().when(codeService).deleteCode(any());

        mockMvc.perform(delete("/api/codes/{id}", 1L))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @DisplayName("코드를 생성한다.")
    @Test
    void addCode() throws Exception {
        CodeRequest parent = CodeRequest.builder()
                .name("parent")
                .build();

        CodeRequest codeRequest = CodeRequest.builder()
                .name("TEST1")
                .parent(parent)
                .build();

        given(codeService.save(any())).willReturn(1L);

        mockMvc.perform(post("/api/codes")
                        .content(objectMapper.writeValueAsString(codeRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/codes/1"))
                .andDo(print());
    }
}
