package com.jaystar.moneyflow.code.controller;

import com.jaystar.moneyflow.code.dto.CodeRequest;
import com.jaystar.moneyflow.code.dto.CodeResponse;
import com.jaystar.moneyflow.code.service.CodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
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

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCode(@PathVariable Long id, @RequestBody CodeRequest codeRequest) {
        codeService.updateCode(id, codeRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCode(@PathVariable Long id) {
        codeService.deleteCode(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Void> addCode(@RequestBody @Valid CodeRequest codeRequest) {
        Long saveId = codeService.save(codeRequest);
        return ResponseEntity.created(URI.create("/api/codes/" + saveId)).build();
    }
}
