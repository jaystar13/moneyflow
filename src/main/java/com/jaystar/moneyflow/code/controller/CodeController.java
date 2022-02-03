package com.jaystar.moneyflow.code.controller;

import com.jaystar.moneyflow.code.dto.CodeResponse;
import com.jaystar.moneyflow.code.service.CodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/contain-word")
    public ResponseEntity<List<CodeResponse>> findByNameContaining(@RequestParam(defaultValue = "") String contain) {
        List<CodeResponse> codeResponses = codeService.findByNameContaining(contain);
        return ResponseEntity.ok(codeResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CodeResponse> findCode(@PathVariable Long id) {
        return ResponseEntity.ok(codeService.findCode(id));
    }
}
