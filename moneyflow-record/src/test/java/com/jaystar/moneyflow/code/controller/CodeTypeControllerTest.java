package com.jaystar.moneyflow.code.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaystar.moneyflow.code.dto.CodeTypeRequest;
import com.jaystar.moneyflow.code.dto.CodeTypeResponse;
import com.jaystar.moneyflow.code.service.CodeTypeService;
import com.jaystar.moneyflow.config.EnableMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@EnableMockMvc
@WebMvcTest(controllers = CodeTypeController.class)
class CodeTypeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CodeTypeService codeTypeService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @DisplayName("모든 코드타입을 조회한다.")
    @Test
    void findAllCodeTypes() throws Exception {
        CodeTypeResponse codeTypeResponse1 = new CodeTypeResponse(1L, "code type one", Collections.emptyList());
        CodeTypeResponse codeTypeResponse2 = new CodeTypeResponse(2L, "code type two", Collections.emptyList());
        CodeTypeResponse codeTypeResponse3 = new CodeTypeResponse(3L, "code type three", Collections.emptyList());

        given(codeTypeService.findAllCodeTypes()).willReturn(Arrays.asList(codeTypeResponse1, codeTypeResponse2, codeTypeResponse3));

        mockMvc.perform(get("/api/code-types").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("code type")))
                .andDo(print());
    }

    @DisplayName("코드타입 한건을 조회한다.")
    @Test
    void findCodeType() throws Exception {
        CodeTypeResponse codeTypeResponse = new CodeTypeResponse(1L, "code type", Collections.emptyList());

        given(codeTypeService.findCodeType(1L)).willReturn(codeTypeResponse);

        mockMvc.perform(get("/api/code-types/{id}", 1L)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("code type")))
                .andDo(print());
    }

    @DisplayName("코드타입명칭으로 조회한다.")
    @Test
    void findCodeTypeByContaining() throws Exception {
        List<CodeTypeResponse> codeTypeResponse = Arrays.asList(
                new CodeTypeResponse(1L, "은행 코드 타입", Collections.emptyList()),
                new CodeTypeResponse(2L, "계좌 구분 타입", Collections.emptyList()),
                new CodeTypeResponse(3L, "지출 구분 타입", Collections.emptyList())
        );

        given(codeTypeService.findByNameContaining(anyString())).willReturn(codeTypeResponse);

        mockMvc.perform(get("/api/code-types/contain-word")
                        .param("contain", "타입")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("타입")))
                .andDo(print());
    }

    @DisplayName("코드타입을 생성한다")
    @Test
    void addCodeType() throws Exception {
        given(codeTypeService.save(any())).willReturn(1L);

        CodeTypeRequest codeTypeRequest = CodeTypeRequest.builder()
                .name("이런 타입")
                .build();

        mockMvc.perform(post("/api/code-types")
                        .content(objectMapper.writeValueAsString(codeTypeRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/code-types/1"))
                .andDo(print());
    }

    @DisplayName("코드타입을 수정한다.")
    @Test
    void updateCodeType() throws Exception {
        CodeTypeRequest codeTypeRequest = CodeTypeRequest.builder()
                .name("저런 타입")
                .build();

        mockMvc.perform(put("/api/code-types/{id}", 1L)
                        .content(objectMapper.writeValueAsString(codeTypeRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @DisplayName("코드타입을 삭제한다.")
    @Test
    void deleteCodeType() throws Exception {
        mockMvc.perform(delete("/api/code-types/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(print());
    }
}