package com.jaystar.moneyflow.code.controller;

import com.jaystar.moneyflow.code.dto.CodeResponse;
import com.jaystar.moneyflow.code.service.CodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/codes")
public class CodeController {
    private final CodeService codeService;

    @GetMapping
    public ResponseEntity<List<CodeResponse>> findCodes() {
        return ResponseEntity.ok(codeService.findAllCodes());
    }
}
