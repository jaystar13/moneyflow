package com.jaystar.moneyflow.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaystar.moneyflow.dto.MasterCodeRequest;
import com.jaystar.moneyflow.dto.MasterCodeResponse;
import com.jaystar.moneyflow.service.MasterCodeService;
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

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MaterCodeController.class)
public class MaterCodeControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private MasterCodeService masterCodeService;

    private MasterCodeRequest masterCodeRequest;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        masterCodeRequest = MasterCodeRequest.builder()
                .code("A12")
                .codeName("마스터코드 원")
                .build();

        objectMapper = new ObjectMapper();
    }

    @DisplayName("마스터 코드 전체를 조회한다.")
    @Test
    void findAllMasterCodes() throws Exception {
        List<MasterCodeResponse> masterCodeResponses = Arrays.asList(
                new MasterCodeResponse(1L, "AAA", "master code1"),
                new MasterCodeResponse(2L, "BBB", "master code2")
        );

        given(masterCodeService.findAllMasterCodes()).willReturn(masterCodeResponses);

        mvc.perform(get("/master-codes")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("master code2")))
                .andDo(print());
    }

    @DisplayName("특정 단어가 포함된 마스터 코드를 조회한다.")
    @Test
    void findByCodeNameContain() throws Exception {
        List<MasterCodeResponse> masterCodeResponses = Arrays.asList(
                new MasterCodeResponse(1L, "A", "CODE-A"),
                new MasterCodeResponse(2L, "B", "CODE-B"),
                new MasterCodeResponse(3L, "C", "CODE-C")
        );

        given(masterCodeService.findByCodeNameContaining(anyString())).willReturn(masterCodeResponses);

        mvc.perform(get("/master-codes/contain-word")
                        .param("contain", "A")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
        ;
    }

    @DisplayName("마스터코드를 추가한다.")
    @Test
    void addMasterCode() throws Exception {
        given(masterCodeService.add(any())).willReturn(1L);

        mvc.perform(post("/master-codes")
                        .header("authorization", "Bearer ADMIN_TOKEN")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(masterCodeRequest)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/master-codes/1"))
                .andDo(print());
    }
}
